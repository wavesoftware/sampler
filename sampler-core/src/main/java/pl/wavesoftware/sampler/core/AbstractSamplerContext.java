/*
 * Copyright (c) 2019 Wave Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.wavesoftware.sampler.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wavesoftware.sampler.api.RandomSource;
import pl.wavesoftware.sampler.api.Sampler;
import pl.wavesoftware.sampler.api.SamplerContext;
import pl.wavesoftware.sampler.api.SamplerControl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * An abstract implementation of {@link SamplerContext}.
 * <p>
 *     Subclasses should implement {@link #getSampler(Class)} method.
 * </p>
 * @see SamplerContext
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 1.0.0
 */
public abstract class AbstractSamplerContext implements SamplerContext {

  private static final Logger LOGGER =
    LoggerFactory.getLogger(AbstractSamplerContext.class);
  private final Map<Class<? extends Sampler<?>>, Object> samples =
    Collections.synchronizedMap(new HashMap<>());
  private final ResolutionContext resolutionContext;
  private final SamplerControl control;

  public AbstractSamplerContext() {
    this(new DefaultRandomSource());
  }

  public AbstractSamplerContext(RandomSource randomSource) {
    this(randomSource, new DefaultSamplerControl(randomSource));
  }

  public AbstractSamplerContext(RandomSource randomSource, SamplerControl control) {
    this.resolutionContext = new DefaultResolutionContext(
      samples::put,
      this::find,
      randomSource
    );
    this.control = control;
  }

  @Override
  public SamplerControl controller() {
    return control;
  }

  @Override
  public <T> T get(Class<? extends Sampler<T>> spec) {
    return resolve(spec, () -> createNew(spec));
  }

  @Override
  public <T> T createNew(Class<? extends Sampler<T>> spec) {
    T sample = getSampler(spec).create();
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug(
        "Created sample {} for spec {}",
        Integer.toHexString(sample.hashCode()), spec
      );
    }
    return sample;
  }

  @Override
  public void close() {
    samples.clear();
  }

  protected abstract <T> Sampler<T> getSampler(Class<? extends Sampler<T>> spec);

  private <T> Optional<T> find(Class<? extends Sampler<T>> spec) {
    if (samples.containsKey(spec)) {
      @SuppressWarnings("unchecked")
      T sample = (T) samples.get(spec);
      return Optional.of(sample);
    }
    return Optional.empty();
  }

  private <T> T resolve(Class<? extends Sampler<T>> spec, Supplier<T> creator) {
    try (ResolutionTransaction<T> transaction = resolutionContext.open(spec)) {
      return transaction.resolve(creator);
    }
  }
}

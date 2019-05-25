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
import pl.wavesoftware.sampler.api.Sampler;
import pl.wavesoftware.sampler.api.SamplerContext;

import java.util.HashMap;
import java.util.Map;

/**
 * An abstract implementation of {@link SamplerContext}.
 * <p>
 *     Subclasses should implement {@link #getSampler(Class)} method.
 * </p>
 * @see SamplerContext
 */
public abstract class AbstractSamplerContext
  implements SamplerContext, AutoCloseable {

  private static final Logger LOGGER =
    LoggerFactory.getLogger(AbstractSamplerContext.class);
  private final Map<Class<? extends Sampler<?>>, Object> samples =
    new HashMap<>();

  @Override
  public <T> T get(Class<? extends Sampler<T>> spec) {
    @SuppressWarnings("unchecked")
    T val = (T) samples.computeIfAbsent(spec, ignored -> createNew(spec));
    return val;
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
}

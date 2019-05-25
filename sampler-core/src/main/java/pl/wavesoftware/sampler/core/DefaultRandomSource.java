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

import io.vavr.Lazy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wavesoftware.sampler.api.EnvironmentResolver;
import pl.wavesoftware.sampler.api.RandomSource;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

/**
 * <p>
 * An class that serves as a default implementation {@link RandomSource}.
 * </p>
 * <p>
 *     Requires an instance of {@link EnvironmentResolver} to resolve
 *     environmental variables.
 * </p>
 * @see EnvironmentResolver
 * @see RandomSource
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 1.0.0
 */
public class DefaultRandomSource implements RandomSource {
  private static final Logger LOGGER =
    LoggerFactory.getLogger(DefaultRandomSource.class);
  private static final String SAMPLER_SEED_PROPERTY = "sampler.seed";

  private final RandomSeedSupplier seedSupplier = new RandomSeedSupplier();
  private final Lazy<Random> randomLazy = Lazy.of(this::doGet);
  private final EnvironmentResolver environmentResolver;

  protected DefaultRandomSource(EnvironmentResolver environmentResolver) {
    this.environmentResolver = environmentResolver;
  }

  @Override
  public Random get() {
    return randomLazy.get();
  }

  private Random doGet() {
    String seed = environmentResolver.resolveProperty(
      SAMPLER_SEED_PROPERTY,
      seedSupplier
    );
    LOGGER.info(
      "Using seed: {}. To re-execute same exact case, set " +
        "environmental variable `export SAMPLER_SEED={}` or Java's " +
        "property `-D{}={}` with that seed value.",
      seed, seed, SAMPLER_SEED_PROPERTY, seed
    );
    return new SecureRandom(seed.getBytes(StandardCharsets.UTF_8));
  }
}

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
import pl.wavesoftware.sampler.api.SamplerControl;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * A default implementation of {@link SamplerControl} using
 * {@link RandomSource}.
 *
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 1.0.0
 */
public final class DefaultSamplerControl implements SamplerControl {

  private static final Logger LOGGER =
    LoggerFactory.getLogger(DefaultSamplerControl.class);

  @Nullable
  private UUID id;
  private final RandomSource randomSource;

  public DefaultSamplerControl(RandomSource randomSource) {
    this.randomSource = randomSource;
  }

  @Override
  public UUID actualId() {
    return ensureId();
  }

  @Override
  public void newId() {
    setId(newUuid());
  }

  @Override
  public synchronized void setId(UUID id) {
    LOGGER.info("Sampler ID: {}", id);
    this.id = id;
  }

  private UUID newUuid() {
    return new UUID(randomSource.nextLong(), randomSource.nextLong());
  }

  private UUID ensureId() {
    if (id == null) {
      newId();
    }
    return id;
  }

}

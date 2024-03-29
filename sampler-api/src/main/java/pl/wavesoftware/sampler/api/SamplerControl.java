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

package pl.wavesoftware.sampler.api;

import java.util.Random;
import java.util.UUID;

/**
 * User can you this interface to control the scope of a tests
 *
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 1.0.0
 */
public interface SamplerControl {
  /**
   * Gets actual ID of sampler scope
   *
   * @return an ID
   */
  UUID actualId();

  /**
   * Sets new generated ID for sampler scope
   */
  void newId();

  /**
   * Set a given ID as a sampler scope ID
   * @param id and ID
   */
  void setId(UUID id);

  /**
   * Creates a predictable Random instance that can be used to generate
   * randomized samples.
   * @return a new random instance
   */
  Random random();
}

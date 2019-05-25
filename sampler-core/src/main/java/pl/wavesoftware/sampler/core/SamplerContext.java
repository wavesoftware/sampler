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

public interface SamplerContext {
  /**
   * Gets a created sample or create one if it wasn't created before
   *
   * @param spec a spec to create a sample
   * @param <T> a type of sample to create
   * @return a sample
   */
  <T> T get(Class<? extends Sampler<T>> spec);

  /**
   * Creates a new sample, without caching it in {@link SamplerContext}.
   *
   * @param spec a spec to create a sample
   * @param <T> a type of sample to create
   * @return a sample
   */
  <T> T createNew(Class<? extends Sampler<T>> spec);

  /**
   * Gets a controller for this sampler context
   *
   * @return a controller for sampler context
   */
  SamplerControl controller();
}

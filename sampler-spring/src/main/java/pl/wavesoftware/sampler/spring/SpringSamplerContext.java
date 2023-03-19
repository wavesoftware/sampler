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

package pl.wavesoftware.sampler.spring;

import org.springframework.context.ApplicationContext;
import pl.wavesoftware.sampler.api.RandomSource;
import pl.wavesoftware.sampler.api.Sampler;
import pl.wavesoftware.sampler.api.SamplerControl;
import pl.wavesoftware.sampler.core.AbstractSamplerContext;

class SpringSamplerContext extends AbstractSamplerContext {

  private final ApplicationContext context;

  SpringSamplerContext(
    ApplicationContext context,
    SamplerControl control,
    RandomSource randomSource
  ) {
    super(randomSource, control);
    this.context = context;
  }

  @Override
  protected <T> Sampler<T> getSampler(Class<? extends Sampler<T>> spec) {
    return context.getBean(spec);
  }
}

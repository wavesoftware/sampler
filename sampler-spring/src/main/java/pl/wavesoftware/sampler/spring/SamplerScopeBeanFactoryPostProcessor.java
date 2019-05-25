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

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

final class SamplerScopeBeanFactoryPostProcessor
  implements BeanFactoryPostProcessor {

  private final SamplerScope samplerScope;

  SamplerScopeBeanFactoryPostProcessor(SamplerScope samplerScope) {
    this.samplerScope = samplerScope;
  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory factory) {
    factory.registerScope(SamplerScope.SAMPLE_SCOPE, samplerScope);
  }
}

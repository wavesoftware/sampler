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

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import pl.wavesoftware.sampler.core.DefaultSamplerControl;
import pl.wavesoftware.sampler.api.RandomSource;
import pl.wavesoftware.sampler.api.SamplerContext;
import pl.wavesoftware.sampler.api.SamplerControl;

/**
 * This is a Spring Boot style auto-configuration class. It can be used also
 * without Spring Boot with {@link Import} annotation.
 */
@ConditionalOnMissingBean(SamplerContext.class)
public class SpringSamplerAutoConfiguration {
  @Bean
  @ConditionalOnMissingBean
  RandomSource samplerRandomSource(Environment environment) {
    return new SpringRandomSource(environment);
  }

  @Bean
  static SamplerScopeBeanFactoryPostProcessor samplerScopeBeanFactoryPostProcessor(SamplerScope scope){
    return new SamplerScopeBeanFactoryPostProcessor(scope);
  }

  @Bean
  @ConditionalOnMissingBean
  SamplerControl samplerControl(RandomSource randomSource) {
    return new DefaultSamplerControl(randomSource);
  }

  @Bean
  SamplerScope samplerScope(SamplerControl samplerControl) {
    return new SamplerScope(samplerControl);
  }

  @Bean
  @Scope(
    value = SamplerScope.SAMPLE_SCOPE,
    proxyMode = ScopedProxyMode.INTERFACES
  )
  SamplerContext samplerContext(
    ApplicationContext applicationContext,
    SamplerControl samplerControl,
    RandomSource randomSource
  ) {
    return new SpringSamplerContext(applicationContext, samplerControl, randomSource);
  }
}

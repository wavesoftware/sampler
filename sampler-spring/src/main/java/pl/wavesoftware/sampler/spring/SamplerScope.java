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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import pl.wavesoftware.sampler.api.SamplerControl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.vavr.collection.HashMap.ofAll;

final class SamplerScope implements Scope, DisposableBean {
  static final String SAMPLE_SCOPE = "sample";

  private static final Logger LOGGER =
    LoggerFactory.getLogger(SamplerScope.class);

  private final Map<UUID, SamplerScopeRegistry> registries =
    Collections.synchronizedMap(new HashMap<>());
  private final SamplerControl samplerControl;

  SamplerScope(SamplerControl samplerControl) {
    this.samplerControl = samplerControl;
  }

  @Override
  public Object get(String name, ObjectFactory<?> objectFactory) {
    return getRegistry().get(name, objectFactory);
  }

  @Override
  public Object remove(String name) {
    return getRegistry().remove(name);
  }

  @Override
  public void registerDestructionCallback(String name, Runnable runnable) {
    getRegistry().registerDestructionCallback(name, runnable);
  }

  @Override
  public Object resolveContextualObject(String key) {
    return null;
  }

  @Override
  public String getConversationId() {
    return samplerControl.actualId().toString();
  }

  private SamplerScopeRegistry getRegistry() {
    return registries.computeIfAbsent(
      samplerControl.actualId(), ignored -> new SamplerScopeRegistry()
    );
  }

  @Override
  public void destroy() {
    ofAll(registries).forEach(SamplerScope::destroyRegistry);
  }

  private static void destroyRegistry(UUID id, SamplerScopeRegistry registry) {
    LOGGER.info("Closing sampler scope: {}", id);
    registry.destroy();
  }
}

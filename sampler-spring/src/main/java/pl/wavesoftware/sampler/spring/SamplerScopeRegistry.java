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

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.ObjectFactory;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static pl.wavesoftware.eid.utils.EidExecutions.tryToExecute;

final class SamplerScopeRegistry implements DisposableBean {

  private final Map<String, Object> objects =
    Collections.synchronizedMap(new HashMap<>());
  private final Map<String, Runnable> destructionCallbacks =
    Collections.synchronizedMap(new HashMap<>());

  Object get(String name, ObjectFactory<?> objectFactory) {
    return objects.computeIfAbsent(name, ignored -> objectFactory.getObject());
  }

  @Nullable
  Object remove(String name) {
    Object bean = objects.remove(name);
    if (bean != null && destructionCallbacks.containsKey(name)) {
      Runnable callback = destructionCallbacks.remove(name);
      callback.run();
    }
    return bean;
  }

  void registerDestructionCallback(String name, Runnable runnable) {
    destructionCallbacks.put(name, runnable);
  }

  @Override
  public void destroy() {
    for (Map.Entry<String, Runnable> entry : destructionCallbacks.entrySet()) {
      tryToExecute(() -> entry.getValue().run(), "20190527:213218");
    }
  }
}

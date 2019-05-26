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

import java.util.function.Supplier;

/**
 * Resolves a property from environment, either from Java style properties
 * (<code>-Dsome.property=value</code>), or environmental variables
 * (<code>export SOME_PROPERTY=value</code>).
 *
 * <p>
 *     Implementations should resolve properties, trying to change their
 *     form if not found at base. For ex.: if user pass property
 *     <code>some.property</code>, this implementation should try to resolve:
 * </p>
 * <ul>
 *     <li><code>System.getProperty("some.property")</code></li>
 *     <li><code>System.getenv("SOME_PROPERTY")</code></li>
 * </ul>
 *
 * 
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 1.0.0
 */
public interface EnvironmentResolver {
  /**
   * Resolves a property from environment. Returns default value, if it can't
   * resolve a given property.
   *
   * @param property a property name, never {@code null}
   * @param defaultValueSupplier a supplier to use to determine default
   *                             value, if property isn't set in environment,
   *                             never {@code null}
   * @return a resolved property
   */
  String resolveProperty(String property, Supplier<String> defaultValueSupplier);
}

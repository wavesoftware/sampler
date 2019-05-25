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

import java.util.Random;
import java.util.function.Supplier;

final class RandomSeedSupplier implements Supplier<String> {

  private static final int BASE36 = 36;
  private static final int MIN = 60_466_176;
  private static final Random RANDOM = new Random();

  @Override
  public String get() {
    int calc = RANDOM.nextInt(Integer.MAX_VALUE - MIN) + MIN;
    return Integer.toString(calc, BASE36);
  }
}

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
import java.util.function.Supplier;

/**
 * A source of a {@link Random randomness} to be used in samples randomization.
 * Implementations should provide a deterministic way of providing Random
 * values, so execution can be rerun if errors ware found.
 *
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 1.0.0
 */
public interface RandomSource {

  /**
   * Returns the next pseudorandom, uniformly distributed {@code long}
   * value from this random number generator's sequence. The general
   * contract of {@code nextLong} is that one {@code long} value is
   * pseudorandomly generated and returned.
   *
   * <p>The method {@code nextLong} is implemented by class {@code Random}
   * as if by:
   *  <pre> {@code
   * public long nextLong() {
   *   return ((long)next(32) << 32) + next(32);
   * }}</pre>
   *
   * Because class {@code Random} uses a seed with only 48 bits,
   * this algorithm will not return all possible {@code long} values.
   *
   * @return the next pseudorandom, uniformly distributed {@code long}
   *         value from this random number generator's sequence
   */
  long nextLong();
}

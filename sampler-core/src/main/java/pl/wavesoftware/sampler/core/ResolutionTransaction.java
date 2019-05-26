package pl.wavesoftware.sampler.core;

import java.util.function.Supplier;

/**
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 1.0.0
 */
interface ResolutionTransaction<T> extends AutoCloseable {
  CharSequence id();
  T resolve(Supplier<T> creator);

  @Override
  void close();
}

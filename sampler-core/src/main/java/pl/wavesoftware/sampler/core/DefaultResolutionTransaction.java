package pl.wavesoftware.sampler.core;

import pl.wavesoftware.sampler.api.RandomSource;
import pl.wavesoftware.sampler.api.Sampler;

import java.util.Optional;
import java.util.function.Supplier;

import static java.lang.Long.toHexString;

/**
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 1.0.0
 */
final class DefaultResolutionTransaction<T> implements ResolutionTransaction<T> {
  private final Class<? extends Sampler<T>> spec;
  private final SamplePersister persister;
  private final SampleFinder finder;
  private final Runnable onCloseHandler;
  private final CharSequence id;

  DefaultResolutionTransaction(
    Class<? extends Sampler<T>> spec,
    SamplePersister persister,
    SampleFinder finder,
    Runnable onCloseHandler,
    RandomSource randomSource
  ) {
    this.spec = spec;
    this.persister = persister;
    this.finder = finder;
    this.onCloseHandler = onCloseHandler;
    id = toHexString(randomSource.nextLong());
  }

  @Override
  public CharSequence id() {
    return id;
  }

  @Override
  public T resolve(Supplier<T> creator) {
    Optional<T> maybeSample = finder.find(spec);
    if (maybeSample.isPresent()) {
      return maybeSample.get();
    }
    T sample = creator.get();
    persister.persist(spec, sample);
    return sample;
  }

  @Override
  public void close() {
    onCloseHandler.run();
  }
}

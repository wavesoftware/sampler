package pl.wavesoftware.sampler.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wavesoftware.sampler.api.RandomSource;
import pl.wavesoftware.sampler.api.Sampler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 1.0.0
 */
final class DefaultResolutionContext implements ResolutionContext {
  private static final Logger LOGGER =
    LoggerFactory.getLogger(DefaultResolutionContext.class);
  private final SamplePersister persister;
  private final SampleFinder finder;
  private final ThreadLocal<Map<Class<? extends Sampler<?>>,
    ResolutionTransaction<?>>> openedTransactions =
    ThreadLocal.withInitial(HashMap::new);
  private final RandomSource randomSource;

  DefaultResolutionContext(
    SamplePersister persister,
    SampleFinder finder,
    RandomSource randomSource
  ) {
    this.persister = persister;
    this.finder = finder;
    this.randomSource = randomSource;
  }

  @Override
  public <T> ResolutionTransaction<T> open(Class<? extends Sampler<T>> spec) {
    if (openedTransactions.get().containsKey(spec)) {
      throw new CycleWhileResolution(spec);
    }
    return startTransaction(spec);
  }

  private <T> ResolutionTransaction<T> startTransaction(
    Class<? extends Sampler<T>> spec
  ) {
    ResolutionTransaction<T> transaction = new DefaultResolutionTransaction<>(
      spec, persister, finder, () -> finishTransaction(spec), randomSource
    );
    LOGGER.trace("Starting transaction {} for spec {}", transaction.id(), spec);
    openedTransactions.get().put(spec, transaction);
    return transaction;
  }

  private <T> void finishTransaction(Class<? extends Sampler<T>> spec) {
    ResolutionTransaction<?> transaction = openedTransactions.get().remove(spec);
    LOGGER.trace("Transaction complete {} for spec {}", transaction.id(), spec);
  }

}

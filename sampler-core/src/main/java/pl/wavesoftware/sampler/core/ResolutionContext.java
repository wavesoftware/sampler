package pl.wavesoftware.sampler.core;

import pl.wavesoftware.sampler.api.Sampler;

/**
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 1.0.0
 */
interface ResolutionContext {
  <T> ResolutionTransaction<T> open(Class<? extends Sampler<T>> spec);
}

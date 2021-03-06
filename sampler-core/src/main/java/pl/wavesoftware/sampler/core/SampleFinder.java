package pl.wavesoftware.sampler.core;

import pl.wavesoftware.sampler.api.Sampler;

import java.util.Optional;

/**
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 1.0.0
 */
interface SampleFinder {
  <T> Optional<T> find(Class<? extends Sampler<T>> spec);
}

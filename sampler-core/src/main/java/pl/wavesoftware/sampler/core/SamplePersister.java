package pl.wavesoftware.sampler.core;

import pl.wavesoftware.sampler.api.Sampler;

/**
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 1.0.0
 */
interface SamplePersister {
  <T> void persist(Class<? extends Sampler<T>> spec, T sample);
}

package pl.wavesoftware.sampler.core;

import org.slf4j.helpers.MessageFormatter;
import pl.wavesoftware.eid.exceptions.EidIllegalStateException;
import pl.wavesoftware.sampler.api.Sampler;

/**
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 1.0.0
 */
final class CycleWhileResolution extends EidIllegalStateException {
  private static final long serialVersionUID = 20190526222843L;
  
  CycleWhileResolution(Class<? extends Sampler<?>> spec) {
    super("20190526:113818", MessageFormatter.format(
      "Cycle detected while resolving sample for spec {}",
      spec
    ).getMessage());
  }
}

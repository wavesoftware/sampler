package pl.wavesoftware.sampler.spring.example.group;

import pl.wavesoftware.sampler.spring.Sample;
import pl.wavesoftware.sampler.spring.example.model.Group;

/**
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 0.1.0
 */
@Sample
final class AdminSampler implements Admin {
  @Override
  public Group create() {
    return new Group("adm");
  }
}

package pl.wavesoftware.sampler.example.sample.group;

import pl.wavesoftware.sampler.example.model.Group;

/**
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 0.1.0
 */
final class AdminSampler implements Admin {
  @Override
  public Group create() {
    return new Group("adm");
  }
}

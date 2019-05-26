package pl.wavesoftware.sampler.example.sample.user;

import lombok.RequiredArgsConstructor;
import pl.wavesoftware.sampler.api.RandomSource;
import pl.wavesoftware.sampler.api.SamplerContext;
import pl.wavesoftware.sampler.example.model.User;
import pl.wavesoftware.sampler.example.sample.group.Admin;
import pl.wavesoftware.sampler.example.sample.group.RemoteDesktop;

/**
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 0.1.0
 */
@RequiredArgsConstructor
final class JohnDoeSampler implements JohnDoe {
  private final SamplerContext samplerContext;
  private final RandomSource randomSource;

  @Override
  public User create() {
    User user = new User();
    user.setId(randomSource.nextLong());
    user.setName("John");
    user.addGroup(samplerContext.get(Admin.class));
    user.addGroup(samplerContext.get(RemoteDesktop.class));
    return user;
  }
}

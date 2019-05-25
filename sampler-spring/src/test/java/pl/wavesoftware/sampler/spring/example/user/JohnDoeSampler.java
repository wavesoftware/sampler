package pl.wavesoftware.sampler.spring.example.user;

import lombok.RequiredArgsConstructor;
import pl.wavesoftware.sampler.core.RandomSource;
import pl.wavesoftware.sampler.core.SamplerContext;
import pl.wavesoftware.sampler.spring.Sample;
import pl.wavesoftware.sampler.spring.example.group.Admin;
import pl.wavesoftware.sampler.spring.example.group.RemoteDesktop;
import pl.wavesoftware.sampler.spring.example.model.User;

/**
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 0.1.0
 */
@Sample
@RequiredArgsConstructor
final class JohnDoeSampler implements JohnDoe {
  private final SamplerContext samplerContext;
  private final RandomSource randomSource;

  @Override
  public User create() {
    User user = new User();
    user.setId(randomSource.get().nextLong());
    user.setName("John");
    user.addGroup(samplerContext.get(Admin.class));
    user.addGroup(samplerContext.get(RemoteDesktop.class));
    return user;
  }
}

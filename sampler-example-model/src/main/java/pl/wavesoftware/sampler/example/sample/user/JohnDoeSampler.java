package pl.wavesoftware.sampler.example.sample.user;

import io.vavr.collection.HashSet;
import lombok.RequiredArgsConstructor;
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
  private final SamplerContext ctx;

  @Override
  public User create() {
    return User.builder()
      .id(ctx.controller().random().nextLong())
      .name("John")
      .groups(HashSet.of(
        ctx.get(Admin.class),
        ctx.get(RemoteDesktop.class)
      ).toJavaSet()).build();
  }
}

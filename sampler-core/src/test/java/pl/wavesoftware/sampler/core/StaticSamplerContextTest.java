package pl.wavesoftware.sampler.core;

import io.vavr.collection.HashMap;
import org.junit.jupiter.api.Test;
import pl.wavesoftware.sampler.api.Sampler;
import pl.wavesoftware.sampler.api.SamplerContext;
import pl.wavesoftware.sampler.example.model.User;
import pl.wavesoftware.sampler.example.sample.user.JohnDoe;

import static org.assertj.core.api.Assertions.assertThat;

class StaticSamplerContextTest {
  @Test
  void staticSamplers() {
    try (SamplerContext ctx = new StaticSamplerContext(
      HashMap.of(JohnDoe.class, (Sampler<User>) () -> User.builder()
        .id(43L)
        .name("John Doe")
        .build()
      )
    )) {
      // when
      User user1 = ctx.get(JohnDoe.class);
      User user2 = ctx.get(JohnDoe.class);

      // then
      assertThat(user1).isNotNull();
      assertThat(user1).isSameAs(user2);
    }
  }
}

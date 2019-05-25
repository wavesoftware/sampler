package pl.wavesoftware.sampler.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.wavesoftware.sampler.core.SamplerContext;
import pl.wavesoftware.sampler.spring.example.UserModelSamplesContext;
import pl.wavesoftware.sampler.spring.example.model.Group;
import pl.wavesoftware.sampler.spring.example.model.User;
import pl.wavesoftware.sampler.spring.example.user.JohnDoe;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 1.0.0
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserModelSamplesContext.class)
class SamplerContextTest {

  @Autowired
  private SamplerContext samplerContext;

  @Test
  void get() {
    // when
    User user = samplerContext.get(JohnDoe.class);

    // then
    assertThat(user).isNotNull();
    assertThat(user.getGroups())
      .extracting(Group::getName)
      .contains("adm", "remote-desktop");
  }

  @Test
  void differentScopes() {
    // when
    User user1 = samplerContext.get(JohnDoe.class);
    User user2 = samplerContext.get(JohnDoe.class);
    UUID firstId = samplerContext.controller().actualId();
    samplerContext.controller().newId();
    User user3 = samplerContext.get(JohnDoe.class);
    User user4 = samplerContext.get(JohnDoe.class);
    UUID secondId = samplerContext.controller().actualId();
    samplerContext.controller().setId(firstId);
    User user5 = samplerContext.get(JohnDoe.class);
    User user6 = samplerContext.get(JohnDoe.class);

    // then
    assertThat(user1).isNotNull().isSameAs(user2);
    assertThat(user3).isNotNull().isSameAs(user4);
    assertThat(user3).isNotSameAs(user1);
    assertThat(user5).isNotNull().isSameAs(user6).isSameAs(user1);
    assertThat(firstId).isNotNull().isNotEqualTo(secondId);
    assertThat(secondId).isNotNull();
  }
}

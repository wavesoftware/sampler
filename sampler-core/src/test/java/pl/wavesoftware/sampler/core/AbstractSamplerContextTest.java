package pl.wavesoftware.sampler.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.wavesoftware.sampler.api.RandomSource;
import pl.wavesoftware.sampler.api.Sampler;
import pl.wavesoftware.sampler.api.SamplerControl;
import pl.wavesoftware.sampler.example.model.User;
import pl.wavesoftware.sampler.example.sample.user.JohnDoe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
class AbstractSamplerContextTest {

  @Mock
  private SamplerControl samplerControl;

  @Mock
  private Sampler<User> userSampler;

  @Mock
  private User userSample;

  @Mock
  private RandomSource randomSource;

  @AfterEach
  void after() {
    Mockito.validateMockitoUsage();
    Mockito.verifyNoMoreInteractions(
      samplerControl,
      userSample,
      userSampler,
      randomSource
    );
  }

  @Test
  @SuppressWarnings("unchecked")
  void get() {
    // given
    when(userSampler.create()).thenReturn(userSample);
    when(randomSource.nextLong()).thenReturn(1L, 2L);
    try(AbstractSamplerContext ctx = new AbstractSamplerContext(randomSource) {

      @Override
      public SamplerControl controller() {
        return samplerControl;
      }

      @Override
      protected <T> Sampler<T> getSampler(Class<? extends Sampler<T>> spec) {
        return (Sampler<T>) userSampler;
      }
    }) {
      // when
      User user1 = ctx.get(JohnDoe.class);
      User user2 = ctx.get(JohnDoe.class);

      // then
      assertThat(user1).isNotNull();
      assertThat(user1).isSameAs(user2);
    }

    verify(randomSource, times(2)).nextLong();
  }
}

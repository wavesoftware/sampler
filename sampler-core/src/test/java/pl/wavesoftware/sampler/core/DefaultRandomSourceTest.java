package pl.wavesoftware.sampler.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.wavesoftware.sampler.api.EnvironmentResolver;
import pl.wavesoftware.sampler.api.RandomSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class DefaultRandomSourceTest {

  @Nested
  @ExtendWith(MockitoExtension.class)
  class OnGivenSeed {

    @Mock
    private EnvironmentResolver resolver;

    @AfterEach
    void after() {
      Mockito.validateMockitoUsage();
      Mockito.verifyNoMoreInteractions(resolver);
    }

    @Test
    void nextLong() {
      // given
      RandomSource randomSource = new DefaultRandomSource(resolver);
      when(resolver.resolveProperty(eq("sampler.seed"), any()))
        .thenReturn("qwerty");

      // when
      long value = randomSource.nextLong();

      // then
      assertThat(value).isEqualTo(2_758_613_504_035_412_277L);
    }
  }

  @Nested
  class OnGeneratedSeed {
    @Test
    void nextLong() {
      // given
      EnvironmentResolver resolver =
        (property, defaultValueSupplier) -> defaultValueSupplier.get();
      RandomSource randomSource = new DefaultRandomSource(resolver);

      // when
      long value1 = randomSource.nextLong();
      long value2 = randomSource.nextLong();

      // then
      assertThat(value1).isNotEqualTo(value2);
    }
  }

}

package pl.wavesoftware.sampler.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.wavesoftware.sampler.api.RandomSource;

import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DefaultSamplerControlTest {

  private final Random random = new Random(123456);
  private final RandomSource randomSource = random::nextLong;

  @Test
  void actualId() {
    // given
    DefaultSamplerControl control = new DefaultSamplerControl(randomSource);

    // when
    UUID id = control.actualId();

    // then
    assertThat(id).isNotNull();
    assertThat(id).isEqualByComparingTo(UUID.fromString(
      "69c8bc26-e1cf-7a6e-fd8b-cbd67852d8ca"
    ));
  }

  @Test
  void newId() {
    // given
    DefaultSamplerControl control = new DefaultSamplerControl(randomSource);
    assertThat(control.actualId()).isNotNull();

    // when
    control.newId();
    UUID id = control.actualId();

    // then
    assertThat(id).isEqualByComparingTo(UUID.fromString(
      "4093d00d-11ca-bf7f-b4a6-5f134e72c441"
    ));
  }

  @Test
  void setId() {
    // given
    DefaultSamplerControl control = new DefaultSamplerControl(randomSource);
    UUID id = UUID.fromString(
      "11111111-1111-1111-1111-111111111111"
    );

    // when
    control.setId(id);

    // then
    assertThat(control.actualId()).isEqualByComparingTo(UUID.fromString(
      "11111111-1111-1111-1111-111111111111"
    ));
  }
}

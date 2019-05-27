package pl.wavesoftware.sampler.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomSeedSupplierTest {

  @Test
  void get() {
    // given
    RandomSeedSupplier supplier = new RandomSeedSupplier();

    // when
    String seed = supplier.get();

    // then
    assertThat(seed).matches("^[0-9a-z]{6}$");
  }
}

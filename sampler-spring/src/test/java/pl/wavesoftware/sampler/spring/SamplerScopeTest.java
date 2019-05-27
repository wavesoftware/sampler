package pl.wavesoftware.sampler.spring;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.wavesoftware.sampler.api.SamplerControl;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
class SamplerScopeTest {

  private static final String ALICE_BEAN_NAME = "alice";
  private static final UUID ID = UUID.fromString(
    "1111111-2222-3333-4444-555555555555"
  );

  @Mock
  private SamplerControl control;

  @AfterEach
  void after() {
    Mockito.validateMockitoUsage();
    Mockito.verifyNoMoreInteractions(control);
  }

  @Test
  void remove() {
    // given
    when(control.actualId()).thenReturn(ID);
    SamplerScope scope = new SamplerScope(control);
    AtomicBoolean runned = new AtomicBoolean(false);
    Runnable aliceObject =
      () -> runned.compareAndSet(false, true);
    Object result = scope.get(ALICE_BEAN_NAME, () -> aliceObject);
    scope.registerDestructionCallback(ALICE_BEAN_NAME, aliceObject);

    // when
    Object removed = scope.remove(ALICE_BEAN_NAME);

    // then
    assertThat(removed).isSameAs(result);
    assertThat(runned).isTrue();
  }

  @Test
  void resolveContextualObject() {
    // given
    SamplerScope scope = new SamplerScope(control);

    // when
    Object result = scope.resolveContextualObject(ALICE_BEAN_NAME);

    // then
    assertThat(result).isNull();
  }

  @Test
  void getConversationId() {
    // given
    when(control.actualId()).thenReturn(ID);
    SamplerScope scope = new SamplerScope(control);

    // when
    String id = scope.getConversationId();

    // then
    assertThat(id).isEqualTo(ID.toString());
  }
}

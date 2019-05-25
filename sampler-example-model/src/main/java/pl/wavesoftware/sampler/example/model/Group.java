package pl.wavesoftware.sampler.example.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 0.1.0
 */
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Group {
  private final String name;
}

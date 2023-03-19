package pl.wavesoftware.sampler.example.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:krzysztof.suszynski@wavesoftware.pl">Krzysztof Suszynski</a>
 * @since 1.0.0
 */
@Setter
@Getter
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "name"})
public class User {
  private Long id;
  private String name;
  private Set<Group> groups;

  public void addGroup(Group group) {
    groups.add(group);
  }

  public Set<Group> getGroups() {
    return new HashSet<>(groups);
  }
}

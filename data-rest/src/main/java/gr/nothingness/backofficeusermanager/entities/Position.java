package gr.nothingness.backofficeusermanager.entities;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tadminposition")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Position {

  @Column(name = "position_id")
  @Id @GeneratedValue(strategy = IDENTITY)
  @Getter @Setter private String id;

  @Column(name = "position_name")
  @NotNull @Size(max = 64)
  @Getter @Setter private String name;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "parent_position_id")
  @Getter @Setter private Position parentPosition;

  @OneToMany(mappedBy = "parentPosition")
  @Getter @Setter private Set<Position> childPositions;

  @OneToMany(mappedBy = "position")
  @JsonIgnore
  @Getter @Setter private Set<BackofficeUser> users;

  @ManyToMany
  @JoinTable(
      name = "tadminposngroup",
      joinColumns = @JoinColumn(name = "position_id"),
      inverseJoinColumns = @JoinColumn(name = "group_id")
  )
  @Getter @Setter private Set<BackofficeGroup> groups;

  @PreRemove
  private void disassociate() {
    users.forEach(user -> user.setPosition(null));
    childPositions.forEach(position -> position.setParentPosition(null));
  }

  public void removeGroup(BackofficeGroup group) {
    groups.remove(group);
  }

}

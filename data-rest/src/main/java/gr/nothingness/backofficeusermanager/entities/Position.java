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
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "tadminposition")
@NoArgsConstructor @Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Position {

  @Column(name = "position_id")
  @Id @GeneratedValue(strategy = IDENTITY)
  private String id;

  @Column(name = "position_name")
  @NaturalId(mutable = true)
  @NotNull @Size(max = 64)
  private String name;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "parent_position_id")
  private Position parentPosition;

  @OneToMany(mappedBy = "parentPosition")
  private Set<Position> childPositions;

  @OneToMany(mappedBy = "position")
  @JsonIgnore
  private Set<BackofficeUser> users;

  @ManyToMany
  @JoinTable(
      name = "tadminposngroup",
      joinColumns = @JoinColumn(name = "position_id"),
      inverseJoinColumns = @JoinColumn(name = "group_id")
  )
  private Set<BackofficeGroup> groups;

  @PreRemove
  private void disassociate() {
    users.forEach(BackofficeUser::removePosition);
    childPositions.forEach(Position::removeParentPosition);
  }

  private void removeParentPosition() {
    parentPosition = null;
  }

  public void removeGroup(BackofficeGroup group) {
    groups.remove(group);
  }

}

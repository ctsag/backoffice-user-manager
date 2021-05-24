package gr.nothingness.backofficeusermanager.entities;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.TIMESTAMP;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "tadmingroup")
@NoArgsConstructor @Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BackofficeGroup {

  @Column(name = "group_id")
  @Id @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(name = "group_name", unique = true)
  @NaturalId(mutable = true)
  @NotNull @Size(min = 1, max = 64)
  private String name;

  @Column(name = "cr_date")
  @JsonIgnore @NotNull @Temporal(TIMESTAMP)
  private Date creationDate = new Date();

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "group_owner")
  private BackofficeUser owner;

  @ManyToMany(mappedBy = "groups")
  @JsonIgnore
  private Set<Position> positions;

  @ManyToMany(mappedBy = "groups")
  @JsonIgnore
  private Set<BackofficeUser> users;

  @ManyToMany(fetch = LAZY)
  @JoinTable(
      name = "tadmingroupop",
      joinColumns = @JoinColumn(name = "group_id"),
      inverseJoinColumns = @JoinColumn(name = "action")
  )
  private Set<Permission> permissions;

  @ManyToMany(fetch = LAZY)
  @JoinTable(
      name = "tadmingroupjurisdiction",
      joinColumns = @JoinColumn(name = "group_id"),
      inverseJoinColumns = @JoinColumn(name = "jur_id")
  )
  private Set<Jurisdiction> jurisdictions;

  @PreRemove
  private void disassociate() {
    positions.forEach(position -> position.removeGroup(this));
    users.forEach(user -> user.removeGroup(this));
  }

  public void removeOwner() {
    owner = null;
  }

  public void removeJurisdiction(Jurisdiction jurisdiction) {
    jurisdictions.remove(jurisdiction);
  }

  public void removePermission(Permission permission) {
    permissions.remove(permission);
  }

}

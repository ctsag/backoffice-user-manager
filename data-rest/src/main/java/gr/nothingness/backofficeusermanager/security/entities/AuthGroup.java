package gr.nothingness.backofficeusermanager.security.entities;

import static javax.persistence.FetchType.EAGER;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tadmingroup")
@NoArgsConstructor @Getter @Setter
public class AuthGroup {

  @Column(name = "group_id")
  @Id
  private Long id;

  @Column(name = "group_name", unique = true)
  private String name;

  @ManyToMany(mappedBy = "groups")
  private Set<AuthUser> users;

  @ManyToMany(fetch = EAGER)
  @JoinTable(
      name = "tadmingroupop",
      joinColumns = @JoinColumn(name = "group_id"),
      inverseJoinColumns = @JoinColumn(name = "action")
  )
  private Set<AuthPermission> permissions;

}

package gr.nothingness.backofficeusermanager.security.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tadminop")
@NoArgsConstructor @Getter @Setter
public class AuthPermission {

  @Column(name = "action")
  @Id
  private String name;

  @ManyToMany(mappedBy = "permissions")
  private Set<AuthUser> users;

  @ManyToMany(mappedBy = "permissions")
  private Set<AuthGroup> groups;

}

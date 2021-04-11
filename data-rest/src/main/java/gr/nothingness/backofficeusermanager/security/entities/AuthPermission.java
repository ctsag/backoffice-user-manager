package gr.nothingness.backofficeusermanager.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@NoArgsConstructor
public class AuthPermission {

  @Column(name = "action")
  @Id
  @Getter @Setter private String name;

  @ManyToMany(mappedBy = "permissions")
  @Getter @Setter private Set<AuthUser> users;

  @ManyToMany(mappedBy = "permissions")
  @Getter @Setter private Set<AuthGroup> groups;

}

package gr.nothingness.backofficeusermanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tadminuser")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BackofficeUser {

  @Column(name = "user_id")
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter private Long id;

  @Column(name = "username")
  @Getter @Setter private String username;

  @Column(name = "password")
  @Getter @Setter private String password;

  @Column(name = "fname")
  @Getter @Setter private String firstName;

  @Column(name = "lname")
  @Getter @Setter private String lastName;

  @ManyToMany
  @JoinTable(
      name = "tadminuserop",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "action")
  )
  @Getter @Setter private List<Permission> permissions;

}

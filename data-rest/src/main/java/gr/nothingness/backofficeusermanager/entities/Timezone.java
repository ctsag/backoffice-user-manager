package gr.nothingness.backofficeusermanager.entities;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ttimezone")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Timezone {

  @RequiredArgsConstructor
  private enum Status {

    A ("Active"),
    S ("Suspended"),
    D ("Deleted");

    @Getter private final String description;

  }

  @RequiredArgsConstructor
  private enum YesNo {

    Y ("Yes"),
    N ("No");

    @Getter private final String description;

  }

  @Column(name = "timezone_id")
  @Id @GeneratedValue(strategy = IDENTITY)
  @Getter private Long id;

  @Column(name = "name")
  @NotNull @Size(max = 80)
  @Getter @Setter private String name;

  @Column(name = "status")
  @Enumerated(STRING)
  @Getter @Setter private Status status = Status.A;

  @Column(name = "display")
  @Enumerated(STRING)
  @Getter @Setter private YesNo display = YesNo.N;

  @OneToMany(mappedBy = "timezone")
  @JsonIgnore
  @Getter @Setter private Set<BackofficeUser> users;

  @PreRemove
  private void disassociate() {
    users.forEach(user -> user.setTimezone(null));
  }

}

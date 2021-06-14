package gr.nothingness.backofficeusermanager.model.entities;

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
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "ttimezone")
@NoArgsConstructor @Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Timezone {

  @RequiredArgsConstructor @Getter
  private enum Status {

    A ("Active"),
    S ("Suspended"),
    D ("Deleted");

    private final String description;

  }

  @RequiredArgsConstructor @Getter
  private enum YesNo {

    Y ("Yes"),
    N ("No");

    private final String description;

  }

  @Column(name = "timezone_id")
  @Id @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(name = "name")
  @NaturalId(mutable = true)
  @NotNull @Size(max = 80)
  private String name;

  @Column(name = "status")
  @Enumerated(STRING)
  private Status status = Status.A;

  @Column(name = "display")
  @Enumerated(STRING)
  private YesNo display = YesNo.N;

  @OneToMany(mappedBy = "timezone")
  @JsonIgnore
  private Set<BackofficeUser> users;

  @PreRemove
  private void disassociate() {
    users.forEach(BackofficeUser::removeTimezone);
  }

}

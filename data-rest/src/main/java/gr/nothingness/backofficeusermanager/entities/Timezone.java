package gr.nothingness.backofficeusermanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "ttimezone",
    indexes = {
        @Index(name = "itimezone_x1", columnList = "timezone_id", unique = true),
        @Index(name = "itimezone_x2", columnList = "name", unique = true)
    },
    uniqueConstraints = @UniqueConstraint(name = "ctimezone_u1", columnNames = "name")
)
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
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter private Long id;

  @Column(name = "name")
  @NotNull @Size(max = 80)
  @Getter @Setter private String name;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  @Getter @Setter private Status status = Status.A;

  @Column(name = "display")
  @Enumerated(EnumType.STRING)
  @Getter @Setter private YesNo display = YesNo.N;

  @OneToMany(mappedBy = "timezone")
  @JsonIgnore
  @Getter @Setter private List<BackofficeUser> users;

}

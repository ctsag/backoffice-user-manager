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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tadmingroup")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BackofficeGroup {

  @Column(name = "group_id")
  @Id @GeneratedValue(strategy = IDENTITY)
  @Getter private Long id;

  @Column(name = "group_name", unique = true)
  @NotNull @Size(min = 1, max = 64)
  @Getter @Setter private String name;

  @Column(name = "cr_date")
  @JsonIgnore @NotNull @Temporal(TIMESTAMP)
  @Getter @Setter private Date creationDate = new Date();

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "group_owner")
  @Getter @Setter private BackofficeUser owner;

  @ManyToMany(mappedBy = "groups")
  @JsonIgnore
  @Getter @Setter private Set<Position> positions;

  @ManyToMany(mappedBy = "groups")
  @JsonIgnore
  @Getter @Setter private Set<BackofficeUser> users;

  @ManyToMany(fetch = LAZY)
  @JoinTable(
      name = "tadmingroupop",
      joinColumns = @JoinColumn(name = "group_id"),
      inverseJoinColumns = @JoinColumn(name = "action")
  )
  @Getter @Setter private Set<Permission> permissions;

}

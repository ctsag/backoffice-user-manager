package gr.nothingness.backofficeusermanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter private Long id;

  @Column(name = "group_name", unique = true)
  @NotNull @Size(min = 1, max = 64)
  @Getter @Setter private String name;

  @Column(name = "cr_date")
  @JsonIgnore @NotNull @Temporal(TemporalType.TIMESTAMP)
  @Getter @Setter private Date creationDate = new Date();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "group_owner")
  @Getter @Setter private BackofficeUser owner;

  @ManyToMany(mappedBy = "groups")
  @JsonIgnore
  @Getter @Setter private List<Position> positions;

  @ManyToMany(mappedBy = "groups")
  @JsonIgnore
  @Getter @Setter private List<BackofficeUser> users;

  @ManyToMany
  @JoinTable(
      name = "tadmingroupop",
      joinColumns = @JoinColumn(name = "group_id"),
      inverseJoinColumns = @JoinColumn(name = "action")
  )
  @Getter @Setter private List<Permission> permissions;

}

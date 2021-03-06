package gr.nothingness.backofficeusermanager.model.entities;

import static javax.persistence.GenerationType.IDENTITY;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "tjurisdiction")
@NoArgsConstructor @Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Jurisdiction {

  @Column(name = "jur_id")
  @Id @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(name = "jurisdiction")
  @NaturalId(mutable = true)
  @NotNull @Size(max = 64)
  private String name;

  @ManyToMany(mappedBy = "jurisdictions")
  @JsonIgnore
  private Set<BackofficeGroup> groups;

  @PreRemove
  private void disassociate() {
    groups.forEach(group -> group.removeJurisdiction(this));
  }

}

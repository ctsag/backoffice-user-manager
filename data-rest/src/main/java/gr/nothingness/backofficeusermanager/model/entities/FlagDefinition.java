package gr.nothingness.backofficeusermanager.model.entities;

import static javax.persistence.CascadeType.ALL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tadminuserflagdesc")
@NoArgsConstructor @Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FlagDefinition implements Serializable {

  @Column(name = "flag_name")
  @Id @Size(max = 32)
  private String name;

  @Column(name = "description")
  @Size(max = 255)
  private String description;

  @Column(name = "note")
  @Size(max = 255)
  private String note;

  @Column(name = "default_val")
  @Size(max = 64)
  private String defaultValue;

  @OneToMany(mappedBy = "flagDefinition", cascade = ALL)
  private Set<FlagValue> flags;

}

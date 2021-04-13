package gr.nothingness.backofficeusermanager.entities;

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
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FlagDefinition implements Serializable {

  @Column(name = "flag_name")
  @Id @Size(max = 32)
  @Getter @Setter private String name;

  @Column(name = "description")
  @Size(max = 255)
  @Getter @Setter private String description;

  @Column(name = "note")
  @Size(max = 255)
  @Getter @Setter private String note;

  @Column(name = "default_val")
  @Size(max = 64)
  @Getter @Setter private String defaultValue;

  @OneToMany(mappedBy = "flagDefinition")
  @Getter @Setter private Set<FlagValue> flags;

}

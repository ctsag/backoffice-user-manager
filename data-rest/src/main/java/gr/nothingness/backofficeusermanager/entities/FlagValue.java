package gr.nothingness.backofficeusermanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gr.nothingness.backofficeusermanager.entities.compositekeys.FlagKey;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tadminuserflag")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FlagValue {

  @EmbeddedId
  @Getter @Setter private FlagKey id;

  @Column(name = "flag_value")
  @Size(max = 64)
  @Getter @Setter private String value;

  @ManyToOne
  @MapsId("user_id") @JoinColumn(name = "user_id")
  @Getter @Setter private BackofficeUser user;

  @ManyToOne
  @MapsId("flag_name") @JoinColumn(name = "flag_name")
  @Getter @Setter private FlagDefinition flagDefinition;

}

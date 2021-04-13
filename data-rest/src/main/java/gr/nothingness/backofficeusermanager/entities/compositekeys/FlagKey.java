package gr.nothingness.backofficeusermanager.entities.compositekeys;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class FlagKey implements Serializable {

  @Column(name = "user_id")
  @Getter @Setter private Long userId;

  @Column(name = "flag_name")
  @Getter @Setter private String flagName;

}

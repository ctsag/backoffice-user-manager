package gr.nothingness.backofficeusermanager.entities.compositekeys;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class FlagKey implements Serializable {

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "flag_name")
  private String flagName;

}

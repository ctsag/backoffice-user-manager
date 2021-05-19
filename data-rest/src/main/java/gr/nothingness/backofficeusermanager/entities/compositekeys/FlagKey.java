package gr.nothingness.backofficeusermanager.entities.compositekeys;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FlagKey implements Serializable {

  @Column(name = "user_id")
  @Getter @Setter private Long userId;

  @Column(name = "flag_name")
  @Getter @Setter private String flagName;

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }

    if (object == null || getClass() != object.getClass()) {
      return false;
    }

    FlagKey flagKey = (FlagKey) object;

    return userId.equals(flagKey.userId) && flagName.equals(flagKey.flagName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, flagName);
  }

}

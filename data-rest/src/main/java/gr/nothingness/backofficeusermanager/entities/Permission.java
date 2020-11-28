package gr.nothingness.backofficeusermanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tadminop")
@NoArgsConstructor
public class Permission {

  @Column(name = "action")
  @Id @Size(max = 64)
  @Getter @Setter private String name;

  @Column(name = "desc")
  @NotNull @Size(max = 80)
  @Getter @Setter private String description;

  @Column(name = "type")
  @NotNull @Size(max = 8)
  @Getter @Setter private String type = "GEN";

  @Column(name = "disporder")
  @Getter @Setter private Short displayOrder;

  @JsonIgnore
  @ManyToMany(mappedBy = "permissions")
  @Getter @Setter private List<BackofficeUser> users;

}

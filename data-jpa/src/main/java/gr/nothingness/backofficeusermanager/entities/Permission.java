package gr.nothingness.backofficeusermanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tadminop")
@NoArgsConstructor
public class Permission {

  @Column(name = "action")
  @Id
  @Getter @Setter private String name;

  @Column(name = "desc")
  @Getter @Setter private String description;

  @Column(name = "type")
  @Getter @Setter private String type;

  @Column(name = "disporder")
  @Getter @Setter private Integer displayOrder;

  @JsonIgnore
  @ManyToMany(mappedBy = "permissions")
  @Getter @Setter private List<BackofficeUser> users;

}


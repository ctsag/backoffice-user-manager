package gr.nothingness.backofficeusermanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tadminoptype")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PermissionType {

  @Column(name = "type")
  @Id @NotBlank @Size(max = 8)
  @Getter @Setter private String name;

  @Column(name = "desc")
  @NotNull @NotBlank @Size(max = 40)
  @Getter @Setter private String description;

  @Column(name = "disporder")
  @Getter @Setter private Short displayOrder;

  @OneToMany(mappedBy = "type")
  @JsonIgnore
  @Getter @Setter private List<Permission> permissions;

}

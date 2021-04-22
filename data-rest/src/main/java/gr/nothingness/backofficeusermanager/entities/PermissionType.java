package gr.nothingness.backofficeusermanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
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

  private static final String DEFAULT_TYPE_NAME = "GEN";
  private static final String DEFAULT_TYPE_DESCRIPTION = "Default permission type";

  @Column(name = "type")
  @Id @Size(min = 1, max = 8)
  @Getter @Setter private String name;

  @Column(name = "desc")
  @NotNull @Size(min = 1, max = 40)
  @Getter @Setter private String description;

  @Column(name = "disporder")
  @Getter @Setter private Short displayOrder;

  @OneToMany(mappedBy = "type")
  @JsonIgnore
  @Getter @Setter private Set<Permission> permissions;

  @PreRemove
  private void disassociatePermissions() {
    PermissionType permissionType = new PermissionType();

    permissionType.setName(DEFAULT_TYPE_NAME);
    permissionType.setDescription(DEFAULT_TYPE_DESCRIPTION);

    permissions.forEach(permission -> permission.setType(permissionType));
  }

}

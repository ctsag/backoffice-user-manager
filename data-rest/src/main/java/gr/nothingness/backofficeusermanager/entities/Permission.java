package gr.nothingness.backofficeusermanager.entities;

import static javax.persistence.FetchType.LAZY;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tadminop")
@NoArgsConstructor @Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Permission {

  private static final String DEFAULT_TYPE_NAME = "GEN";
  private static final String DEFAULT_TYPE_DESCRIPTION = "Default permission type";

  @Column(name = "action")
  @Id @Size(min = 1, max = 64)
  private String name;

  @Column(name = "desc")
  @NotNull @Size(min = 1, max = 80)
  private String description;

  @Column(name = "disporder")
  private Short displayOrder;

  @ManyToMany(mappedBy = "permissions")
  @JsonIgnore
  private Set<BackofficeUser> users;

  @ManyToMany(mappedBy = "permissions")
  @JsonIgnore
  private Set<BackofficeGroup> groups;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "type")
  private PermissionType type;

  @PrePersist @PreUpdate
  public void prePersist() {
    if (type == null) {
      type = createDefaultPermissionType();
    }
  }

  @PreRemove
  private void disassociate() {
    users.forEach(user -> user.removePermission(this));
    groups.forEach(group -> group.removePermission(this));
  }

  private PermissionType createDefaultPermissionType() {
    PermissionType permissionType = new PermissionType();

    permissionType.setName(DEFAULT_TYPE_NAME);
    permissionType.setDescription(DEFAULT_TYPE_DESCRIPTION);

    return permissionType;
  }

}

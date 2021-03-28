package gr.nothingness.backofficeusermanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tadminop")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Permission {

  private static final String DEFAULT_TYPE_NAME = "GEN";
  private static final String DEFAULT_TYPE_DESCRIPTION = "Default permission type";

  @Column(name = "action")
  @Id @Size(min = 1, max = 64)
  @Getter @Setter private String name;

  @Column(name = "desc")
  @NotNull @Size(min = 1, max = 80)
  @Getter @Setter private String description;

  @Column(name = "disporder")
  @Getter @Setter private Short displayOrder;

  @ManyToMany(mappedBy = "permissions")
  @JsonIgnore
  @Getter @Setter private List<BackofficeUser> users;

  @ManyToMany(mappedBy = "permissions")
  @JsonIgnore
  @Getter @Setter private List<BackofficeGroup> groups;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "type")
  @Getter @Setter private PermissionType type;

  @PrePersist @PreUpdate
  public void prePersist() {
    if (type == null) {
      type = createDefaultPermissionType();
    }
  }

  private PermissionType createDefaultPermissionType() {
    PermissionType permissionType = new PermissionType();

    permissionType.setName(DEFAULT_TYPE_NAME);
    permissionType.setDescription(DEFAULT_TYPE_DESCRIPTION);

    return permissionType;
  }

}

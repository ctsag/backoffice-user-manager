package gr.nothingness.backofficeusermanager.audit.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tadmingroupop_aud")
@NoArgsConstructor @Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BackofficeGroupPermissionAudit extends BaseAudit {

  @Column(name = "group_id")
  private Long groupId;

  @Column(name = "action")
  @Size(max = 64)
  private String permissionName;

}

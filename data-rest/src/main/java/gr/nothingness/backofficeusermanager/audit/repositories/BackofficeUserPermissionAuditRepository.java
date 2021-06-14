package gr.nothingness.backofficeusermanager.audit.repositories;

import gr.nothingness.backofficeusermanager.audit.entities.BackofficeGroupPermissionAudit;
import java.util.List;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(
    collectionResourceRel = "groupPermissionAudits",
    itemResourceRel = "groupPermissionAudit",
    path = "groupPermissionAudits"
)
public interface BackofficeUserPermissionAuditRepository extends AuditRepository<BackofficeGroupPermissionAudit> {

  @RestResource(path = "byGroupId", rel = "byGroupId")
  public List<BackofficeGroupPermissionAudit> findByGroupId(Long groupId);

  @RestResource(path = "byPermissionName", rel = "byPermissionName")
  public List<BackofficeGroupPermissionAudit> findByPermissionName(String permissionName);

}

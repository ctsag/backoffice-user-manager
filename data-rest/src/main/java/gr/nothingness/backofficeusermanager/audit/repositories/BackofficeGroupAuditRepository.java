package gr.nothingness.backofficeusermanager.audit.repositories;

import gr.nothingness.backofficeusermanager.audit.entities.BackofficeGroupAudit;
import java.util.List;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(
    collectionResourceRel = "groupAudits",
    itemResourceRel = "groupAudit",
    path = "groupAudits"
)
public interface BackofficeGroupAuditRepository extends AuditRepository<BackofficeGroupAudit> {

  @RestResource(path = "byGroupId", rel = "byGroupId")
  public List<BackofficeGroupAudit> findByGroupId(Long groupId);

}

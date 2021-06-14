package gr.nothingness.backofficeusermanager.model.repositories;

import gr.nothingness.backofficeusermanager.model.entities.PermissionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "permissionTypes",
    itemResourceRel = "permissionType",
    path = "permissionTypes"
)
public interface PermissionTypeRepository extends JpaRepository<PermissionType, String> {

}

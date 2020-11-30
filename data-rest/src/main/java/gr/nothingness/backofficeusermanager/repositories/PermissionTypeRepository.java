package gr.nothingness.backofficeusermanager.repositories;

import gr.nothingness.backofficeusermanager.entities.PermissionType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "permissionTypes",
    itemResourceRel = "permissionType",
    path = "permissionsTypes"
)
public interface PermissionTypeRepository extends CrudRepository<PermissionType, String> {

}

package gr.nothingness.backofficeusermanager.repositories;

import gr.nothingness.backofficeusermanager.entities.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "permissions",
    itemResourceRel = "permission",
    path = "permissions"
)
public interface PermissionRepository extends CrudRepository<Permission, String> {

}

package gr.nothingness.backofficeusermanager.repositories;

import gr.nothingness.backofficeusermanager.entities.Permission;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(
    collectionResourceRel = "permissions",
    itemResourceRel = "permission",
    path = "permissions"
)
public interface PermissionRepository extends JpaRepository<Permission, String> {

  @RestResource(path = "byNameLike", rel = "byNameLike")
  public List<Permission> findByNameContainingIgnoreCase(String name);

}

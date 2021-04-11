package gr.nothingness.backofficeusermanager.security.repositories;

import gr.nothingness.backofficeusermanager.security.entities.AuthPermission;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthPermissionRepository extends ReadOnlyRepository<AuthPermission, String> {

}

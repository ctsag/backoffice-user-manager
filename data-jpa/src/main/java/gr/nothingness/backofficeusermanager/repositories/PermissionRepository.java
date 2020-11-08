package gr.nothingness.backofficeusermanager.repositories;

import gr.nothingness.backofficeusermanager.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, String> {

}

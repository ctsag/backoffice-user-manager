package gr.nothingness.backofficeusermanager.security.repositories;

import gr.nothingness.backofficeusermanager.security.entities.AuthGroup;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthGroupRepository extends ReadOnlyRepository<AuthGroup, Long> {

}

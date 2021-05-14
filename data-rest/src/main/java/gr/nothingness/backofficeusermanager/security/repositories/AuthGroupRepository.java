package gr.nothingness.backofficeusermanager.security.repositories;

import gr.nothingness.backofficeusermanager.security.entities.AuthGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthGroupRepository extends CrudRepository<AuthGroup, Long> {

}

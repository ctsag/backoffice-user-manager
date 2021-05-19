package gr.nothingness.backofficeusermanager.security.repositories;

import gr.nothingness.backofficeusermanager.security.entities.AuthUser;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepository extends CrudRepository<AuthUser, Long> {

  public Optional<AuthUser> findByUsername(String username);

}

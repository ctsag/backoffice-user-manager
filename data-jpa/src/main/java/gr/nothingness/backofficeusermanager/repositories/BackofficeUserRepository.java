package gr.nothingness.backofficeusermanager.repositories;

import gr.nothingness.backofficeusermanager.entities.BackofficeUser;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface BackofficeUserRepository extends CrudRepository<BackofficeUser, Long> {

  List<BackofficeUser> findByLastName(String name);

}

package gr.nothingness.backofficeusermanager.repositories;

import gr.nothingness.backofficeusermanager.entities.BackofficeUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackofficeUserRepository extends JpaRepository<BackofficeUser, Long> {

  List<BackofficeUser> findByLastName(String name);

}

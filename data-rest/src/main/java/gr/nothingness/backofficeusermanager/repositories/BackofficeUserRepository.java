package gr.nothingness.backofficeusermanager.repositories;

import gr.nothingness.backofficeusermanager.entities.BackofficeUser;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "users",
    itemResourceRel = "user",
    path = "users"
)
public interface BackofficeUserRepository extends CrudRepository<BackofficeUser, Long> {

  List<BackofficeUser> findByLastName(String name);

}

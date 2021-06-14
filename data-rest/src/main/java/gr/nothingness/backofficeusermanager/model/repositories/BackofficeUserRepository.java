package gr.nothingness.backofficeusermanager.model.repositories;

import gr.nothingness.backofficeusermanager.model.entities.BackofficeUser;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(
    collectionResourceRel = "users",
    itemResourceRel = "user",
    path = "users"
)
public interface BackofficeUserRepository extends JpaRepository<BackofficeUser, Long> {

  @RestResource(path = "byFullName", rel = "byFullName")
  public List<BackofficeUser> findByFirstNameAndLastNameIgnoreCase(
      String firstName,
      String lastName
  );

  @RestResource(exported = false)
  public Optional<BackofficeUser> findByUsername(String username);

}

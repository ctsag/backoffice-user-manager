package gr.nothingness.backofficeusermanager.model.repositories;

import gr.nothingness.backofficeusermanager.model.entities.BackofficeGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "groups",
    itemResourceRel = "group",
    path = "groups"
)
public interface BackofficeGroupRepository extends JpaRepository<BackofficeGroup, Long> {

}

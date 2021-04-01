package gr.nothingness.backofficeusermanager.repositories;

import gr.nothingness.backofficeusermanager.entities.BackofficeGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "groups",
    itemResourceRel = "group",
    path = "groups"
)
public interface BackofficeGroupRepository extends CrudRepository<BackofficeGroup, Long> {

}

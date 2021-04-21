package gr.nothingness.backofficeusermanager.repositories;

import gr.nothingness.backofficeusermanager.entities.FlagDefinition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "flagDefinitions",
    itemResourceRel = "flagDefinition",
    path = "flagDefinitions"
)
public interface FlagDefinitionRepository extends CrudRepository<FlagDefinition, String> {

}

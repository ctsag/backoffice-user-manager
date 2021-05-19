package gr.nothingness.backofficeusermanager.repositories;

import gr.nothingness.backofficeusermanager.entities.FlagDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "flagDefinitions",
    itemResourceRel = "flagDefinition",
    path = "flagDefinitions"
)
public interface FlagDefinitionRepository extends JpaRepository<FlagDefinition, String> {

}

package gr.nothingness.backofficeusermanager.repositories;

import gr.nothingness.backofficeusermanager.entities.FlagValue;
import gr.nothingness.backofficeusermanager.entities.compositekeys.FlagKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "flags",
    itemResourceRel = "flag",
    path = "flags"
)
public interface FlagValueRepository extends CrudRepository<FlagValue, FlagKey> {

}

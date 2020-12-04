package gr.nothingness.backofficeusermanager.repositories;

import gr.nothingness.backofficeusermanager.entities.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "positions",
    itemResourceRel = "position",
    path = "positions"
)
public interface PositionsRepository extends CrudRepository<Position, String> {

}

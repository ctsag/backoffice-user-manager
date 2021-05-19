package gr.nothingness.backofficeusermanager.repositories;

import gr.nothingness.backofficeusermanager.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "positions",
    itemResourceRel = "position",
    path = "positions"
)
public interface PositionsRepository extends JpaRepository<Position, String> {

}

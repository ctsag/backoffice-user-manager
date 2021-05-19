package gr.nothingness.backofficeusermanager.repositories;

import gr.nothingness.backofficeusermanager.entities.Jurisdiction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "jurisdictions",
    itemResourceRel = "jurisdiction",
    path = "jurisdictions"
)
public interface JurisdictionRepository extends JpaRepository<Jurisdiction, Long> {

}

package gr.nothingness.backofficeusermanager.model.repositories;

import gr.nothingness.backofficeusermanager.model.entities.Timezone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "timezones",
    itemResourceRel = "timezone",
    path = "timezones"
)
public interface TimezoneRepository extends JpaRepository<Timezone, Long> {

}

package gr.nothingness.backofficeusermanager.repositories;

import gr.nothingness.backofficeusermanager.entities.Timezone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "timezones",
    itemResourceRel = "timezone",
    path = "timezones"
)
public interface TimezoneRepository extends JpaRepository<Timezone, Long> {

}

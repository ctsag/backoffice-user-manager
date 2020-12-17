package gr.nothingness.backofficeusermanager.repositories;

import gr.nothingness.backofficeusermanager.entities.Timezone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "timezones",
    itemResourceRel = "timezone",
    path = "timezones"
)
public interface TimezoneRepository extends CrudRepository<Timezone, Long> {

}

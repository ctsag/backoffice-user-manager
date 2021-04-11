package gr.nothingness.backofficeusermanager.configuration;

import static org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED;

import gr.nothingness.backofficeusermanager.entities.BackofficeGroup;
import gr.nothingness.backofficeusermanager.entities.BackofficeUser;
import gr.nothingness.backofficeusermanager.entities.Permission;
import gr.nothingness.backofficeusermanager.entities.PermissionType;
import gr.nothingness.backofficeusermanager.repositories.BackofficeUserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class DataRestConfiguration implements RepositoryRestConfigurer {

  @Override
  public void configureRepositoryRestConfiguration(
      RepositoryRestConfiguration configuration,
      CorsRegistry corsRegistry
  ) {
    configuration.exposeIdsFor(
        BackofficeUser.class,
        BackofficeGroup.class,
        Permission.class,
        PermissionType.class
    );

    configuration
        .withEntityLookup()
        .forRepository(BackofficeUserRepository.class)
        .withIdMapping(BackofficeUser::getUsername)
        .withLookup(BackofficeUserRepository::findByUsername);
  }

}

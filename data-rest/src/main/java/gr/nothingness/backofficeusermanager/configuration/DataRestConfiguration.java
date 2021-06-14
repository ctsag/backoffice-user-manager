package gr.nothingness.backofficeusermanager.configuration;

import gr.nothingness.backofficeusermanager.audit.entities.BackofficeGroupPermissionAudit;
import gr.nothingness.backofficeusermanager.model.entities.BackofficeGroup;
import gr.nothingness.backofficeusermanager.audit.entities.BackofficeGroupAudit;
import gr.nothingness.backofficeusermanager.model.entities.BackofficeUser;
import gr.nothingness.backofficeusermanager.model.entities.FlagDefinition;
import gr.nothingness.backofficeusermanager.model.entities.Jurisdiction;
import gr.nothingness.backofficeusermanager.model.entities.Permission;
import gr.nothingness.backofficeusermanager.model.entities.PermissionType;
import gr.nothingness.backofficeusermanager.model.repositories.BackofficeUserRepository;
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
        PermissionType.class,
        Jurisdiction.class,
        FlagDefinition.class,
        BackofficeGroupAudit.class,
        BackofficeGroupPermissionAudit.class
    );

    configuration
        .withEntityLookup()
        .forRepository(BackofficeUserRepository.class)
        .withIdMapping(BackofficeUser::getUsername)
        .withLookup(BackofficeUserRepository::findByUsername);

    configuration
        .getExposureConfiguration()
        .disablePutForCreation();
  }

}

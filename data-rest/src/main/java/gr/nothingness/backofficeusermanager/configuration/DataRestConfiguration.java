package gr.nothingness.backofficeusermanager.configuration;

import gr.nothingness.backofficeusermanager.entities.BackofficeUser;
import gr.nothingness.backofficeusermanager.entities.Permission;
import gr.nothingness.backofficeusermanager.entities.PermissionType;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class DataRestConfiguration implements RepositoryRestConfigurer {

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration configuration) {
    configuration.exposeIdsFor(BackofficeUser.class, Permission.class, PermissionType.class);
  }

}

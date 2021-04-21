package gr.nothingness.backofficeusermanager.entities.compositekeys;

import gr.nothingness.backofficeusermanager.entities.FlagValue;
import gr.nothingness.backofficeusermanager.repositories.FlagValueRepository;
import java.io.Serializable;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.rest.webmvc.spi.BackendIdConverter;
import org.springframework.stereotype.Service;

@Service
public class FlagKeyIdConverter implements BackendIdConverter {

  @Autowired
  private FlagValueRepository repository;

  private static final String DELIMITER = "_";

  @Override
  public boolean supports(Class<?> candidateClass) {
    return candidateClass.equals(FlagValue.class);
  }

  @Override
  public Serializable fromRequestId(String source, Class<?> candidateClass) {
    int delimiterIndex = source.indexOf(DELIMITER);

    if (delimiterIndex > -1) {
      Long userId = Long.parseLong(source.substring(0, delimiterIndex));
      String flagName = source.substring(delimiterIndex + 1);

      if (repository.existsById(new FlagKey(userId, flagName))) {
        return new FlagKey(userId, flagName);
      }
    }

    throw new ResourceNotFoundException();
  }

  @Override
  public String toRequestId(Serializable source, Class<?> candidateClass) {
    FlagKey flagKey = (FlagKey) source;
    Long userId = flagKey.getUserId();
    String flagName = flagKey.getFlagName();

    if (repository.existsById(new FlagKey(userId, flagName))) {
      return String.format("%s" + DELIMITER + "%s", flagKey.getUserId(), flagKey.getFlagName());
    }

    throw new HibernateException("composite key does not match an existing record");
  }

}

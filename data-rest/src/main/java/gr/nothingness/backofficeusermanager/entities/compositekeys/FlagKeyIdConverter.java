package gr.nothingness.backofficeusermanager.entities.compositekeys;

import gr.nothingness.backofficeusermanager.entities.FlagValue;
import java.io.Serializable;
import org.springframework.data.rest.webmvc.spi.BackendIdConverter;
import org.springframework.stereotype.Service;

@Service
public class FlagKeyIdConverter implements BackendIdConverter {

  private static final String DELIMITER = "_";

  @Override
  public boolean supports(Class<?> candidateClass) {
    return candidateClass.equals(FlagValue.class);
  }

  @Override
  public Serializable fromRequestId(String source, Class<?> candidateClass) {
    int delimiterIndex = source.indexOf(DELIMITER);
    Long userId = Long.parseLong(source.substring(0, delimiterIndex));
    String flagName = source.substring(delimiterIndex + 1);

    return new FlagKey(userId, flagName);
  }

  @Override
  public String toRequestId(Serializable source, Class<?> candidateClass) {
    FlagKey flagKey = (FlagKey) source;
    return String.format("%s" + DELIMITER + "%s", flagKey.getUserId(), flagKey.getFlagName());
  }
}

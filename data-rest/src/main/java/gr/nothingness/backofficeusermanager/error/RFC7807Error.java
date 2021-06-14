package gr.nothingness.backofficeusermanager.error;

import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@JsonInclude(Include.NON_EMPTY)
@Getter
public class RFC7807Error {

  @JsonIgnore
  private final HttpStatus statusEnum;

  @JsonIgnore
  private MediaType contentType = APPLICATION_PROBLEM_JSON;

  private final Integer status;
  private String title = "Undefined error";
  private String detail;
  private String type;
  private String instance;
  private final Set<FailureDetail> failures = new HashSet<>();

  private RFC7807Error(HttpStatus status) {
    this.status = status.value();
    this.statusEnum = status;
  }

  public void addFailure(FailureDetail failure) {
    failures.add(failure);
  }

  public Map<String, Object> toMap() {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.convertValue(this, new TypeReference<Map<String, Object>>(){});
  }

  public static RFC7807ErrorBuilder withStatus(HttpStatus status) {
    return new RFC7807Error(status).new RFC7807ErrorBuilder();
  }

  public class RFC7807ErrorBuilder {

    private final RFC7807Error rfc7807Error;

    public RFC7807ErrorBuilder() {
      rfc7807Error = RFC7807Error.this;
    }

    public RFC7807ErrorBuilder andTitle(String title) {
      rfc7807Error.title = title;
      return this;
    }

    public RFC7807ErrorBuilder andDetail(String detail) {
      rfc7807Error.detail = detail;
      return this;
    }

    public RFC7807ErrorBuilder andType(String type) {
      rfc7807Error.type = type;
      return this;
    }

    public RFC7807ErrorBuilder andInstance(String instance) {
      rfc7807Error.instance = instance;
      return this;
    }

    public RFC7807ErrorBuilder andContentType(MediaType contentType) {
      rfc7807Error.contentType = contentType;
      return this;
    }

    public RFC7807Error build() {
      return rfc7807Error;
    }

  }

}
package gr.nothingness.backofficeusermanager.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.nothingness.backofficeusermanager.exceptions.problems.Problem;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@JsonInclude(Include.NON_EMPTY)
public class RFC7807Error {

  @Getter @Setter @JsonIgnore private MediaType contentType = MediaType.APPLICATION_PROBLEM_JSON;
  @Getter @JsonIgnore private final HttpStatus statusEnum;
  @Getter @NotNull private final Integer status;
  @Getter @NotNull private final String title;
  @Getter @NotNull private final String detail;
  @Getter @Setter private String type;
  @Getter @Setter private String instance;
  @Getter private final Set<Problem> problems = new HashSet<>();

  public RFC7807Error(HttpStatus status, String title, String detail) {
    this.status = status.value();
    this.statusEnum = status;
    this.title = title;
    this.detail = detail;
  }

  public RFC7807Error(HttpStatus status, String title, String detail, String instance) {
    this(status, title, detail);
    this.instance = instance;
  }

  public RFC7807Error(
      HttpStatus status,
      String title,
      String detail,
      String instance,
      MediaType contentType
  ) {
    this(status, title, detail, instance);
    this.contentType = contentType;
  }

  public void addProblem(Problem problem) {
    problems.add(problem);
  }

  public Map<String, Object> toMap() {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.convertValue(this, new TypeReference<Map<String, Object>>(){});
  }

}
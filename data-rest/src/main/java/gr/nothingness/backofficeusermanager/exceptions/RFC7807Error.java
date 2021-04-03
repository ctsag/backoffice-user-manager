package gr.nothingness.backofficeusermanager.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import gr.nothingness.backofficeusermanager.exceptions.problems.Problem;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@JsonInclude(Include.NON_NULL)
public class RFC7807Error {

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

  public void addProblem(Problem problem) {
    problems.add(problem);
  }

}
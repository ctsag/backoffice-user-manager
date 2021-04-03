package gr.nothingness.backofficeusermanager.exceptions.problems;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidationProblem extends Problem {

  @Getter @NotNull private final String constraint;
  @Getter @NotNull private final String message;

}

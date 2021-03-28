package gr.nothingness.backofficeusermanager.errors.problems;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GenericProblem extends Problem {

  @Getter @NotNull private final String message;

}

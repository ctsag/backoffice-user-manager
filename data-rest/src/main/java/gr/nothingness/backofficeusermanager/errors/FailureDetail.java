package gr.nothingness.backofficeusermanager.errors;

import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@JsonInclude(Include.NON_EMPTY)
@RequiredArgsConstructor(access = PRIVATE)
public class FailureDetail {

  @Getter private final String message;
  @Getter private String constraint;

  public static FailureDetailBuilder withMessage(String message) {
    return new FailureDetail(message).new FailureDetailBuilder();
  }

  public class FailureDetailBuilder {

    private final FailureDetail failureDetails;

    public FailureDetailBuilder() {
      failureDetails = FailureDetail.this;
    }

    public FailureDetailBuilder andConstraint(String constraint) {
      failureDetails.constraint = constraint;
      return this;
    }

    public FailureDetail build() {
      return failureDetails;
    }

  }

}

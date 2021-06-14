package gr.nothingness.backofficeusermanager.error;

import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@JsonInclude(Include.NON_EMPTY)
@RequiredArgsConstructor(access = PRIVATE) @Getter
public class FailureDetail {

  private final String message;
  private String constraint;

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

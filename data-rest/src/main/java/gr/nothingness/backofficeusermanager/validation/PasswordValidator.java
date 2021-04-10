package gr.nothingness.backofficeusermanager.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

  private static final int MIN_LENGTH = 8;
  private static final int MAX_LENGTH = 40;

  @Override
  public void initialize(ValidPassword annotation) {
  }

  @Override
  public boolean isValid(String password, ConstraintValidatorContext context) {
    context.disableDefaultConstraintViolation();

    if (password != null) {
      if (password.length() < MIN_LENGTH || password.length() > MAX_LENGTH) {
        return throwViolation("size must be between " + MIN_LENGTH + " and " + MAX_LENGTH, context);
      }

      if (password.equals("password")) {
        return throwViolation("must not be 'password'", context);
      }

      if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
        return throwViolation(
            "must containt one number, one uppercase and one lowercase character",
            context
        );
      }
    }

    return true;
  }

  private boolean throwViolation(String message, ConstraintValidatorContext context) {
    context.
        buildConstraintViolationWithTemplate(message)
        .addPropertyNode("password")
        .addConstraintViolation();

    return false;
  }

}

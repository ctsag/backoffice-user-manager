package gr.nothingness.backofficeusermanager.validation;

import gr.nothingness.backofficeusermanager.model.entities.BackofficeUser;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, BackofficeUser> {

  private static final int MIN_LENGTH = 8;
  private static final int MAX_LENGTH = 40;

  @Override
  public void initialize(ValidPassword annotation) {
  }

  @Override
  public boolean isValid(BackofficeUser user, ConstraintValidatorContext context) {
    boolean valid = true;

    context.disableDefaultConstraintViolation();

    if (user.getPassword() == null) {
      valid = throwViolation("must not be null", context);
    }

    if (user.getPlainTextPassword() != null) {
      if (user.getPlainTextPassword().equals(user.getUsername())) {
        valid = throwViolation("must not be the same as the username", context);
      }

      if (user.getPlainTextPassword().length() < MIN_LENGTH || user.getPlainTextPassword().length() > MAX_LENGTH) {
        valid = throwViolation(
            "size must be between " + MIN_LENGTH + " and " + MAX_LENGTH,
            context
        );
      }

      if (user.getPlainTextPassword().equals("password")) {
        valid = throwViolation("must not be 'password'", context);
      }

      if (!user.getPlainTextPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
        valid = throwViolation(
            "must contain one number, one uppercase and one lowercase character",
            context
        );
      }
    }

    return valid;
  }

  private boolean throwViolation(
      String message,
      ConstraintValidatorContext context
  ) {
    context.
        buildConstraintViolationWithTemplate(message)
        .addPropertyNode("password")
        .addConstraintViolation();

    return false;
  }

}

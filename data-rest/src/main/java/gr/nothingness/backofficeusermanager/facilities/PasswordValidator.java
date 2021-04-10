package gr.nothingness.backofficeusermanager.facilities;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

  private static final int MIN_LENGTH = 8;
  private static final int MAX_LENGTH = 40;

  @Override
  public void initialize(ValidPassword password) {
  }

  @Override
  public boolean isValid(String password, ConstraintValidatorContext context) {
    boolean validates = true;

    context.disableDefaultConstraintViolation();

    if (password != null && !password.isEmpty()) {
      if (password.length() < MIN_LENGTH || password.length() > MAX_LENGTH) {
        context.
            buildConstraintViolationWithTemplate(
                "size must be between " + MIN_LENGTH + " and " + MAX_LENGTH
            )
            .addPropertyNode("password")
            .addConstraintViolation();

        validates = false;
      }
    }

    return validates;
  }

}

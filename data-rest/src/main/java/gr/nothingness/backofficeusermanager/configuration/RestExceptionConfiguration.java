package gr.nothingness.backofficeusermanager.configuration;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import gr.nothingness.backofficeusermanager.errors.RFC7807Error;
import gr.nothingness.backofficeusermanager.errors.problems.Problem;
import gr.nothingness.backofficeusermanager.errors.problems.GenericProblem;
import gr.nothingness.backofficeusermanager.errors.problems.ValidationProblem;
import javax.validation.ConstraintViolation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionConfiguration {

  @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
  protected ResponseEntity<Object> handleDatabaseConstraintViolation(
      org.hibernate.exception.ConstraintViolationException exception
  ) {
    RFC7807Error apiError = new RFC7807Error(
        INTERNAL_SERVER_ERROR,
        "Database level constraint violation",
        "One or more of the provided values violates a database level contraint. "
            + "These contraints usually concern uniqueness, referential integrity "
            + "or limiting acceptable values"
    );

    Throwable sqlException = exception.getSQLException().getCause();
    Problem problem = new ValidationProblem(
        exception.getConstraintName(),
        sqlException.getMessage().replaceFirst("ISAM error: {2}", "").replaceAll(".$", "")
    );
    apiError.addProblem(problem);

    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(javax.validation.ConstraintViolationException.class)
  protected ResponseEntity<Object> handleValidationConstraintFailure(
      javax.validation.ConstraintViolationException exception
  ) {
    RFC7807Error apiError = new RFC7807Error(
        BAD_REQUEST,
        "Application level constraint violation",
        "One or more of the provided values violates validation rules. "
            + "These rules usually concern text length, minimum and maximum "
            + "numeric values, or the prevention of empty input for required "
            + "fields"
    );

    for (ConstraintViolation<?> violation: exception.getConstraintViolations()) {
      Problem problem = new ValidationProblem(
          violation.getPropertyPath().toString(),
          violation.getMessage()
      );
      apiError.addProblem(problem);
    }

    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(com.fasterxml.jackson.databind.exc.InvalidFormatException.class)
  protected ResponseEntity<Object> handleEnumeratedConstraintFailure(
      com.fasterxml.jackson.databind.exc.InvalidFormatException exception
  ) {
    RFC7807Error apiError = new RFC7807Error(
        BAD_REQUEST,
        "Enumeration constraint violation",
        "One or more of the provided values is not within the list of acceptable ones "
            + "for the field they're being assigned to"
    );

    Problem problem = new ValidationProblem(
        exception.getPathReference().replaceFirst("^(.+?)\"", "").replaceFirst("\"(.+?)$", ""),
        exception.getOriginalMessage()
            .replaceFirst("^(.+?): ", "")
            .replaceFirst(" for Enum class", "")
    );
    apiError.addProblem(problem);

    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(org.hibernate.HibernateException.class)
  protected ResponseEntity<Object> handlePrimaryKeyChangeFailure(
      org.hibernate.HibernateException exception
  ) {
    RFC7807Error apiError;
    Problem problem;

    if (exception.getMessage().matches("^identifier of an instance of .*$")) {
      apiError = new RFC7807Error(
          CONFLICT,
          "Primary identifier change attempt",
          "An attempt to change the primary identifier for an entity has been made. "
              + "This is not allowed"
      );

      problem = new ValidationProblem(
          "primary identifier",
          exception.getMessage().replaceFirst("^(.+?)was ", "")
      );
    } else if (exception.getMessage().matches("^ids for this class must be manually assigned.*$")) {
      apiError = new RFC7807Error(
          BAD_REQUEST,
          "Primary identifier required",
          "Data entities require a primary identifier to be provided before it can be created"
      );

      problem = new GenericProblem(
          "no primary identifier provided"
      );
    } else if (exception.getMessage().matches("^An immutable natural identifier of entity.*$")) {
      apiError = new RFC7807Error(
          CONFLICT,
          "Immutable identifier change attempt",
          "An attempt to change an immutable identifier for an entity has been made. "
              + "This is not allowed"
      );

      problem = new ValidationProblem(
          "immutable identifier",
          exception.getMessage().replaceFirst("^(.+?)was ", "")
      );
    } else {
      apiError = new RFC7807Error(
          INTERNAL_SERVER_ERROR,
          "Unknown database level violation",
          "Something went wrong when trying to apply the requested change to the database"
      );

      problem = new GenericProblem(exception.getMessage());
    }

    apiError.addProblem(problem);

    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(org.springframework.core.convert.ConversionFailedException.class)
  protected ResponseEntity<Object> handleDataConversionFailure(
      org.springframework.core.convert.ConversionFailedException exception
  ) {
    RFC7807Error apiError = new RFC7807Error(
        BAD_REQUEST,
        "Data type conversion failure",
        "The request URL or body contains values that are of an invalid data type"
    );

    if (exception.getMessage() != null) {
      Problem problem = new GenericProblem(
          exception.getMessage()
              .replaceFirst("^(.+?): ", "")
              .replaceFirst("For ", "")
              .replaceAll("\"", "'")
      );
      apiError.addProblem(problem);
    }

    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(com.fasterxml.jackson.databind.exc.MismatchedInputException.class)
  protected ResponseEntity<Object> handleMissingInputFailure(
      com.fasterxml.jackson.databind.exc.MismatchedInputException exception
  ) {
    RFC7807Error apiError = new RFC7807Error(
        BAD_REQUEST,
        "Missing input",
        "The message received is missing some of the required input. This usually indicates "
            + "a missing message body"
    );

    Problem problem = new GenericProblem("missing input");
    apiError.addProblem(problem);

    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(com.fasterxml.jackson.core.JsonParseException.class)
  protected ResponseEntity<Object> handleMalformedInputFailure(
      com.fasterxml.jackson.core.JsonParseException exception
  ) {
    RFC7807Error apiError = new RFC7807Error(
        BAD_REQUEST,
        "Malformed input",
        "The message received contains input that is unable to be parsed. This usually "
            + "indicates a malformed message body, e.g. JSON or XML syntax errors"
    );

    Problem problem = new GenericProblem("malformed JSON input");
    apiError.addProblem(problem);

    return buildResponseEntity(apiError);
  }

  private ResponseEntity<Object> buildResponseEntity(RFC7807Error apiError) {
    return ResponseEntity
        .status(apiError.getStatusEnum())
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(apiError);
  }

}

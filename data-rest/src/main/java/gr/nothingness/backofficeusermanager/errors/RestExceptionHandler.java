package gr.nothingness.backofficeusermanager.errors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
  protected ResponseEntity<Object> handleNoHandlerFound(
      org.springframework.web.servlet.NoHandlerFoundException exception,
      HttpServletRequest request
  ) {
    RFC7807Error apiError = RFC7807Error
        .withStatus(NOT_FOUND)
        .andTitle("Not Found")
        .andInstance(request.getRequestURI())
        .build();

    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(org.springframework.data.rest.webmvc.ResourceNotFoundException.class)
  protected ResponseEntity<Object> handleNotFoundFailure(
      org.springframework.data.rest.webmvc.ResourceNotFoundException exception,
      HttpServletRequest request
  ) {
    RFC7807Error apiError = RFC7807Error
        .withStatus(NOT_FOUND)
        .andTitle("Not Found")
        .andInstance(request.getRequestURI())
        .build();

    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
  protected ResponseEntity<Object> handleDatabaseConstraintViolation(
      org.hibernate.exception.ConstraintViolationException exception,
      HttpServletRequest request
  ) {
    RFC7807Error apiError = RFC7807Error
        .withStatus(INTERNAL_SERVER_ERROR)
        .andTitle("Database level constraint violation")
        .andDetail(
            "One or more of the provided values violates a database level constraint. "
                + "These constraints usually concern uniqueness, referential integrity "
                + "or limiting acceptable values"
        )
        .andInstance(request.getRequestURI())
        .build();

    Throwable sqlException = exception.getSQLException();

    if (sqlException.getCause() != null) {
      sqlException = sqlException.getCause();
    }

    FailureDetail failureDetail = FailureDetail
        .withMessage(
            sqlException
                .getMessage()
                .replaceFirst("constraint \\((.+?)\\) ", "constraint ")
                .replaceFirst("ISAM error: {2}", "")
                .replaceAll(".$", "")
        )
        .andConstraint(exception.getConstraintName())
        .build();

    apiError.addFailure(failureDetail);

    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(org.hibernate.exception.GenericJDBCException.class)
  protected ResponseEntity<Object> handleDatabaseConstraintViolation(
      org.hibernate.exception.GenericJDBCException exception,
      HttpServletRequest request
  ) {
    RFC7807Error apiError = RFC7807Error
        .withStatus(INTERNAL_SERVER_ERROR)
        .andTitle("Database integrity violation")
        .andDetail(
            "One or more of the provided values causes a database integrity violation. "
                + "Usually this issue involves missing or null values being passed when "
                + "a non null or non empty value is expected, or when the wrong data type "
                + "is used, e.g. a string when a numeric value is expected."
        )
        .andInstance(request.getRequestURI())
        .build();

    Throwable sqlException = exception.getSQLException();
    FailureDetail failureDetail = FailureDetail
        .withMessage(sqlException.getMessage().replaceAll("on table \\((.+?)\\) ", ""))
        .build();

    apiError.addFailure(failureDetail);

    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(javax.validation.ConstraintViolationException.class)
  protected ResponseEntity<Object> handleValidationConstraintFailure(
      javax.validation.ConstraintViolationException exception,
      HttpServletRequest request
  ) {
    RFC7807Error apiError = RFC7807Error
        .withStatus(BAD_REQUEST)
        .andTitle("Application level constraint violation")
        .andDetail(
            "One or more of the provided values violates validation rules. "
                + "These rules usually concern text length, minimum and maximum "
                + "numeric values, or the prevention of empty input for required fields"
        )
        .andInstance(request.getRequestURI())
        .build();

    for (ConstraintViolation<?> violation: exception.getConstraintViolations()) {
      FailureDetail failureDetail = FailureDetail
          .withMessage(violation.getMessage())
          .andConstraint(violation.getPropertyPath().toString().replaceFirst("^(.+?)\\.", ""))
          .build();

      apiError.addFailure(failureDetail);
    }

    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(com.fasterxml.jackson.databind.exc.InvalidFormatException.class)
  protected ResponseEntity<Object> handleEnumeratedConstraintFailure(
      com.fasterxml.jackson.databind.exc.InvalidFormatException exception,
      HttpServletRequest request
  ) {
    RFC7807Error apiError = RFC7807Error
        .withStatus(BAD_REQUEST)
        .andTitle("Enumeration constraint violation")
        .andDetail(
            "One or more of the provided values is not within the list of acceptable ones "
                + "for the field they're being assigned to"
        )
        .andInstance(request.getRequestURI())
        .build();

    FailureDetail failureDetail = FailureDetail
        .withMessage(
            exception
                .getOriginalMessage()
                .replaceFirst("^(.+?): ", "")
                .replaceFirst(" for Enum class", "")
        )
        .andConstraint(
            exception
                .getPathReference()
                .replaceFirst("^(.+?)\"", "")
                .replaceFirst("\"(.+?)$", "")
        )
        .build();

    apiError.addFailure(failureDetail);

    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(org.hibernate.HibernateException.class)
  protected ResponseEntity<Object> handlePrimaryKeyChangeFailure(
      org.hibernate.HibernateException exception,
      HttpServletRequest request
  ) {
    RFC7807Error apiError;
    FailureDetail failureDetail;

    if (exception.getMessage().matches("^identifier of an instance of .*$")) {
      apiError = RFC7807Error
          .withStatus(CONFLICT)
          .andTitle("Primary identifier change attempt")
          .andDetail(
              "An attempt to change the primary identifier for an entity has been made. "
                  + "This is not allowed"
          )
          .andInstance(request.getRequestURI())
          .build();

      failureDetail = FailureDetail
          .withMessage(exception.getMessage().replaceFirst("^(.+?)was ", ""))
          .andConstraint("primary identifier")
          .build();
    } else if (exception.getMessage().matches("^ids for this class must be manually assigned.*$")) {
      apiError = RFC7807Error
          .withStatus(BAD_REQUEST)
          .andTitle("Primary identifier required")
          .andDetail(
              "Data entities require a primary identifier to be provided before it can be created"
          )
          .andInstance(request.getRequestURI())
          .build();

      failureDetail = FailureDetail
          .withMessage("no primary identifier provided")
          .build();
    } else if (exception.getMessage().matches("^An immutable natural identifier of entity.*$")) {
      apiError = RFC7807Error
          .withStatus(CONFLICT)
          .andTitle("Immutable identifier change attempt")
          .andDetail(
              "An attempt to change an immutable identifier for an entity has been made. "
                  + "This is not allowed"
          )
          .andInstance(request.getRequestURI())
          .build();

      failureDetail = FailureDetail
          .withMessage(exception.getMessage().replaceFirst("^(.+?)was ", ""))
          .andConstraint("immutable identifier")
          .build();
    } else {
      apiError = RFC7807Error
          .withStatus(INTERNAL_SERVER_ERROR)
          .andTitle("Unknown database level violation")
          .andDetail(
              "Something went wrong when trying to apply the requested change to the database"
          )
          .andInstance(request.getRequestURI())
          .build();

      failureDetail = FailureDetail
          .withMessage(exception.getMessage())
          .build();
    }

    apiError.addFailure(failureDetail);

    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(org.springframework.core.convert.ConversionFailedException.class)
  protected ResponseEntity<Object> handleDataConversionFailure(
      org.springframework.core.convert.ConversionFailedException exception,
      HttpServletRequest request
  ) {
    RFC7807Error apiError = RFC7807Error
        .withStatus(BAD_REQUEST)
        .andTitle("Data type conversion failure")
        .andDetail("The request URL or body contains values that are of an invalid data type")
        .andInstance(request.getRequestURI())
        .build();

    if (exception.getMessage() != null) {
      FailureDetail failureDetail = FailureDetail
          .withMessage(
              exception
                  .getMessage()
                  .replaceFirst("^(.+?): ", "")
                  .replaceFirst("For ", "")
                  .replaceAll("\"", "'")
          )
          .build();

      apiError.addFailure(failureDetail);
    }

    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(com.fasterxml.jackson.databind.exc.MismatchedInputException.class)
  protected ResponseEntity<Object> handleMissingInputFailure(
      com.fasterxml.jackson.databind.exc.MismatchedInputException exception,
      HttpServletRequest request
  ) {
    RFC7807Error apiError = RFC7807Error
        .withStatus(BAD_REQUEST)
        .andTitle("Missing input")
        .andDetail(
            "The message received is missing some of the required input. This usually indicates "
                + "a missing message body"
        )
        .andInstance(request.getRequestURI())
        .build();

    FailureDetail failureDetail = FailureDetail
        .withMessage("missing input")
        .build();

    apiError.addFailure(failureDetail);

    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(com.fasterxml.jackson.core.JsonParseException.class)
  protected ResponseEntity<Object> handleMalformedInputFailure(
      com.fasterxml.jackson.core.JsonParseException exception,
      HttpServletRequest request
  ) {
    RFC7807Error apiError = RFC7807Error
        .withStatus(BAD_REQUEST)
        .andTitle("Malformed input")
        .andDetail(
            "The message received contains input that is unable to be parsed. This usually "
                + "indicates a malformed message body, e.g. JSON or XML syntax errors"
        )
        .andInstance(request.getRequestURI())
        .build();

    FailureDetail failureDetail = FailureDetail
        .withMessage("malformed JSON input")
        .build();

    apiError.addFailure(failureDetail);

    return buildResponseEntity(apiError);
  }

  private ResponseEntity<Object> buildResponseEntity(RFC7807Error apiError) {
    return ResponseEntity
        .status(apiError.getStatusEnum())
        .contentType(apiError.getContentType())
        .body(apiError);
  }

}

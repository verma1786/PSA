package com.state.psa.character.handler;

import com.state.psa.character.exception.*;
import com.state.psa.character.model.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * This class handle all global exception
 */
@ControllerAdvice
@Slf4j
@AllArgsConstructor
public class CustomErrorHandler extends ResponseEntityExceptionHandler {
    MessageSource messageSource;

    /**
     * handle field validation
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ApiResponse errorResponse = ApiResponse.builder()
                .status(String.valueOf(status))
                .message(messageSource.getMessage("validation.error", null, Locale.US))
                .errors(getErrors(ex))
                .build();
        log.error("[CustomErrorHandler.handleMethodArgumentNotValid] : error response {}", errorResponse);
        return new ResponseEntity<>(errorResponse, headers, status);
    }


    /**
     * handle if invoice list is null
     */
    @ExceptionHandler({ResourceNotFoundException.class})
    public final ResponseEntity<Object> handleUserNotFound(Exception ex, WebRequest request) {
        ApiResponse errorResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.NOT_FOUND))
                .message(ex.getMessage())
                .errors(Arrays.asList(ex.getLocalizedMessage()))
                .build();
        log.error("[CustomErrorHandler.handleAllExceptions] : error response {}", errorResponse);
        return new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
    }


    /**
     * handle validation
     */
    @ExceptionHandler({ValidationError.class, ConstraintViolationException.class})
    public final ResponseEntity<Object> handleValidation(Exception ex, WebRequest request) {
        ApiResponse errorResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.BAD_REQUEST))
                .message(ex.getMessage())
                .errors(Arrays.asList(ex.getLocalizedMessage()))
                .build();
        log.error("[CustomErrorHandler.handleAllExceptions] : error response {}", errorResponse);
        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * handle server error
     */
    @ExceptionHandler({InternalServiceError.class})
    public final ResponseEntity<Object> handleServerError(Exception ex, WebRequest request) {
        ApiResponse errorResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                .message(ex.getMessage())
                .errors(Arrays.asList(ex.getLocalizedMessage()))
                .build();
        log.error("[CustomErrorHandler.handleAllExceptions] : error response {}", errorResponse);
        return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * handle all exception hierarchy
     */
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public final ResponseEntity<Object> handleAuthError(Exception ex, WebRequest request) {
        ApiResponse errorResponse = ApiResponse.builder()
                .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .message(ex.getMessage())
                .errors(Arrays.asList(ex.getLocalizedMessage()))
                .build();
        log.error("[CustomErrorHandler.handleAllExceptions] : error response {}", errorResponse);
        return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private List<String> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public final ResponseEntity<Object>   handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex){
            ApiResponse errorResponse = ApiResponse.builder()
                    .status(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()))
                    .message(ex.getMessage())
                    .errors(Arrays.asList(ex.getLocalizedMessage()))
                    .build();
            log.error("[CustomErrorHandler.handleAllExceptions] : error response {}", errorResponse);
            return new ResponseEntity(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

   /* @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }*/

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }
}

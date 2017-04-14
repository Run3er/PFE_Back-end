package projMngmntSaaS.api.validation.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

/**
 * API Error messages formatting for {@link RepositoryConstraintViolationException}.
 * Error responses are returned in JSON as an array of errors, each containing the
 * rejected field name, a code signaling the cause, and the rejected field value.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ RepositoryConstraintViolationException.class })
    public ResponseEntity<Object> handleAccessDeniedException(
            Exception ex, WebRequest request) {
        RepositoryConstraintViolationException nevEx =
                (RepositoryConstraintViolationException) ex;

        ObjectMapper mapper = new ObjectMapper();

        ArrayNode arrayNode = mapper.createArrayNode();
        Errors errors = nevEx.getErrors();
        List<FieldError> fieldErrors = nevEx.getErrors().getFieldErrors();
        List<ObjectError> globalErrors = nevEx.getErrors().getGlobalErrors();

        for (FieldError fieldError : fieldErrors) {
            ObjectNode errorNode = mapper.createObjectNode();
            errorNode
                    .put("field", fieldError.getField())
                    .put("code", fieldError.getCode())
                    .put("rejectedValue", fieldError.getRejectedValue() == null ? null : fieldError.getRejectedValue().toString());
            arrayNode.add(errorNode);
        }
        for (ObjectError globalError : globalErrors) {
            ObjectNode errorNode = mapper.createObjectNode();
            errorNode
                    .put("code", globalError.getCode())
                    .put("message", globalError.toString());
            arrayNode.add(errorNode);
        }

        ObjectNode responseBody = mapper.createObjectNode();
        responseBody
//                .put("objectName", errors.getObjectName())
                .set("errors", arrayNode);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(ex, responseBody, headers, HttpStatus.PARTIAL_CONTENT, request);
    }
}

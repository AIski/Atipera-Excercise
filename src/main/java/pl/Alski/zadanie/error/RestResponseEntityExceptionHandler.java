package pl.Alski.zadanie.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.Alski.zadanie.entity.ErrorResponse;

@ControllerAdvice
public final class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {HttpClientErrorException.class})
    protected ResponseEntity<Object> handleClientError(RuntimeException ex, WebRequest request){
        ErrorResponse response = new ErrorResponse(404,"User not found. Please give an existing github user.");
        return handleExceptionInternal(ex, response,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorResponse response = new ErrorResponse(406,"Not acceptable. Please use 'Accept= application/json' header instead.");
        return handleExceptionInternal(ex, response,
                new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }

}

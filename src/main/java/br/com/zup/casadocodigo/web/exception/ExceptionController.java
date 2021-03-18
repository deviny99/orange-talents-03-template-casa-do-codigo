package br.com.zup.casadocodigo.web.exception;

import org.springframework.http.HttpStatus;

public class ExceptionController extends RuntimeException{

    private final HttpStatus status;

    public ExceptionController(HttpStatus status, String message){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public static ExceptionController notFound(String message){
        return new ExceptionController(HttpStatus.NOT_FOUND,message);
    }

    public static ExceptionController forbidden(String message) {
        return new ExceptionController(HttpStatus.FORBIDDEN, message);
    }

    public static ExceptionController notAcceptable(String message) {
        return new ExceptionController(HttpStatus.NOT_ACCEPTABLE, message);
    }

    public static ExceptionController conflict(String message) {
        return new ExceptionController(HttpStatus.CONFLICT, message);
    }

    public static ExceptionController badRequest(String message) {
        return new ExceptionController(HttpStatus.BAD_REQUEST, message);
    }

    public static ExceptionController preconditionFailed(String message) {
        return new ExceptionController(HttpStatus.PRECONDITION_FAILED, message);
    }

    public static ExceptionController unsupportedMediaType(String message) {
        return new ExceptionController(HttpStatus.UNSUPPORTED_MEDIA_TYPE, message);
    }

}
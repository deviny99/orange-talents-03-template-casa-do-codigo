package br.com.zup.casadocodigo.web.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.http.ResponseEntity.badRequest;


@ControllerAdvice(annotations = RestController.class)
public class ErrorHandler {

    @ExceptionHandler(ExceptionController.class)
    public ResponseEntity<?> handleControllerException(ExceptionController exceptionController){
        return status(exceptionController.getStatus())
                .body(Map.of("mensagem",exceptionController.getLocalizedMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return badRequest().body(Map.of(
                "mensagem", "Não foi possível ler a requisição.\n"+exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        final var campos = exception.getBindingResult().getFieldErrors().stream()
                .filter(it -> it.getDefaultMessage() != null)
                .filter(it -> !it.getDefaultMessage().isBlank())
                .collect(groupingBy(FieldError::getField,
                        mapping(DefaultMessageSourceResolvable::getDefaultMessage, toList())));
        return badRequest().body(Map.of(
                "mensagem", "Requisição inválida.",
                "campos", campos));
    }

}
package br.com.users.app.exceptions;

import br.com.users.app.dto.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponseDto> handleCustomException(CustomException ex) {
        ExceptionResponseDto response = new ExceptionResponseDto();
        response.setMessage(ex.getMessage());
        response.setErros(ex.getErrors());
        return ResponseEntity.status(ex.getHttpStatus()).body(response);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ProblemDetail> handleUnauthorizedInSecrity(BaseException ex) {
        ProblemDetail probD = ex.problemDetail();
        return ResponseEntity.status(probD.getStatus()).body(probD);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDto> handleValidationException(MethodArgumentNotValidException ex) {
        ExceptionResponseDto response = new ExceptionResponseDto();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
        );
        response.setErros(errors);
        response.setMessage("Erro nos campos enviados");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> handleGlobalException(Exception ex) {
        ex.printStackTrace();
        ExceptionResponseDto response = new ExceptionResponseDto();
        response.setMessage("Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

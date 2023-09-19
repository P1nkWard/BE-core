package com.example.core.member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MemberExceptionHandler {
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        return createResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(MemberAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleMemberAlreadyExistsException(MemberAlreadyExistsException ex) {
        return createResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        StringBuilder error = new StringBuilder();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> error.append(String.format("{%s : %s},", e.getField(), e.getDefaultMessage())));
        error.delete(error.length() - 1, error.length()); // 마지막 쉼표와 공백 문자 제거

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "유효하지 않은 입력값입니다");
        responseBody.put("error", error.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    @ExceptionHandler(NotFoundMemberException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundMemberException(NotFoundMemberException ex) {
        return createResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    private ResponseEntity<Map<String, String>> createResponse(HttpStatus status, String message) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", message);

        return ResponseEntity.status(status).body(responseBody);
    }
}
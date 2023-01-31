package com.sandbox.server.exception;

import com.sandbox.server.api.thirdParty.RabbitMq;
import com.sandbox.server.model.result.RestError;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
@AllArgsConstructor
public class GlobalExceptionHandler {
    @ExceptionHandler(value = SandboxServerRuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestError SandboxServerRuntimeException(Exception e) {
        return new RestError("bad_request", e.getMessage());
    }

    @ExceptionHandler(value = SandBoxServerForbiddenException.class)
    public RestError SandboxForbiddenException(Exception e) {return new RestError("forbidden", "403", e.getMessage());}

    private final RabbitMq rabbitMq;
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestError ExceptionHandler(Exception e){
        String message ="[" + LocalDateTime.now().toString() + "] "+e.getMessage();
        rabbitMq.telegramSender(message);
        return new RestError("Internal_Server_Error", "500", e.getMessage());
    }
}

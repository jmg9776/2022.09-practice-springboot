package com.sandbox.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class SandBoxServerForbiddenException extends RuntimeException{

    public SandBoxServerForbiddenException(String id, String pw) {
        super ("{\"id\":\"" + id + "\",\"pw\":\"" + pw + "\"}");
    }

    public SandBoxServerForbiddenException(String text){
        super (text);
    }
}

package com.iade.mobilemechanics.models.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    public BadRequestException(String name) {
        super("The data you introduced isn't valide "+"'"+name+"'");
    }


    public BadRequestException(String numberplate, String a) {
        super("The data you introduced isn't valide "+"'"+numberplate+"'");
    }
}
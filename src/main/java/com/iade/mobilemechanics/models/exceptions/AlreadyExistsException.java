package com.iade.mobilemechanics.models.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String name, String emlType, String idname) {
        super(emlType + " with " + idname+" " + name + " already exist ");
    }
}
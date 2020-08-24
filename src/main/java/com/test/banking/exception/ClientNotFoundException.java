package com.test.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ClientNotFoundException extends ResponseStatusException {
    public ClientNotFoundException(int id) {
        super(HttpStatus.NOT_FOUND, "Клиент не найден. ID: " + id);
    }
}

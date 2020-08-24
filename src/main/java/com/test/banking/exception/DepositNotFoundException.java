package com.test.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DepositNotFoundException extends ResponseStatusException {
    public DepositNotFoundException(int id) {
        super(HttpStatus.NOT_FOUND, "Вклад не найден. ID: " + id);
    }
}

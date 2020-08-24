package com.test.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BankNotFoundException extends ResponseStatusException {
    public BankNotFoundException(int id) {
        super(HttpStatus.NOT_FOUND, "Банк не найден. ID: " + id);
    }
}

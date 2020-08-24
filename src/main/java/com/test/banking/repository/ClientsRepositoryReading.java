package com.test.banking.repository;

import com.test.banking.dto.request.ClientsFilter;
import com.test.banking.entity.Client;

import java.util.List;

public interface ClientsRepositoryReading {
    List<Client> findClients(ClientsFilter filter);
}

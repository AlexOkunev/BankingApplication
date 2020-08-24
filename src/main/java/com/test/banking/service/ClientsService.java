package com.test.banking.service;

import com.test.banking.dto.request.ClientsFilter;
import com.test.banking.entity.Client;

import java.util.List;

public interface ClientsService {
    Client createClient(Client client);

    void deleteClientById(int id);

    Client updateClient(int id, Client client);

    Client findClient(int id);

    List<Client> findClients(ClientsFilter filter);
}

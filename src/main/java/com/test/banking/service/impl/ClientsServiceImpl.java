package com.test.banking.service.impl;

import com.test.banking.dto.request.ClientsFilter;
import com.test.banking.entity.Client;
import com.test.banking.exception.ClientNotFoundException;
import com.test.banking.repository.ClientsRepository;
import com.test.banking.service.ClientsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClientsServiceImpl implements ClientsService {
    private ClientsRepository clientsRepository;

    public ClientsServiceImpl(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    @Override
    @Transactional
    public Client createClient(Client client) {
        return clientsRepository.save(client);
    }

    @Override
    @Transactional
    public void deleteClientById(int id) {
        clientsRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Client updateClient(int id, Client client) {
        Client oldClient = clientsRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        BeanUtils.copyProperties(client, oldClient, "id");
        return clientsRepository.save(oldClient);
    }

    @Override
    @Transactional
    public Client findClient(int id) {
        return clientsRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
    }

    @Override
    @Transactional
    public List<Client> findClients(ClientsFilter filter) {
        return clientsRepository.findClients(filter);
    }
}

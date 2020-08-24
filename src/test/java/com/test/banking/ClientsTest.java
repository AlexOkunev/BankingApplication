package com.test.banking;

import com.test.banking.entity.Client;
import com.test.banking.enumeration.ClientType;
import com.test.banking.repository.ClientsRepository;
import com.test.banking.service.ClientsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureDataJpa
public class ClientsTest {
    @Autowired
    private ClientsService clientsService;

    @Autowired
    private ClientsRepository clientsRepository;

    @Before
    public void init() {
        clientsRepository.deleteAll();
    }

    @Test
    public void testCreate() {
        Client client = new Client();
        client.setAddress("г. Пермь");
        client.setShortName("Иванов И.И.");
        client.setFullName("Иванов И.И.");
        client.setType(ClientType.IP);

        assertEquals(clientsRepository.count(), 0);

        clientsService.createClient(client);

        assertEquals(clientsRepository.count(), 1);
    }

    @Test
    public void testDelete() {
        Client client = new Client();
        client.setAddress("г. Пермь");
        client.setShortName("Иванов И.И.");
        client.setFullName("Иванов И.И.");
        client.setType(ClientType.IP);

        assertEquals(clientsRepository.count(), 0);
        client = clientsService.createClient(client);
        assertEquals(clientsRepository.count(), 1);
        clientsService.deleteClientById(client.getId());
        assertEquals(clientsRepository.count(), 0);
    }

    @Test
    public void testUpdate() {
        Client client = new Client();
        client.setAddress("г. Пермь");
        client.setShortName("Иванов И.И.");
        client.setFullName("Иванов И.И.");
        client.setType(ClientType.IP);

        assertEquals(clientsRepository.count(), 0);
        client = clientsService.createClient(client);
        assertEquals(clientsRepository.count(), 1);
        assertTrue(client.getFullName().equals("Иванов И.И."));
        assertTrue(client.getAddress().equals("г. Пермь"));

        Client client1 = new Client();
        client1.setAddress("г. Кунгур");
        client1.setShortName("Петров И.И.");
        client1.setFullName("Петров И.И.");
        client1.setType(ClientType.PHYSICAL);

        clientsService.updateClient(client.getId(), client1);

        Client updateClient = clientsRepository.findById(client.getId()).get();

        assertTrue(updateClient.getFullName().equals("Петров И.И."));
        assertTrue(updateClient.getAddress().equals("г. Кунгур"));
    }
}

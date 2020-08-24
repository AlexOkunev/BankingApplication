package com.test.banking;

import com.test.banking.dto.request.DepositUpdateRequestDto;
import com.test.banking.entity.Bank;
import com.test.banking.entity.Client;
import com.test.banking.entity.Deposit;
import com.test.banking.enumeration.ClientType;
import com.test.banking.repository.BanksRepository;
import com.test.banking.repository.ClientsRepository;
import com.test.banking.repository.DepositsRepository;
import com.test.banking.service.BanksService;
import com.test.banking.service.ClientsService;
import com.test.banking.service.DepositsService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureDataJpa
public class DepositsTest {
    @Autowired
    private ClientsService clientsService;

    @Autowired
    private BanksService banksService;

    @Autowired
    private DepositsService depositsService;

    @Autowired
    private DepositsRepository depositsRepository;

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private BanksRepository banksRepository;

    private static Bank BANK;
    private static Client CLIENT;

    @BeforeClass
    public static void init() {
        BANK = new Bank();
        BANK.setBik("041112233");
        BANK.setName("Сбербанк");

        CLIENT = new Client();
        CLIENT.setAddress("г. Пермь");
        CLIENT.setShortName("Иванов И.И.");
        CLIENT.setFullName("Иванов И.И.");
        CLIENT.setType(ClientType.IP);
    }

    @Before
    public void initEach() {
        depositsRepository.deleteAll();
        banksRepository.deleteAll();
        clientsRepository.deleteAll();
    }

    @Test
    public void testCreate() {
        Bank bank = banksService.createBank(BANK);
        Client client = clientsService.createClient(CLIENT);

        Deposit deposit = new Deposit();
        deposit.setPercent(10.0);
        deposit.setTerm(12);
        deposit.setClient(client);
        deposit.setBank(bank);
        deposit.setCreateDate(LocalDate.now());

        assertEquals(depositsRepository.count(), 0);

        depositsService.createDeposit(deposit);

        assertEquals(depositsRepository.count(), 1);
    }

    @Test
    public void testDelete() {
        Bank bank = banksService.createBank(BANK);
        Client client = clientsService.createClient(CLIENT);

        Deposit deposit = new Deposit();
        deposit.setPercent(10.0);
        deposit.setTerm(12);
        deposit.setClient(client);
        deposit.setBank(bank);
        deposit.setCreateDate(LocalDate.now());

        assertEquals(depositsRepository.count(), 0);
        depositsService.createDeposit(deposit);
        assertEquals(depositsRepository.count(), 1);
        depositsService.deleteDepositById(deposit.getId());
        assertEquals(depositsRepository.count(), 0);
    }

    @Test
    public void testUpdate() {
        Bank bank = banksService.createBank(BANK);
        Client client = clientsService.createClient(CLIENT);

        Deposit deposit = new Deposit();
        deposit.setPercent(10.0);
        deposit.setTerm(12);
        deposit.setClient(client);
        deposit.setBank(bank);
        deposit.setCreateDate(LocalDate.now());

        assertEquals(depositsRepository.count(), 0);
        Deposit createdDeposit = depositsService.createDeposit(deposit);
        assertEquals(depositsRepository.count(), 1);
        assertEquals(createdDeposit.getTerm(), deposit.getTerm());
        assertEquals(createdDeposit.getBank().getId(), deposit.getBank().getId());
        assertEquals(createdDeposit.getClient().getId(), deposit.getClient().getId());
        assertEquals(createdDeposit.getCreateDate(), deposit.getCreateDate());
        assertEquals(createdDeposit.getPercent(), deposit.getPercent());


        DepositUpdateRequestDto updateRequestDto = new DepositUpdateRequestDto();
        updateRequestDto.setPercent(20.0);
        updateRequestDto.setTerm(24);

        depositsService.updateDeposit(createdDeposit.getId(), updateRequestDto);

        Deposit updatedDeposit = depositsService.findDeposit(createdDeposit.getId());

        assertEquals(updatedDeposit.getTerm(), updateRequestDto.getTerm());
        assertEquals(updatedDeposit.getBank().getId(), deposit.getBank().getId());
        assertEquals(updatedDeposit.getClient().getId(), deposit.getClient().getId());
        assertEquals(updatedDeposit.getCreateDate(), deposit.getCreateDate());
        assertEquals(updatedDeposit.getPercent(), updateRequestDto.getPercent());
    }
}

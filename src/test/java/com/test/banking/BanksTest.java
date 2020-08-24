package com.test.banking;

import com.test.banking.entity.Bank;
import com.test.banking.repository.BanksRepository;
import com.test.banking.service.BanksService;
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
public class BanksTest {
    @Autowired
    private BanksService banksService;

    @Autowired
    private BanksRepository banksRepository;

    @Before
    public void init() {
        banksRepository.deleteAll();
    }

    @Test
    public void testCreate() {
        Bank bank = new Bank();
        bank.setBik("041112233");
        bank.setName("Сбербанк");

        assertEquals(banksRepository.count(), 0);

        banksService.createBank(bank);

        assertEquals(banksRepository.count(), 1);
    }

    @Test
    public void testDelete() {
        Bank bank = new Bank();
        bank.setBik("041112233");
        bank.setName("Сбербанк");

        assertEquals(banksRepository.count(), 0);
        bank = banksService.createBank(bank);
        assertEquals(banksRepository.count(), 1);
        banksService.deleteBankById(bank.getId());
        assertEquals(banksRepository.count(), 0);
    }

    @Test
    public void testUpdate() {
        Bank bank = new Bank();
        bank.setBik("041112233");
        bank.setName("Сбербанк");

        assertEquals(banksRepository.count(), 0);
        bank = banksService.createBank(bank);
        assertEquals(banksRepository.count(), 1);
        assertTrue(bank.getName().equals("Сбербанк"));

        Bank bank1 = new Bank();
        bank1.setBik(bank.getBik());
        bank1.setName("РОСБАНК");

        banksService.updateBank(bank.getId(), bank1);

        Bank updateBank = banksRepository.findById(bank.getId()).get();

        assertEquals(updateBank.getId(), bank.getId());
        assertEquals(updateBank.getName(), "РОСБАНК");
    }
}

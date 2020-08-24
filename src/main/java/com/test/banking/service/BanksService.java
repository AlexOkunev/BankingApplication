package com.test.banking.service;

import com.test.banking.dto.request.BanksFilter;
import com.test.banking.entity.Bank;

import java.util.List;

public interface BanksService {
    Bank createBank(Bank bank);

    void deleteBankById(int id);

    Bank updateBank(int id, Bank bank);

    Bank findBank(int id);

    List<Bank> findBanks(BanksFilter filter);
}

package com.test.banking.repository;

import com.test.banking.dto.request.BanksFilter;
import com.test.banking.entity.Bank;

import java.util.List;

public interface BanksRepositoryReading {
    List<Bank> findBanks(BanksFilter filter);
}

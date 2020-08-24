package com.test.banking.repository;

import com.test.banking.entity.Bank;
import org.springframework.data.repository.CrudRepository;

public interface BanksRepository extends CrudRepository<Bank, Integer>, BanksRepositoryReading {
}
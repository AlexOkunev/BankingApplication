package com.test.banking.repository;

import com.test.banking.entity.Bank;
import com.test.banking.entity.Deposit;
import org.springframework.data.repository.CrudRepository;

public interface DepositsRepository extends CrudRepository<Deposit, Integer>, DepositsRepositoryReading {
}
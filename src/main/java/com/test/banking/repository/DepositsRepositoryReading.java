package com.test.banking.repository;

import com.test.banking.dto.request.DepositsFilter;
import com.test.banking.entity.Deposit;

import java.util.List;

public interface DepositsRepositoryReading {
    List<Deposit> findDeposits(DepositsFilter filter);
}

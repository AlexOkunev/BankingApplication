package com.test.banking.service;

import com.test.banking.dto.request.DepositUpdateRequestDto;
import com.test.banking.dto.request.DepositsFilter;
import com.test.banking.entity.Deposit;

import java.util.List;

public interface DepositsService {
    Deposit createDeposit(Deposit deposit);

    void deleteDepositById(int id);

    Deposit updateDeposit(int id, DepositUpdateRequestDto updateRequest);

    Deposit findDeposit(int id);

    List<Deposit> findDeposits(DepositsFilter filter);
}

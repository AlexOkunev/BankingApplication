package com.test.banking.service.impl;

import com.test.banking.dto.request.DepositUpdateRequestDto;
import com.test.banking.dto.request.DepositsFilter;
import com.test.banking.entity.Deposit;
import com.test.banking.exception.DepositNotFoundException;
import com.test.banking.repository.DepositsRepository;
import com.test.banking.service.DepositsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DepositsServiceImpl implements DepositsService {
    private DepositsRepository depositsRepository;

    public DepositsServiceImpl(DepositsRepository depositsRepository) {
        this.depositsRepository = depositsRepository;
    }

    @Override
    @Transactional
    public Deposit createDeposit(Deposit deposit) {
        return depositsRepository.save(deposit);
    }

    @Override
    @Transactional
    public void deleteDepositById(int id) {
        depositsRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Deposit updateDeposit(int id, DepositUpdateRequestDto updateRequest) {
        Deposit oldDeposit = depositsRepository.findById(id).orElseThrow(() -> new DepositNotFoundException(id));
        oldDeposit.setTerm(updateRequest.getTerm());
        oldDeposit.setPercent(updateRequest.getPercent());
        return depositsRepository.save(oldDeposit);
    }

    @Override
    @Transactional
    public Deposit findDeposit(int id) {
        return depositsRepository.findById(id).orElseThrow(() -> new DepositNotFoundException(id));
    }

    @Override
    @Transactional
    public List<Deposit> findDeposits(DepositsFilter filter) {
        return depositsRepository.findDeposits(filter);
    }
}

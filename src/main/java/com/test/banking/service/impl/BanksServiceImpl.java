package com.test.banking.service.impl;

import com.test.banking.dto.request.BanksFilter;
import com.test.banking.entity.Bank;
import com.test.banking.exception.BankNotFoundException;
import com.test.banking.repository.BanksRepository;
import com.test.banking.service.BanksService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BanksServiceImpl implements BanksService {
    private BanksRepository banksRepository;

    public BanksServiceImpl(BanksRepository banksRepository) {
        this.banksRepository = banksRepository;
    }

    @Override
    @Transactional
    public Bank createBank(Bank bank) {
        return banksRepository.save(bank);
    }

    @Override
    @Transactional
    public void deleteBankById(int id) {
        banksRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Bank updateBank(int id, Bank bank) {
        Bank oldBank = banksRepository.findById(id).orElseThrow(() -> new BankNotFoundException(id));
        BeanUtils.copyProperties(bank, oldBank, "id");
        return banksRepository.save(oldBank);
    }

    @Override
    @Transactional
    public Bank findBank(int id) {
        return banksRepository.findById(id).orElseThrow(() -> new BankNotFoundException(id));
    }

    @Override
    @Transactional
    public List<Bank> findBanks(BanksFilter filter) {
        return banksRepository.findBanks(filter);
    }
}

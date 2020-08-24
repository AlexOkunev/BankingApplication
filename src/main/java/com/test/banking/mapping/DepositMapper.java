package com.test.banking.mapping;

import com.test.banking.dto.request.DepositCreateRequestDto;
import com.test.banking.entity.Bank;
import com.test.banking.entity.Client;
import com.test.banking.entity.Deposit;
import com.test.banking.service.BanksService;
import com.test.banking.service.ClientsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DepositMapper {
    private ModelMapper modelMapper;

    private BanksService banksService;

    private ClientsService clientsService;

    public DepositMapper(ModelMapper modelMapper, BanksService banksService, ClientsService clientsService) {
        this.modelMapper = modelMapper;
        this.banksService = banksService;
        this.clientsService = clientsService;
    }

    @PostConstruct
    public void init() {
        modelMapper.createTypeMap(DepositCreateRequestDto.class, Deposit.class)
                .addMappings(m -> m.using(ctx -> getBank((Integer) ctx.getSource())).map(DepositCreateRequestDto::getBankId, Deposit::setBank))
                .addMappings(m -> m.using(ctx -> getClient((Integer) ctx.getSource())).map(DepositCreateRequestDto::getClientId, Deposit::setClient));
    }

    private Client getClient(Integer source) {
        return clientsService.findClient(source);
    }

    private Bank getBank(Integer source) {
        return banksService.findBank(source);
    }
}

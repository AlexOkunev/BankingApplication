package com.test.banking.web;

import com.test.banking.dto.request.BankRequestDto;
import com.test.banking.dto.request.BanksFilter;
import com.test.banking.dto.request.BankRequestDto;
import com.test.banking.dto.request.BanksFilter;
import com.test.banking.dto.response.BankDto;
import com.test.banking.entity.Bank;
import com.test.banking.entity.Bank;
import com.test.banking.service.BanksService;
import com.test.banking.service.BanksService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/banks")
@Api(description = "Выполнение операций над банками")
@Validated
public class BanksController {
    private BanksService banksService;

    private ModelMapper modelMapper;

    public BanksController(BanksService banksService, ModelMapper modelMapper) {
        this.banksService = banksService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation("Создание банка")
    @PostMapping("/")
    public ResponseEntity<BankDto> createBank(@ApiParam("Запрос") @Valid @RequestBody BankRequestDto request) throws URISyntaxException {
        Bank bank = banksService.createBank(createEntity(request));
        return ResponseEntity.created(new URI("/banks/" + bank.getId())).body(createDTO(bank));
    }

    @ApiOperation("Редактирование банка")
    @PutMapping("/{id}")
    public ResponseEntity<BankDto> editBank(@ApiParam("ID") @PathVariable Integer id, @ApiParam("Запрос") @Valid @RequestBody BankRequestDto request) {
        Bank newBank = createEntity(request);
        Bank bank = banksService.updateBank(id, newBank);
        return ResponseEntity.ok(createDTO(bank));
    }

    @ApiOperation("Удаление банка")
    @DeleteMapping("/{id}")
    public ResponseEntity createBank(@ApiParam("ID") @PathVariable Integer id) {
        banksService.deleteBankById(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Получение списка банков")
    @GetMapping("/")
    public ResponseEntity<List<BankDto>> getBanks(@Valid BanksFilter filter) {
        List<Bank> banks = banksService.findBanks(filter);
        return ResponseEntity.ok(createDTOs(banks));
    }

    @ApiOperation("Получение банка")
    @GetMapping("/{id}")
    public ResponseEntity<BankDto> getBank(int id) {
        Bank bank = banksService.findBank(id);
        return ResponseEntity.ok(createDTO(bank));
    }

    private Bank createEntity(BankRequestDto dto) {
        return modelMapper.map(dto, Bank.class);
    }

    private BankDto createDTO(Bank bank) {
        return modelMapper.map(bank, BankDto.class);
    }

    private List<BankDto> createDTOs(List<Bank> banks) {
        return banks.stream().map(bank -> createDTO(bank)).collect(Collectors.toList());
    }
}

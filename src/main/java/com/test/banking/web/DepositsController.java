package com.test.banking.web;

import com.test.banking.dto.request.DepositCreateRequestDto;
import com.test.banking.dto.request.DepositUpdateRequestDto;
import com.test.banking.dto.request.DepositsFilter;
import com.test.banking.dto.response.DepositDto;
import com.test.banking.entity.Deposit;
import com.test.banking.service.DepositsService;
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
@RequestMapping("/deposits")
@Api(description = "Выполнение операций над вкладами")
@Validated
public class DepositsController {
    private DepositsService depositsService;

    private ModelMapper modelMapper;

    public DepositsController(DepositsService depositsService, ModelMapper modelMapper) {
        this.depositsService = depositsService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation("Создание вклада")
    @PostMapping("/")
    public ResponseEntity<DepositDto> createDeposit(@ApiParam("Запрос") @Valid @RequestBody DepositCreateRequestDto request) throws URISyntaxException {
        Deposit deposit = depositsService.createDeposit(createEntity(request));
        return ResponseEntity.created(new URI("/deposits/" + deposit.getId())).body(createDTO(deposit));
    }

    @ApiOperation("Редактирование вклада")
    @PutMapping("/{id}")
    public ResponseEntity<DepositDto> editDeposit(@ApiParam("ID") @PathVariable Integer id, @ApiParam("Запрос") @Valid @RequestBody DepositUpdateRequestDto request) {
        Deposit deposit = depositsService.updateDeposit(id, request);
        return ResponseEntity.ok(createDTO(deposit));
    }

    @ApiOperation("Удаление вклада")
    @DeleteMapping("/{id}")
    public ResponseEntity createDeposit(@ApiParam("ID") @PathVariable Integer id) {
        depositsService.deleteDepositById(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Получение списка вкладов")
    @GetMapping("/")
    public ResponseEntity<List<DepositDto>> getDeposits(@Valid DepositsFilter filter) {
        List<Deposit> deposits = depositsService.findDeposits(filter);
        return ResponseEntity.ok(createDTOs(deposits));
    }

    @ApiOperation("Получение вклада")
    @GetMapping("/{id}")
    public ResponseEntity<DepositDto> getDeposit(int id) {
        Deposit deposit = depositsService.findDeposit(id);
        return ResponseEntity.ok(createDTO(deposit));
    }

    private Deposit createEntity(DepositCreateRequestDto dto) {
        return modelMapper.map(dto, Deposit.class);
    }

    private DepositDto createDTO(Deposit deposit) {
        return modelMapper.map(deposit, DepositDto.class);
    }

    private List<DepositDto> createDTOs(List<Deposit> deposits) {
        return deposits.stream().map(deposit -> createDTO(deposit)).collect(Collectors.toList());
    }
}

package com.test.banking.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.test.banking.entity.Bank;
import com.test.banking.entity.Client;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@ApiModel("Вклад")
public class DepositDto {
    @ApiModelProperty(notes = "ID", required = true, example = "1", position = 0)
    private Integer id;

    @ApiModelProperty(notes = "Банк", required = true, example = "1", position = 1)
    private Bank bank;

    @ApiModelProperty(notes = "Клиент", required = true, example = "1", position = 2)
    private Client client;

    @ApiModelProperty(notes = "Дата открытия вклада", required = true, example = "10.07.2020", position = 3)
    @JsonFormat(pattern = "dd.MM.yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate createDate;

    @ApiModelProperty(notes = "Процент", required = true, example = "15.0", position = 4)
    private Double percent;

    @ApiModelProperty(notes = "Срок", required = true, example = "12", position = 4)
    private Integer term;
}

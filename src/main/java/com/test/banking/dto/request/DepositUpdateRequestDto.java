package com.test.banking.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@ApiModel("Запрос на изменение вклада")
public class DepositUpdateRequestDto {
    @ApiModelProperty(notes = "Процент", required = true, example = "15.0", position = 0)
    @NotNull(message = "Необходимо указать процент")
    @Positive(message = "Процент должен быть положительным числом")
    private Double percent;

    @ApiModelProperty(notes = "Срок", required = true, example = "12", position = 1)
    @NotNull(message = "Необходимо указать срок в месяцах")
    @Positive(message = "Срок должен быть положительным числом")
    private Integer term;
}

package com.test.banking.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.test.banking.enumeration.ClientType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@ApiModel("Запрос на создание вклада")
public class DepositCreateRequestDto {
    @ApiModelProperty(notes = "ID банка", required = true, example = "1", position = 0)
    @NotNull(message = "Необходимо указать банк")
    private Integer bankId;

    @ApiModelProperty(notes = "ID клиента", required = true, example = "1", position = 1)
    @NotNull(message = "Необходимо указать клиента")
    private Integer clientId;

    @ApiModelProperty(notes = "Дата открытия вклада", required = true, example = "10.07.2020", position = 3)
    @NotNull(message = "Необходимо указать дату открытия вклада")
    @JsonFormat(pattern = "dd.MM.yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate createDate;

    @ApiModelProperty(notes = "Процент", required = true, example = "15.0", position = 4)
    @NotNull(message = "Необходимо указать процент")
    @Positive(message = "Процент должен быть положительным числом")
    private Double percent;

    @ApiModelProperty(notes = "Срок", required = true, example = "12", position = 5)
    @NotNull(message = "Необходимо указать срок в месяцах")
    @Positive(message = "Срок должен быть положительным числом")
    private Integer term;
}

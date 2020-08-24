package com.test.banking.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ApiModel("Запрос на создание и редактирование банка")
public class BankRequestDto {
    @ApiModelProperty(notes = "Наименование", required = true, example = "Сбербанк", position = 0)
    @NotBlank(message = "Необходимо указать краткое наименование")
    private String name;

    @ApiModelProperty(notes = "БИК", required = true, example = "041112223", position = 1)
    @Pattern(regexp = "^04(\\d){7}$", message = "БИК должен состоять из 9-ти цифр и начинаться с 04")
    private String bik;
}

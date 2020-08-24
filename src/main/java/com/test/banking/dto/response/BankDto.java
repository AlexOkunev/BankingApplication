package com.test.banking.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BankDto {
    @ApiModelProperty(notes = "ID", example = "1", position = 0)
    private Integer id;

    @ApiModelProperty(notes = "Наименование", example = "Сбербанк", position = 1)
    private String name;

    @ApiModelProperty(notes = "БИК", example = "041112223", position = 2)
    private String bik;
}

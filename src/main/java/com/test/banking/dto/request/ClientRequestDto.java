package com.test.banking.dto.request;

import com.test.banking.enumeration.ClientType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("Запрос на создание и редактирование клиента")
public class ClientRequestDto {
    @ApiModelProperty(notes = "Краткое наименование", required = true, example = "ИП Иванов И.И.", position = 0)
    @NotBlank(message = "Необходимо указать краткое наименование")
    private String shortName;

    @ApiModelProperty(notes = "Полное наименование", required = true, example = "ИП Иванов И.И.", position = 1)
    @NotBlank(message = "Необходимо указать полное наименование")
    private String fullName;

    @ApiModelProperty(notes = "Адрес", required = true, example = "г. Пермь, ул. Ленина, 64", position = 2)
    @NotBlank(message = "Необходимо указать адрес")
    private String address;

    @ApiModelProperty(notes = "Тип клиента", required = true, example = "IP", position = 3)
    @NotNull(message = "Необходимо указать тип клиента")
    private ClientType type;
}

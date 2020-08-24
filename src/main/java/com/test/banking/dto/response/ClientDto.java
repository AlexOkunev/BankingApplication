package com.test.banking.dto.response;

import com.test.banking.enumeration.ClientType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ClientDto {
    @ApiModelProperty(notes = "ID", example = "1", position = 0)
    private Integer id;

    @ApiModelProperty(notes = "Краткое наименование", example = "ИП Иванов И.И.", position = 1)
    private String shortName;

    @ApiModelProperty(notes = "Полное наименование", example = "ИП Иванов И.И.", position = 2)
    private String fullName;

    @ApiModelProperty(notes = "Адрес", example = "г. Пермь, ул. Ленина, 64", position = 3)
    private String address;

    @ApiModelProperty(notes = "Тип клиента", example = "IP", position = 4)
    private ClientType type;
}

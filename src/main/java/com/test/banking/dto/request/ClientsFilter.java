package com.test.banking.dto.request;

import com.test.banking.enumeration.ClientType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Data
@ApiModel("Фильтр для клиентов")
public class ClientsFilter {
    @ApiParam("ID")
    private Integer id;

    @ApiParam("Краткое наименование")
    private String shortName;

    @ApiParam("Полное наименование")
    private String fullName;

    @ApiParam("Адрес")
    private String address;

    @ApiParam("Тип")
    private ClientType type;

    @ApiParam("Пагинация. Начало")
    @Min(0)
    private int pagingFirstResult;

    @ApiParam("Пагинация. Количество")
    @Positive
    private int pagingMaxResults;
}

package com.test.banking.dto.request;

import com.test.banking.enumeration.ClientType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Data
@ApiModel("Фильтр для клиентов")
public class BanksFilter {
    @ApiParam("ID")
    private Integer id;

    @ApiParam("Наименование")
    private String name;

    @ApiParam("БИК")
    private String bik;

    @ApiParam("Пагинация. Начало")
    @Min(0)
    private int pagingFirstResult;

    @ApiParam("Пагинация. Количество")
    @Positive
    private int pagingMaxResults;
}

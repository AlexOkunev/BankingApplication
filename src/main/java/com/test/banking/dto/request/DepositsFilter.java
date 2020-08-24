package com.test.banking.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@ApiModel("Фильтр для вкладов")
public class DepositsFilter {
    @ApiParam("ID")
    private Integer id;

    @ApiParam("Наименование банка")
    private String bankName;

    @ApiParam("ID банка")
    private Integer bankId;

    @ApiParam("Полное наименование клиента")
    private String clientFullName;

    @ApiParam("Краткое наименование клиента")
    private String clientShortName;

    @ApiParam("ID клиента")
    private Integer clientId;

    @ApiParam("Процент от")
    private Double percentMin;

    @ApiParam("Процент до")
    private Double percentMax;

    @ApiParam("Срок от")
    private Integer termMin;

    @ApiParam("Срок до")
    private Integer termMax;

    @ApiParam("Дата от")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateMin;

    @ApiParam("Дата до")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateMax;

    @ApiParam("Пагинация. Начало")
    @Min(0)
    private int pagingFirstResult;

    @ApiParam("Пагинация. Количество")
    @Positive
    private int pagingMaxResults;
}

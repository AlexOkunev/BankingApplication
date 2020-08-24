package com.test.banking.entity;

import com.test.banking.enumeration.ClientType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "banks")
@Data
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "banks_gen")
    @SequenceGenerator(name = "banks_gen", sequenceName = "banks_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "bank_name")
    private String name;

    @Column(name = "bik")
    private String bik;
}

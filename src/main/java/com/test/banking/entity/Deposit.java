package com.test.banking.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "deposits")
@Data
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deposit_gen")
    @SequenceGenerator(name = "deposit_gen", sequenceName = "deposit_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "percent")
    private Double percent;

    @Column(name = "term")
    private Integer term;
}

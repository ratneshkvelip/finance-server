package com.example.finance_server.Entities;

import com.example.finance_server.Enums.AccountType;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user_account", schema = "finance")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    private Integer balance;

    private LocalDate createdAt;
    private LocalDate updatedAt;

    private String accountUuid;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @OneToMany(mappedBy = "userAccount")
    private List<Transaction> transactions;


}

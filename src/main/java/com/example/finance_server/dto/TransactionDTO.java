package com.example.finance_server.dto;

import com.example.finance_server.Enums.Category;
import com.example.finance_server.Enums.PaymentMethod;
import com.example.finance_server.Enums.SubCategory;
import com.example.finance_server.Enums.TransactionType;

public record TransactionDTO (
        String Name,
        String category,
        String subCategory,
        String method,
        String transactionType,
        String description,
        Integer amount,
        String bankName
    ) {}




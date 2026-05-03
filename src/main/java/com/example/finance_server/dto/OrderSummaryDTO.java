package com.example.finance_server.dto;

import com.example.finance_server.Enums.Category;
import com.example.finance_server.Enums.PaymentMethod;
import com.example.finance_server.Enums.SubCategory;
import com.example.finance_server.Enums.TransactionType;

public record OrderSummaryDTO(
        Category category,
        SubCategory subCategory,
        PaymentMethod method,
        TransactionType transactionType,
        String description,
        Integer amount,
        String bankName
) {}


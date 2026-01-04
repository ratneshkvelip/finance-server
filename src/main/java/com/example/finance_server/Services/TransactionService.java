package com.example.finance_server.Services;

import com.example.finance_server.ErrorHandler.BusinessException;
import com.example.finance_server.Model.TransactionDTO;
import com.example.finance_server.Model.User;
import com.example.finance_server.Repository.TransactionRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionDTO> getOrders(int userId) {
        return transactionRepository.findOrdersByUserId(userId);
    }

    public String addUser(User user) {

        try {
            transactionRepository.addUser(user);
        } catch (DuplicateKeyException e) {
            throw new BusinessException("Email already exists", "USER_DUPLICATE");

        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Invalid user data", "USER_INVALID_DATA");
        }

        return "Successfully Added user.";
    }
}

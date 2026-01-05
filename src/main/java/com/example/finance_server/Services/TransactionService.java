package com.example.finance_server.Services;

import com.example.finance_server.ErrorHandler.BusinessException;
import com.example.finance_server.Model.TransactionReqBody;
import com.example.finance_server.Repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public String addTransaction(TransactionReqBody transaction, String userName) {
        int userId=0,accountId=0;
        try {
            userId= transactionRepository.getUserId(userName);
            accountId = transactionRepository.getAccount(transaction.getAccount());
        }catch (EmptyResultDataAccessException e){
            log.error("e: ", e);
        }

        try {
            int id=transactionRepository.addTransaction(transaction,userId,accountId);
        } catch (DataIntegrityViolationException e) {
            log.error("e: ", e);
            throw new BusinessException("Invalid user data", "INVALID_DATA");
        }catch(Exception e){
            log.error("e: ", e);
        }

        return "Successfully Added transaction.";
    }

    public List<TransactionReqBody> getAllTransactions(String userName) {
        return transactionRepository.getAllTransaction(userName);
    }
}

package com.example.finance_server.Services;

import com.example.finance_server.Entities.Transaction;
import com.example.finance_server.Entities.User;
import com.example.finance_server.Enums.Category;
import com.example.finance_server.Enums.PaymentMethod;
import com.example.finance_server.Enums.SubCategory;
import com.example.finance_server.Enums.TransactionType;
import com.example.finance_server.ErrorHandler.BusinessException;
import com.example.finance_server.Model.TransactionReqBody;
import com.example.finance_server.Repository.OrderRepository;
import com.example.finance_server.Repository.TransactionRepository;
import com.example.finance_server.Repository.UserAccountRepository;
import com.example.finance_server.Repository.UserRepositoryJPA;
import com.example.finance_server.dto.OrderSummaryDTO;
import com.example.finance_server.dto.TransactionDTO;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);
    @Autowired
    private  TransactionRepository transactionRepository;

    @Autowired
    private UserRepositoryJPA userRepositoryJPA;

    @Autowired
    private  OrderRepository orderRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

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

    public List<OrderSummaryDTO> getTrans(String userName){
        return orderRepository.fetchTrans(userName);
    }



    public List<TransactionDTO> getTransNative(String userName){
        List<TransactionDTO> rows = orderRepository.fetchTransNative(userName);

//        List<TransactionDTO> result = rows.stream()
//                .map(r -> new TransactionDTO(
//                        (String) r[0],
//                        (String) r[1],
//                        (String) r[2],
//                        (String) r[3],
//                        (String) r[4],
//                        (String) r[5],
//                        ((Number) r[6]).intValue(),
//                        (String) r[7]
//                ))
//                .toList();
        return rows;
    }



    public List<User> getTrans1(String userName){
        return orderRepository.findAll();
    }


    @Transactional
    public String addTransactionJpa(TransactionReqBody transactionReqBody,String userName,String accountUuid){
        Long userId=userRepositoryJPA.getUserId(userName);

        Long  accountId=userAccountRepository.getUserAccountId(accountUuid);

        try {
            orderRepository.insertTransaction(
                    userId,
                    accountId,
                    transactionReqBody.getCategory(),
                    transactionReqBody.getSubCategory(),
                    transactionReqBody.getMethod(),
                    transactionReqBody.getTransactionType(),
                    transactionReqBody.getDescription(),
                    transactionReqBody.getAmount()
            );
        }catch (Exception e){
            log.error("error --> ",e);
        }

        return "Added new Transaction;";
    }
}

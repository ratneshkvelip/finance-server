package com.example.finance_server.Controllers;

import com.example.finance_server.Entities.Transaction;
import com.example.finance_server.Entities.User;
import com.example.finance_server.Model.TransactionReqBody;
import com.example.finance_server.Services.TransactionService;
import com.example.finance_server.dto.OrderSummaryDTO;
import com.example.finance_server.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class PostTransaction {

    @Autowired
    private  TransactionService transactionService;



    @PostMapping("/transaction/{userName}")
    public String addTransaction(@RequestBody TransactionReqBody transactionReqBody,@PathVariable String userName){

        return transactionService.addTransaction(transactionReqBody,userName);
    }

    @GetMapping("/transaction/{userName}")
    public List<TransactionReqBody> getAllTransactions(@PathVariable String userName){

        return transactionService.getAllTransactions(userName);
    }

    @GetMapping("/transactions0/{userName}")
    public List<OrderSummaryDTO> getTrans(@PathVariable String userName){

        return transactionService.getTrans(userName);
    }

    @GetMapping("/transactionnative/{userName}")
    public List<TransactionDTO> getTransNative(@PathVariable String userName){

        return transactionService.getTransNative(userName);
    }


    @GetMapping("/transactions/{userName}")
    public List<User> getTrans1(@PathVariable String userName){

        return transactionService.getTrans1(userName);
    }

    @PostMapping("/transactionjpa/{userName}")
    public String addTransactionJPA(@RequestBody TransactionReqBody transactionReqBody,@PathVariable String userName,@RequestParam String accountUuid){

        return transactionService.addTransactionJpa(transactionReqBody,userName,accountUuid);
    }
}



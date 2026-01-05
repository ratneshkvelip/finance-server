package com.example.finance_server.Controllers;

import com.example.finance_server.Model.TransactionReqBody;
import com.example.finance_server.Services.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class PostTransaction {

    private final TransactionService transactionService;

    public PostTransaction(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping("/transaction/{userName}")
    public String addTransaction(@RequestBody TransactionReqBody transactionReqBody,@PathVariable String userName){

        return transactionService.addTransaction(transactionReqBody,userName);
    }

    @GetMapping("/transaction/{userName}")
    public List<TransactionReqBody> getAllTransactions(@PathVariable String userName){

        return transactionService.getAllTransactions(userName);
    }

}



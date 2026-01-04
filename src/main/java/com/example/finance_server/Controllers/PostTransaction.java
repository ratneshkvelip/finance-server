package com.example.finance_server.Controllers;

import com.example.finance_server.Model.TransactionDTO;
import com.example.finance_server.Model.User;
import com.example.finance_server.Services.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

class Transaction{
    public String category;
    public String subCategory;
    public String transactionType;
    public String description;
    public String method;
    public int amount;
}

@RestController
@RequestMapping("/v1")
public class PostTransaction {

    private final TransactionService transactionService;

    public PostTransaction(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/user/{userId}")
    public List<TransactionDTO> getOrders(@PathVariable int userId) {
        return transactionService.getOrders(userId);
    }

    @PostMapping("/user")
    public String addUser(@RequestBody User user){

        return transactionService.addUser(user);
    }

}



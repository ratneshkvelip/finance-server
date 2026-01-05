package com.example.finance_server.Controllers;

import com.example.finance_server.Model.User;
import com.example.finance_server.Model.UserCredential;
import com.example.finance_server.Services.RegisterNewUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class UserController {
    private final RegisterNewUser registerNewUser;

    public UserController(RegisterNewUser registerNewUser){
        this.registerNewUser=registerNewUser;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserCredential userCredential){
        //Implementation of login
        return "";
    }


    @PostMapping("/logout")
    public String logout(){
        //Logout implementation
        return "";
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody User user){
        return registerNewUser.register(user);

    }

}

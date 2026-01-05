package com.example.finance_server.Services;

import com.example.finance_server.ErrorHandler.BusinessException;
import com.example.finance_server.Model.UserDTO;
import com.example.finance_server.Model.User;
import com.example.finance_server.Repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;



import com.example.finance_server.Util.RandomStringGenerator;

import java.util.List;

@Service
public class RegisterNewUser {

    private final UserRepository userRepository;


    public RegisterNewUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getOrders(int userId) {
        return userRepository.findOrdersByUserId(userId);
    }


    public String register(User user){
        String uuid=RandomStringGenerator.generate(10);
        user.setUuid(uuid);
        System.out.println(uuid);

        try {
            userRepository.addUser(user);
        } catch (DuplicateKeyException e) {
            throw new BusinessException("Email already exists", "USER_DUPLICATE");

        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Invalid user data", "USER_INVALID_DATA");
        }

        return "Successfully Added user.";
    }





}

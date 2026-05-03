package com.example.finance_server.Repository;

import com.example.finance_server.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepositoryJPA extends JpaRepository<User,Long> {
    @Query("SELECT u.id FROM User u WHERE u.userName = :userName")
    Long getUserId(@Param("userName") String userName);
}

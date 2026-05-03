package com.example.finance_server.Repository;

import com.example.finance_server.Entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {

    @Query("SELECT ua.id FROM UserAccount ua WHERE ua.accountUuid = :accountUuid")
    Long getUserAccountId(@Param("accountUuid") String accountUuid);
}

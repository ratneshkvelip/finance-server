package com.example.finance_server.Repository;

import com.example.finance_server.Entities.Transaction;
import com.example.finance_server.Entities.User;
import com.example.finance_server.dto.OrderSummaryDTO;
import com.example.finance_server.dto.TransactionDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface OrderRepository extends JpaRepository<User, Long> {

    @Query("""
        SELECT new com.example.finance_server.dto.OrderSummaryDTO(
            t.category,
            t.subCategory,
            t.method,
            t.transactionType,
            t.description,
            t.amount,
            b.bankName
        )
        FROM User u
        JOIN u.transactions t
        JOIN t.userAccount ua
        JOIN ua.bank b
        WHERE u.userName = :userName
    """)
    List<OrderSummaryDTO> fetchTrans(@Param("userName") String userName);


    @Query(value="""
        select
                	ua.account_type as accountType,
                	t.category as category,
                	t.sub_category as subCategory,
                	t."method" as method,
                	t.transaction_type as transactionType,
                	t.description as description,
                	t.amount as amount,
                	b.bank_name as bankName
                from
                	finance."transaction" t
                inner join finance.user_account ua on
                	t.user_account_id = ua.id
                inner join finance.bank b on
                	ua.bank_id = b.id
                where
                	t.user_id =(
                	select
                		id
                	from
                		finance."user" u
                	where
                		u.user_name =:userName)
    """,
            nativeQuery=true)
    List<TransactionDTO> fetchTransNative(@Param("userName") String userName);

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO finance."transaction"
        (user_id, user_account_id, category, sub_category, method,
         transaction_type, description, amount, created_at)
        VALUES
        (:userId, :userAccountId, CAST(:category AS finance.categories),
                                 CAST(:subCategory AS finance.sub_categories),
                                 CAST(:method AS finance.payment_methods),
                                 CAST(:transactionType AS finance.transaction_types), :description, :amount, now())
        """,
            nativeQuery = true)
    int insertTransaction(
            @Param("userId") Long userId,
            @Param("userAccountId") Long userAccountId,
            @Param("category") String category,
            @Param("subCategory") String subCategory,
            @Param("method") String method,
            @Param("transactionType") String transactionType,
            @Param("description") String description,
            @Param("amount") Integer amount
    );

}


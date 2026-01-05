package com.example.finance_server.Repository;

import com.example.finance_server.Mapper.TransactionMapper;
import com.example.finance_server.Model.TransactionReqBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionRepository {
    private static final Logger log = LoggerFactory.getLogger(TransactionRepository.class);
    private final JdbcTemplate jdbcTemplate;

    public TransactionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer getUserId(String userName) {
        System.out.println(userName);
        Integer userId = 0;
        String sql = """
            SELECT
              u.id as userId
            from
                finance.user u
            where user_name=?
        """;

        try {
            userId = jdbcTemplate.queryForObject(
                    sql,
                    Integer.class,
                    userName
            );
        } catch (EmptyResultDataAccessException e) {
            log.error("e: ", e);
            throw e;
        }
        return userId;

    }

    public Integer getAccount(String accountUuid) {

        Integer accountId = 0;
        String sql = """
            select ua.id from finance.user_account ua where ua.account_uuid =?
        """;

        try {
            accountId = jdbcTemplate.queryForObject(
                    sql,
                    Integer.class,
                    accountUuid
            );
        } catch (EmptyResultDataAccessException e) {
            log.error("e: ", e);
            throw e;
        }
        return accountId;

    }


    public int addTransaction(TransactionReqBody transactionReqBody,int userId, int accountId) {

        String sql = """
           INSERT INTO
            finance."transaction"
                (user_id, user_account_id, category, sub_category, amount, "method", transaction_type, description, created_at)
            VALUES
            (?, ?, ?::finance.categories, ?::finance.sub_categories, ?, ?::finance.payment_methods, ?::finance.transaction_types, ?, now());
        """;

        return jdbcTemplate.update(
                sql,
                userId,
                accountId,
                transactionReqBody.getCategory(),
                transactionReqBody.getSubCategory(),
                transactionReqBody.getAmount(),
                transactionReqBody.getMethod(),
                transactionReqBody.getTransactionType(),
                transactionReqBody.getDescription()
        );

    }


    public List<TransactionReqBody> getAllTransaction(String userName) {

        String sql = """
            select b.bank_name as bank_name, t.category as category, t.sub_category as sub_category, t.amount as amount, t."method" as method, t.transaction_type as transaction_type, t.description as description
                    from finance."transaction" t\s
                    inner join finance.user_account ua on t.user_account_id =ua.id\s
                    inner join finance.bank b on ua.bank_id =b.id\s
                    where t.user_id =(select id from finance."user" u where u.user_name =?)
        """;

        return jdbcTemplate.query(
                sql,
                new TransactionMapper(),
                userName
        );
    }

}

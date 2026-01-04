package com.example.finance_server.Repository;

import com.example.finance_server.Mapper.TransactionMapper;
import com.example.finance_server.Model.TransactionDTO;
import com.example.finance_server.Model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionRepository {
    private final JdbcTemplate jdbcTemplate;

    public TransactionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TransactionDTO> findOrdersByUserId(int userId) {

        String sql = """
            SELECT
                u.name as name,
                u.email as email
            from
                finance.user u
            where id=?
        """;

        return jdbcTemplate.query(
                sql,
                new TransactionMapper(),
                userId
        );
    }

    public int addUser(User user) {

        String sql = """
           INSERT INTO
            finance."user"
            ( "uuid", user_name, name, mobile_number, email, "password", created_at, updated_at, last_logged_in)
           VALUES
            (?, ?, ?, ?, ?, ?, now(), now(), now());
        """;

        return jdbcTemplate.update(
                sql,
                user.getUuid(),
                user.getUserName(),
                user.getName(),
                user.getMobileNumber(),
                user.getEmail(),
                user.getPassword()
        );
    }
}

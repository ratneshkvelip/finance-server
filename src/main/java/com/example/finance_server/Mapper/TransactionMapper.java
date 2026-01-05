package com.example.finance_server.Mapper;

import com.example.finance_server.Model.TransactionReqBody;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionMapper  implements RowMapper<TransactionReqBody> {

    @Override
    public TransactionReqBody mapRow(ResultSet rs, int rowNum)
            throws SQLException {

        TransactionReqBody dto = new TransactionReqBody();
        dto.setAccount(rs.getString("bank_name"));
        dto.setCategory(rs.getString("category"));
        dto.setDescription(rs.getString("description"));
        dto.setSubCategory(rs.getString("sub_category"));
        dto.setMethod(rs.getString("method"));
        dto.setTransactionType(rs.getString("transaction_type"));
        dto.setAmount(rs.getInt("amount"));

        return dto;
    }

}
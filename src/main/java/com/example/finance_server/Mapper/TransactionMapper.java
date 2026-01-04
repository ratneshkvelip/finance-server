package com.example.finance_server.Mapper;

import com.example.finance_server.Model.TransactionDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionMapper  implements RowMapper<TransactionDTO> {

    @Override
    public TransactionDTO mapRow(ResultSet rs, int rowNum)
            throws SQLException {

        TransactionDTO dto = new TransactionDTO();
        dto.setName(rs.getString("name"));
        dto.setA1(rs.getString("email"));

        return dto;
    }

}
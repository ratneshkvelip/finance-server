package com.example.finance_server.Mapper;

import com.example.finance_server.Model.UserDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserDTO> {


    @Override
    public UserDTO mapRow(ResultSet rs, int rowNum)
            throws SQLException {

        UserDTO dto = new UserDTO();
        dto.setName(rs.getString("name"));
        dto.setA1(rs.getString("email"));

        return dto;
    }


}

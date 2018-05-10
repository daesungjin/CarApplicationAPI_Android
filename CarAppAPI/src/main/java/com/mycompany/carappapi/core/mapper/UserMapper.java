/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carappapi.core.mapper;

import com.mycompany.carappapi.core.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 *
 * @author User
 */
public class UserMapper implements ResultSetMapper<User>{
User user;
HashMap<String,Integer> tempMap = new HashMap<>();
    @Override
    public User map(int i, ResultSet rs, StatementContext sc) throws SQLException {
        user = new User();
        user.setPhoneNumber(rs.getString("phoneNumber"));
        user.setUserID(rs.getString("userID"));
        user.setUserName(rs.getString("userName"));
        return user;
    }
    
}

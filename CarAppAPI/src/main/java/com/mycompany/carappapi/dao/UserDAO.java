/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carappapi.dao;

import com.mycompany.carappapi.core.User;
import com.mycompany.carappapi.core.mapper.UserMapper;
import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 *
 * @author User
 */
@RegisterMapper(UserMapper.class)
public interface UserDAO {
    @SqlQuery("select * from user ")
    List<User> findAll();
    @SqlQuery("select * from user where userID = :userID")
    List<User> findByUserID(@Bind("userID") String userID);
    @SqlUpdate("insert into user values(:userID, :userName, :phoneNumber)")
    void create(@Bind("userID") String userID, @Bind(":userName")String userName, @Bind("phoneNumber") String phoneNumber);
    @SqlUpdate("delete from user where userID = :userID")
    void delete(@Bind("userID") String userID);
}

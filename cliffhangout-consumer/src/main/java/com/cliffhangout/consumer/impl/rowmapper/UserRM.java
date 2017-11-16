package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.beans.User;
import com.cliffhangout.consumer.contract.dao.ImageDao;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRM extends AbstractRM implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setSalt(rs.getString("salt"));
        user.setEmail(rs.getString("email"));
        user.setRole(rs.getString("role"));
        if(rs.getInt("image_id")!=0)
        {
            user.setImage(getDaoFactory().getImageDao().find(rs.getInt("image_id")));
        }
        return user;
    }
}

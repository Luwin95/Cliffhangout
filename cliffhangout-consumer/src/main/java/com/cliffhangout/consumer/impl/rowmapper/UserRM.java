package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.beans.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRM extends AbstractRM implements RowMapper<User> {
    private ImageRM imageRM;

    private ImageRM getImageRM() {
        return imageRM;
    }

    public void setImageRM(ImageRM imageRM) {
        this.imageRM = imageRM;
    }

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setRole(rs.getString("role"));
        user.setActive(rs.getBoolean("active"));
        if(rs.getInt("image_id")!=0)
        {
            user.setImage(getImageRM().mapRow(rs, rowNum));
        }
        return user;
    }
}

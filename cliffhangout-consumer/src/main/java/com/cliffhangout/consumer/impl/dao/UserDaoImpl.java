package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.User;
import com.cliffhangout.consumer.contract.dao.UserDao;
import com.cliffhangout.consumer.impl.rowmapper.UserRM;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.*;

public class UserDaoImpl extends AbstractDaoImpl implements UserDao {
    @Override
    public void create(User user){
        String vSQL = "INSERT INTO user_account(login, password, salt, email, role, image_id) VALUES(:login, :password, :salt, :email, :role, :image_id)";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("login", user.getLogin(), Types.VARCHAR);
        vParams.addValue("password", user.getPassword(), Types.VARCHAR);
        vParams.addValue("salt", "test", Types.VARCHAR);
        vParams.addValue("email", user.getEmail(), Types.VARCHAR);
        vParams.addValue("role", user.getRole(), Types.VARCHAR);
        if(user.getImage()!=null)
        {
            vParams.addValue("image_id", user.getImage().getId(), Types.INTEGER);
        }else{
            vParams.addValue("image_id", null, Types.NULL);
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        int vNbrLigneMaJ = vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void update(User user){
        String vSQL = "UPDATE  user_account SET login=:login, password=:password, salt=:salt, email=:email, role=:role, image_id=:image_id WHERE id=:id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("login", user.getLogin(), Types.VARCHAR);
        vParams.addValue("password", user.getPassword(), Types.VARCHAR);
        vParams.addValue("salt", user.getSalt(), Types.VARCHAR);
        vParams.addValue("email", user.getEmail(), Types.VARCHAR);
        vParams.addValue("role", user.getRole(), Types.VARCHAR);
        if(user.getImage().getId() !=0)
        {
            vParams.addValue("image_id", user.getImage().getId(), Types.INTEGER);
        }else{
            vParams.addValue("image_id", null, Types.NULL);
        }
        vParams.addValue("id", user.getId(), Types.INTEGER);

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void delete(User user){
        String vSQL = "DELETE FROM user_account WHERE id=:id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", user.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public User find(int id){
        try{
            MapSqlParameterSource vParams = new MapSqlParameterSource();
            StringBuilder vSQL= new StringBuilder("SELECT *, id AS user_id FROM user_account WHERE 1=1 ");
            if(id>0)
            {
                vSQL.append("AND id = :id");
                vParams.addValue("id", id);
            }
            NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
            RowMapper<User> vRowMapper = new UserRM();
            User user = vJdbcTemplate.queryForObject(vSQL.toString(), vParams, vRowMapper);
            return user;
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public User findByLogin(String login){
        try{
            MapSqlParameterSource vParams = new MapSqlParameterSource();
            StringBuilder vSQL= new StringBuilder("SELECT *, id AS user_id FROM user_account WHERE 1=1 ");
            if(!login.equals(""))
            {
                vSQL.append("AND login = :login");
                vParams.addValue("login", login);
            }
            NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
            RowMapper<User> vRowMapper = new UserRM();
            User user = vJdbcTemplate.queryForObject(vSQL.toString(), vParams, vRowMapper);
            return user;
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }
}

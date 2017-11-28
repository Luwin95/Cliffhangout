package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.User;
import com.cliffhangout.consumer.contract.dao.UserDao;
import com.cliffhangout.consumer.impl.rowmapper.UserRM;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.*;
import java.util.List;

public class UserDaoImpl extends AbstractDaoImpl implements UserDao {
    @Override
    public void create(User user){
        String vSQL = "INSERT INTO user_account(login, password, email, role, image_id) VALUES(:login, :password,:email, :role, :image_id)";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("login", user.getLogin(), Types.VARCHAR);
        vParams.addValue("password", user.getPassword(), Types.VARCHAR);
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
        String vSQL = "UPDATE  user_account SET login=:login, password=:password, email=:email, role=:role, image_id=:image_id WHERE user_account_id=:id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("login", user.getLogin(), Types.VARCHAR);
        vParams.addValue("password", user.getPassword(), Types.VARCHAR);
        vParams.addValue("email", user.getEmail(), Types.VARCHAR);
        vParams.addValue("role", user.getRole(), Types.VARCHAR);
        if(user.getImage() !=null)
        {
            if(user.getImage().getId() !=0)
            {
                vParams.addValue("image_id", user.getImage().getId(), Types.INTEGER);
            }
        }else{
            vParams.addValue("image_id", null, Types.NULL);
        }
        vParams.addValue("id", user.getId(), Types.INTEGER);

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void delete(User user){
        String vSQL = "DELETE FROM user_account WHERE user_account_id=:id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", user.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public User find(int id){
        try{
            MapSqlParameterSource vParams = new MapSqlParameterSource();
            StringBuilder vSQL= new StringBuilder("SELECT user_account.*, image.*, user_account.user_account_id AS user_id, image.image_id AS imageId FROM user_account LEFT JOIN image ON user_account.image_id=image.image_id WHERE 1=1 ");
            if(id>0)
            {
                vSQL.append("AND user_account_id = :id");
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
            StringBuilder vSQL= new StringBuilder("SELECT user_account.*, image.*, user_account.user_account_id AS user_id, image.image_id AS imageId FROM user_account LEFT JOIN image ON user_account.image_id=image.image_id WHERE 1=1 ");
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

    @Override
    public List<User> findAll() {
        try{
            StringBuilder vSQL= new StringBuilder("SELECT user_account.*, image.*, user_account.user_account_id AS user_id, image.image_id AS imageId FROM user_account LEFT JOIN image ON user_account.image_id=image.image_id ");
            JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
            RowMapper<User> vRowMapper = new UserRM();
            List<User> vList = vJdbcTemplate.query(vSQL.toString(),vRowMapper);
            return vList;
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}

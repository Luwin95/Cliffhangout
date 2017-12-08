package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.User;
import com.cliffhangout.consumer.contract.dao.UserDao;
import com.cliffhangout.consumer.impl.rowmapper.UserRM;
import org.springframework.dao.EmptyResultDataAccessException;


import java.sql.*;
import java.util.List;

public class UserDaoImpl extends AbstractDaoImpl implements UserDao {

    private UserRM userRM;

    protected UserRM getUserRM() {
        return userRM;
    }

    public void setUserRM(UserRM userRM) {
        this.userRM = userRM;
    }

    @Override
    public void create(User user){
        String vSQL = "INSERT INTO user_account(login, password, email, role, image_id, active) VALUES(:login, :password,:email, :role, :image_id, :active)";
        getvParams().addValue("login", user.getLogin(), Types.VARCHAR);
        getvParams().addValue("password", user.getPassword(), Types.VARCHAR);
        getvParams().addValue("email", user.getEmail(), Types.VARCHAR);
        getvParams().addValue("role", user.getRole(), Types.VARCHAR);
        getvParams().addValue("active", user.isActive(), Types.BOOLEAN);
        if(user.getImage()!=null)
        {
            getvParams().addValue("image_id", user.getImage().getId(), Types.INTEGER);
        }else{
            getvParams().addValue("image_id", null, Types.NULL);
        }
        int vNbrLigneMaJ = getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public void update(User user){
        String vSQL = "UPDATE  user_account SET login=:login, password=:password, email=:email, role=:role, image_id=:image_id, active=:active WHERE user_account_id=:id";
        getvParams().addValue("login", user.getLogin(), Types.VARCHAR);
        getvParams().addValue("password", user.getPassword(), Types.VARCHAR);
        getvParams().addValue("email", user.getEmail(), Types.VARCHAR);
        getvParams().addValue("role", user.getRole(), Types.VARCHAR);
        getvParams().addValue("active", user.isActive(), Types.BOOLEAN);
        if(user.getImage() !=null)
        {
            if(user.getImage().getId() !=0)
            {
                getvParams().addValue("image_id", user.getImage().getId(), Types.INTEGER);
            }
        }else{
            getvParams().addValue("image_id", null, Types.NULL);
        }
        getvParams().addValue("id", user.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public void delete(User user){
        String vSQL = "DELETE FROM user_account WHERE user_account_id=:id";
        getvParams().addValue("id", user.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public User find(int id){
        try{
            StringBuilder vSQL= new StringBuilder("SELECT user_account.*, image.*, user_account.user_account_id AS user_id, image.image_id AS imageId FROM user_account LEFT JOIN image ON user_account.image_id=image.image_id WHERE 1=1 ");
            if(id>0)
            {
                vSQL.append("AND user_account_id = :id");
                getvParams().addValue("id", id);
            }
            User user = getvNamedParameterJdbcTemplate().queryForObject(vSQL.toString(),getvParams(), getUserRM());
            return user;
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }



    @Override
    public User findByLoginActive(String login){
        try{
            StringBuilder vSQL= new StringBuilder("SELECT user_account.*, image.*, user_account.user_account_id AS user_id, image.image_id AS imageId FROM user_account LEFT JOIN image ON user_account.image_id=image.image_id WHERE 1=1 ");
            if(!login.equals(""))
            {
                vSQL.append("AND login = :login AND active=true");
                getvParams().addValue("login", login);
            }
            User user = getvNamedParameterJdbcTemplate().queryForObject(vSQL.toString(), getvParams(), getUserRM());
            return user;
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        try{
            StringBuilder vSQL= new StringBuilder("SELECT user_account.*, image.*, user_account.user_account_id AS user_id, image.image_id AS imageId FROM user_account LEFT JOIN image ON user_account.image_id=image.image_id ORDER BY user_account_id");
            List<User> vList = getvJdbcTemplate().query(vSQL.toString(),getUserRM());
            return vList;
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public boolean emailIsInDb(String email) {
        Integer count = getvJdbcTemplate().queryForObject(
                "SELECT COUNT(*) FROM user_account  WHERE email=?", Integer.class, email);
        return count != null && count > 0;
    }

    @Override
    public boolean loginIsInDb(String login) {
        Integer count = getvJdbcTemplate().queryForObject(
                "SELECT COUNT(*) FROM user_account  WHERE login=?", Integer.class, login);
        return count != null && count > 0;
    }
}

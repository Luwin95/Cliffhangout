package com.cliffhangout.dao;

import com.cliffhangout.beans.User;
import java.sql.*;

public class UserDaoImpl implements UserDao {
    private DaoFactory daoFactory;

    UserDaoImpl(DaoFactory daoFactory){
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(User user){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO user_account(login, password, salt, email, role, image_id) VALUES(?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getSalt());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getRole());
            preparedStatement.setInt(1, user.getImage().getId());

            preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public User find(int id){
        User user = new User();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT * FROM user_account WHERE id=?;");
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            if(resultat.next())
            {
                user.setId(resultat.getInt("id"));
                user.setLogin(resultat.getString("login"));
                user.setPassword(resultat.getString("password"));
                user.setSalt(resultat.getString("salt"));
                user.setEmail(resultat.getString("email"));
                user.setRole(resultat.getString("role"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }
}

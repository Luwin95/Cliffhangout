package com.cliffhangout.dao;

import com.cliffhangout.beans.User;
import java.sql.*;

public class UserDaoImpl implements UserDao {
    private DaoFactory daoFactory;

    UserDaoImpl(DaoFactory daoFactory){
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(User user) throws DaoException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO user_account(login, password, salt, email, role) VALUES(?, ?, ?, ?, ?);");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getSalt());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getRole());
            //preparedStatement.setInt(6, user.getImage().getId());

            preparedStatement.executeUpdate();
            connection.commit();
        }catch(SQLException e){
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
    }

    @Override
    public void update(User user) throws DaoException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE  user_account SET login=?, password=?, salt=?, email=?, role=? WHERE id=?;");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getSalt());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getRole());
            //preparedStatement.setInt(6, user.getImage().getId());
            preparedStatement.setInt(6, user.getId());

            preparedStatement.executeUpdate();
            connection.commit();
        }catch(SQLException e){
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
    }

    @Override
    public void delete(User user) throws DaoException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM user_account WHERE id=?;");
            preparedStatement.setInt(1, user.getId());

            preparedStatement.executeUpdate();
            connection.commit();
        }catch(SQLException e){
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }

    }

    @Override
    public User find(int id) throws DaoException{
        User user = new User();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user_account WHERE id=?;");
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            if(resultat.next())
            {
                user = buildUser(resultat);
            }
        }catch (SQLException e){
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
            e.printStackTrace();
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
        return user;
    }

    @Override
    public User findByLogin(String login) throws DaoException {
        User user = new User();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user_account WHERE login=?;");
            preparedStatement.setString(1, login);
            resultat = preparedStatement.executeQuery();
            if(resultat.next())
            {
                user = buildUser(resultat);
            }
        }catch (SQLException e){
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
            e.printStackTrace();
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
        return user;
    }

    @Override
    public User buildUser(ResultSet resultSet) throws DaoException {
        User user = new User();
        try{
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setSalt(resultSet.getString("salt"));
            user.setEmail(resultSet.getString("email"));
            user.setRole(resultSet.getString("role"));
        }catch(SQLException e)
        {
            e.printStackTrace();
        }

        return user;
    }
}

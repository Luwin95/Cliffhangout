package com.cliffhangout.dao;

import com.cliffhangout.beans.Length;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LengthDaoImpl implements LengthDao {
    private DaoFactory daoFactory;

    LengthDaoImpl(DaoFactory daoFactory){
        this.daoFactory =daoFactory;
    }

    @Override
    public void create(Length length) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO length(name, description, way_id) VALUES(?, ?, ?);");
            preparedStatement.setString(1, length.getName());
            preparedStatement.setString(2, length.getDescription());
            preparedStatement.setInt(3, length.getWay().getId());

            preparedStatement.executeUpdate();
            connection.commit();
        }
        catch(SQLException e){
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
    public void update(Length length) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE length SET name=?, description=?, way_id=? WHERE id=?;");
            preparedStatement.setString(1, length.getName());
            preparedStatement.setString(2, length.getDescription());
            preparedStatement.setInt(3, length.getWay().getId());
            preparedStatement.setInt(4, length.getId());

            preparedStatement.executeUpdate();
            connection.commit();
        }
        catch(SQLException e){
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
    public void delete(Length length) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM length WHERE id=?;");
            preparedStatement.setInt(1, length.getId());

            preparedStatement.executeUpdate();
            connection.commit();
        }
        catch(SQLException e){
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
}


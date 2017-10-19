package com.cliffhangout.dao;

import com.cliffhangout.beans.Way;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WayDaoImpl implements WayDao {
    private DaoFactory daoFactory;

    WayDaoImpl(DaoFactory daoFactory){
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(Way way) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO way(name, height, quotation, points_nb, site_id) VALUES(?, ?, ?, ?, ?);");
            preparedStatement.setString(1, way.getName());
            preparedStatement.setFloat(2, way.getHeight());
            preparedStatement.setString(3, way.getQuotation());
            preparedStatement.setInt(4, way.getPointsNb());
            preparedStatement.setInt(5, way.getSector().getId());

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
    public void update(Way way) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE sector SET name=?, height=?, quotation=?, points_nb=?, site_id=? WHERE id=?;");
            preparedStatement.setString(1, way.getName());
            preparedStatement.setFloat(2, way.getHeight());
            preparedStatement.setString(3, way.getQuotation());
            preparedStatement.setInt(4, way.getPointsNb());
            preparedStatement.setInt(5, way.getSector().getId());
            preparedStatement.setInt(5, way.getId());

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
    public void delete(Way way) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM way WHERE id=?;");
            preparedStatement.setInt(1, way.getId());

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



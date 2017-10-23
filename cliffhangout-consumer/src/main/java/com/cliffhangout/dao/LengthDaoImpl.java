package com.cliffhangout.dao;

import com.cliffhangout.beans.Length;
import com.cliffhangout.beans.Point;
import com.cliffhangout.beans.Way;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class LengthDaoImpl implements LengthDao {
    private DaoFactory daoFactory;
    private WayDao wayDao;

    LengthDaoImpl(DaoFactory daoFactory, WayDao wayDao){
        this.daoFactory =daoFactory;
        this.wayDao = wayDao;
    }

    @Override
    public void create(Length length) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO length(name, description, way_id) VALUES(?, ?, ?);",Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, length.getName());
            preparedStatement.setString(2, length.getDescription());
            preparedStatement.setInt(3, length.getWay().getId());

            preparedStatement.executeUpdate();
            connection.commit();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys())
            {
                if(generatedKeys.next())
                {
                    length.setId(generatedKeys.getInt(1));
                }else{
                    throw new SQLException("Creating length failed, no ID obtained");
                }
            }
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

    @Override
    public void deleteAllByWay(Way way) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM length WHERE way_id=?;");
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

    @Override
    public Length find(int id) throws DaoException {
        Length length = new Length();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM length WHERE id=?;");
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();

            while(resultat.next()){
                length = buildLength(resultat);
            }
        }catch (SQLException e){
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
        return length;
    }

    @Override
    public Set<Length> findAllByWay(Way way) throws DaoException {
        Set<Length> lengths = new HashSet<Length>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM length WHERE way_id=? ORDER BY id;");
            preparedStatement.setInt(1, way.getId());
            resultat = preparedStatement.executeQuery();

            while(resultat.next()){
                Length length = buildLength(resultat);
                lengths.add(length);
            }
        }catch (SQLException e){
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
        return lengths;
    }

    @Override
    public Length buildLength(ResultSet resultSet) throws DaoException {
        Length length = new Length();
        try{
            length.setId(resultSet.getInt("id"));
            length.setName(resultSet.getString("name"));
            length.setDescription(resultSet.getString("description"));
            Way way = wayDao.find(resultSet.getInt("way_id"));
            length.setWay(way);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return length;
    }
}


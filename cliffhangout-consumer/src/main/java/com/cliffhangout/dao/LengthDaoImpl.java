package com.cliffhangout.dao;

import com.cliffhangout.beans.Length;
import com.cliffhangout.beans.Point;
import com.cliffhangout.beans.Way;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LengthDaoImpl implements LengthDao {
    private DaoFactory daoFactory;
    private PointDao pointDao;

    LengthDaoImpl(DaoFactory daoFactory, PointDao pointDao){
        this.daoFactory =daoFactory;
        this.pointDao = pointDao;
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
                    for(Point point : length.getPoints())
                    {
                        pointDao.create(point);
                    }

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

            for(Point point : length.getPoints())
            {
                pointDao.update(point);
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
    public void delete(Length length) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            pointDao.deleteAllByLength(length);
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
            for(Length length : way.getLengths())
            {
                pointDao.deleteAllByLength(length);
            }
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
    public Length find(int id, Way way) throws DaoException {
        Length length = new Length(way);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM length WHERE id=?;");
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();

            while(resultat.next()){
                length = buildLength(resultat, way);
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
    public List<Length> findAllByWay(Way way) throws DaoException {
        List<Length> lengths = new ArrayList<Length>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM length WHERE way_id=? ORDER BY id;");
            preparedStatement.setInt(1, way.getId());
            resultat = preparedStatement.executeQuery();

            while(resultat.next()){
                Length length = buildLength(resultat, way);
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
    public Length buildLength(ResultSet resultSet, Way way) throws DaoException {
        Length length = new Length(way);
        try{
            length.setId(resultSet.getInt("id"));
            length.setName(resultSet.getString("name"));
            length.setDescription(resultSet.getString("description"));
            length.setPoints(pointDao.findAllByLength(length));

        }catch(SQLException e){
            e.printStackTrace();
        }
        return length;
    }
}


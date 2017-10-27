package com.cliffhangout.dao;

import com.cliffhangout.beans.Length;
import com.cliffhangout.beans.Sector;
import com.cliffhangout.beans.Way;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WayDaoImpl implements WayDao {
    private DaoFactory daoFactory;
    private LengthDao lengthDao;
    private QuotationDao quotationDao;

    WayDaoImpl(DaoFactory daoFactory, LengthDao lengthDao, QuotationDao quotationDao){
        this.daoFactory = daoFactory;
        this.lengthDao = lengthDao;
        this.quotationDao = quotationDao;
    }

    @Override
    public void create(Way way) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO way(name, height, quotation_difficulty, points_nb, sector_id) VALUES(?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, way.getName());
            preparedStatement.setDouble(2, way.getHeight());
            preparedStatement.setInt(3, way.getQuotation().getDifficulty());
            preparedStatement.setInt(4, way.getPointsNb());
            preparedStatement.setInt(5, way.getSector().getId());

            preparedStatement.executeUpdate();
            connection.commit();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys())
            {
                if(generatedKeys.next())
                {
                    way.setId(generatedKeys.getInt(1));
                    for(Length length : way.getLengths())
                    {
                        lengthDao.create(length);
                    }
                }else{
                    throw new SQLException("Creating way failed, no ID obtained");
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
            e.printStackTrace();
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
            preparedStatement = connection.prepareStatement("UPDATE way SET name=?, height=?, quotation_difficulty=?, points_nb=?, sector_id=? WHERE id=?;");
            preparedStatement.setString(1, way.getName());
            preparedStatement.setDouble(2, way.getHeight());
            preparedStatement.setInt(3, way.getQuotation().getDifficulty());
            preparedStatement.setInt(4, way.getPointsNb());
            preparedStatement.setInt(5, way.getSector().getId());
            preparedStatement.setInt(6, way.getId());
            preparedStatement.executeUpdate();
            connection.commit();

            for(Length length : way.getLengths())
            {
                lengthDao.update(length);
            }

        }
        catch(SQLException e){
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e2) {
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
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
    }

    @Override
    public void delete(Way way) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            lengthDao.deleteAllByWay(way);
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

    @Override
    public void deleteAllBySector(Sector sector) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            for(Way way : sector.getWays())
            {
                lengthDao.deleteAllByWay(way);
            }
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM way WHERE sector_id=?;");
            preparedStatement.setInt(1, sector.getId());
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
    public Way find(int id, Sector sector) throws DaoException {
        Way way = new Way(sector);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM way WHERE id=?;");
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();

            while(resultat.next()){
                way = buildWay(resultat, sector);
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
        return way;
    }

    @Override
    public List<Way> findAllBySector(Sector sector) throws DaoException {
        List<Way> ways = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM way WHERE sector_id=? ORDER BY id;");
            preparedStatement.setInt(1, sector.getId());
            resultat = preparedStatement.executeQuery();

            while(resultat.next()){
                Way way = buildWay(resultat, sector);
                ways.add(way);
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
        return ways;
    }

    @Override
    public Way buildWay(ResultSet resultat, Sector sector) throws DaoException {
        Way way = new Way(sector);
        try{
            way.setId(resultat.getInt("id"));
            way.setName(resultat.getString("name"));
            way.setHeight(resultat.getDouble("height"));
            way.setQuotation(quotationDao.find(resultat.getInt("quotation_difficulty")));
            way.setPointsNb(resultat.getInt("points_nb"));
            way.setLengths(lengthDao.findAllByWay(way));
        }catch(SQLException e){
            e.printStackTrace();
        }
        return way;
    }
}



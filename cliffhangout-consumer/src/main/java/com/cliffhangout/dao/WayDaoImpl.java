package com.cliffhangout.dao;

import com.cliffhangout.beans.Length;
import com.cliffhangout.beans.Sector;
import com.cliffhangout.beans.Way;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class WayDaoImpl implements WayDao {
    private DaoFactory daoFactory;
    private SectorDao sectorDao;

    WayDaoImpl(DaoFactory daoFactory,SectorDao sectorDao){
        this.daoFactory = daoFactory;
        this.sectorDao = sectorDao;
    }

    @Override
    public void create(Way way) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO way(name, height, quotation, points_nb, sector_id) VALUES(?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, way.getName());
            preparedStatement.setDouble(2, way.getHeight());
            preparedStatement.setString(3, way.getQuotation());
            preparedStatement.setInt(4, way.getPointsNb());
            preparedStatement.setInt(5, way.getSector().getId());

            preparedStatement.executeUpdate();
            connection.commit();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys())
            {
                if(generatedKeys.next())
                {
                    way.setId(generatedKeys.getInt(1));
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
            preparedStatement = connection.prepareStatement("UPDATE way SET name=?, height=?, quotation=?, points_nb=?, sector_id=? WHERE id=?;");
            preparedStatement.setString(1, way.getName());
            preparedStatement.setDouble(2, way.getHeight());
            preparedStatement.setString(3, way.getQuotation());
            preparedStatement.setInt(4, way.getPointsNb());
            preparedStatement.setInt(5, way.getSector().getId());
            preparedStatement.setInt(6, way.getId());
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
    public Way find(int id) throws DaoException {
        Way way = new Way();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM way WHERE id=?;");
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();

            while(resultat.next()){
                way = buildWay(resultat);
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
    public Set<Way> findAllBySector(Sector sector) throws DaoException {
        Set<Way> ways = new HashSet<Way>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM way WHERE sector_id=? ORDER BY id;");
            preparedStatement.setInt(1, sector.getId());
            resultat = preparedStatement.executeQuery();

            while(resultat.next()){
                Way way = buildWay(resultat);
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
    public Way buildWay(ResultSet resultat) throws DaoException {
        Way way = new Way();
        try{
            way.setId(resultat.getInt("id"));
            way.setName(resultat.getString("name"));
            way.setHeight(resultat.getDouble("height"));
            way.setQuotation(resultat.getString("quotation"));
            way.setPointsNb(resultat.getInt("points_nb"));
            Sector sector = sectorDao.find(resultat.getInt("sector_id"));
            way.setSector(sector);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return way;
    }
}



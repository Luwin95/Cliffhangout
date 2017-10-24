package com.cliffhangout.dao;

import com.cliffhangout.beans.Sector;
import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Way;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SectorDaoImpl implements SectorDao {

    private DaoFactory daoFactory;
    private WayDao wayDao;

    SectorDaoImpl(DaoFactory daoFactory, WayDao wayDao){
        this.daoFactory = daoFactory;
        this.wayDao = wayDao;
    }

    @Override
    public void create(Sector sector) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO sector(name, description, site_id) VALUES(?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, sector.getName());
            preparedStatement.setString(2, sector.getDescription());
            preparedStatement.setInt(3, sector.getSite().getId());

            preparedStatement.executeUpdate();
            connection.commit();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys())
            {
                if(generatedKeys.next())
                {
                    sector.setId(generatedKeys.getInt(1));
                    for(Way way : sector.getWays())
                    {
                        wayDao.create(way);
                    }
                }else{
                    throw new SQLException("Creating sector failed, no ID obtained");
                }
            }
        }catch(SQLException e){
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
    public void update(Sector sector) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE sector SET name=?, description=?, site_id=? WHERE id=?;");
            preparedStatement.setString(1, sector.getName());
            preparedStatement.setString(2, sector.getDescription());
            preparedStatement.setInt(3, sector.getSite().getId());
            preparedStatement.setInt(4, sector.getId());

            preparedStatement.executeUpdate();
            connection.commit();
            for(Way way : sector.getWays())
            {
                wayDao.update(way);
            }
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
    public void delete(Sector sector) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            wayDao.deleteAllBySector(sector);
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM sector WHERE id=?;");
            preparedStatement.setInt(1, sector.getId());
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
    public void deleteAllBySite(Site site) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            for(Sector sector : site.getSectors())
            {
                wayDao.deleteAllBySector(sector);
            }
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM sector WHERE site_id=?;");
            preparedStatement.setInt(1, site.getId());
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
    public Sector find(int id, Site site) throws DaoException {
        Sector sector = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM sector WHERE id=?;");
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();

            while(resultat.next()){
                sector = buildSector(resultat, site);
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
        return sector;
    }

    @Override
    public List<Sector> findAllBySite(Site site) throws DaoException {
        List<Sector> sectors = new ArrayList<Sector>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM sector WHERE site_id=? ORDER BY id;");
            preparedStatement.setInt(1, site.getId());
            resultat = preparedStatement.executeQuery();

            while(resultat.next()){
                Sector sector = buildSector(resultat, site);
                sectors.add(sector);
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
        return sectors;
    }

    @Override
    public Sector buildSector(ResultSet resultat, Site site) throws DaoException {
        Sector sector = new Sector(site);
        try{
            sector.setId(resultat.getInt("id"));
            sector.setName(resultat.getString("name"));
            sector.setDescription(resultat.getString("description"));
            sector.setWays(wayDao.findAllBySector(sector));
        }catch(SQLException e){
            e.printStackTrace();
        }
        return sector;
    }
}

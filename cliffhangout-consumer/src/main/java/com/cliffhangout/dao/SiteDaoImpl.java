package com.cliffhangout.dao;

import com.cliffhangout.beans.Sector;
import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Topo;
import com.cliffhangout.beans.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SiteDaoImpl implements SiteDao{

    private DaoFactory daoFactory;
    private UserDao userDao;

    SiteDaoImpl(DaoFactory daoFactory, UserDao userDao){
        this.daoFactory = daoFactory;
        this.userDao = userDao;
    }

    @Override
    public void create(Site site) throws DaoException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO site(name, description, location, postcode, latitude, longitude, user_account_id) VALUES(?, ?, ?, ?, ?, ?, ?);",Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, site.getName());
            preparedStatement.setString(2, site.getDescription());
            preparedStatement.setString(3, site.getLocation());
            preparedStatement.setInt(4, site.getPostcode());
            preparedStatement.setDouble(5, site.getLatitude());
            preparedStatement.setDouble(6, site.getLongitude());
            preparedStatement.setInt(7, site.getCreator().getId());

            preparedStatement.executeUpdate();
            connection.commit();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys())
            {
                if(generatedKeys.next())
                {
                    site.setId(generatedKeys.getInt(1));
                }else{
                    throw new SQLException("Creating site failed, no ID obtained");
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
    public void update(Site site) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE site SET name=?, description=?, location=?, postcode=?, latitude=?, longitude=?, user_account_id=? WHERE id=?;");
            preparedStatement.setString(1, site.getName());
            preparedStatement.setString(2, site.getDescription());
            preparedStatement.setString(3, site.getLocation());
            preparedStatement.setInt(4, site.getPostcode());
            preparedStatement.setDouble(5, site.getLatitude());
            preparedStatement.setDouble(6, site.getLongitude());
            preparedStatement.setInt(7, site.getCreator().getId());
            preparedStatement.setInt(8, site.getId());

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
    public void delete(Site site) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM site WHERE id=?;");
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
    public Site find(int id) throws DaoException{
        Site site = new Site();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM site WHERE id=?;");
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            while(resultat.next()){
                site = buildSite(resultat);
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
        return site;
    }

    @Override
    public List<Site> findAllSites() throws DaoException{
        List<Site> sites = new ArrayList<Site>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            resultat = statement.executeQuery("SELECT * FROM site ORDER BY id;");

            while(resultat.next()){
                Site site = buildSite(resultat);
                sites.add(site);
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
        return sites;
    }

    @Override
    public Set<Site> findAllByTopo(Topo topo) throws DaoException {
        Set<Site> sites = new HashSet<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM site_topo LEFT JOIN site on site.id = site_topo.site_id WHERE topo_id=? ORDER BY site.id;");
            preparedStatement.setInt(1, topo.getId());
            resultat = preparedStatement.executeQuery();
            while(resultat.next()){
                Site site = buildSite(resultat);
                sites.add(site);
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
        return sites;
    }

    @Override
    public Site buildSite(ResultSet resultat) throws DaoException {
        Site site = new Site();
        try{
            site.setId(resultat.getInt("id"));
            site.setName(resultat.getString("name"));
            site.setDescription(resultat.getString("description"));
            site.setLocation(resultat.getString("location"));
            site.setPostcode(resultat.getInt("postcode"));
            site.setLatitude(resultat.getFloat("latitude"));
            site.setLongitude(resultat.getFloat("longitude"));
            User user = userDao.find(resultat.getInt("user_account_id"));
            site.setCreator(user);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return site;
    }
}

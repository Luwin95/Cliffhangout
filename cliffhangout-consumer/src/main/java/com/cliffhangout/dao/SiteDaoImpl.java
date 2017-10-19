package com.cliffhangout.dao;

import com.cliffhangout.beans.Site;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SiteDaoImpl implements SiteDao{

    private DaoFactory daoFactory;

    SiteDaoImpl(DaoFactory daoFactory){
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(Site site) throws DaoException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO site(name, description, location, postcode, latitude, longitude, user_account_id) VALUES(?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, site.getName());
            preparedStatement.setString(2, site.getDescription());
            preparedStatement.setString(3, site.getLocation());
            preparedStatement.setInt(4, site.getPostcode());
            preparedStatement.setFloat(5, site.getLatitude());
            preparedStatement.setFloat(6, site.getLongitude());
            preparedStatement.setInt(7, site.getCreator().getId());

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
            preparedStatement.setFloat(5, site.getLatitude());
            preparedStatement.setFloat(6, site.getLongitude());
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
    public List<Site> findAllSites() throws DaoException{
        List<Site> sites = new ArrayList<Site>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            resultat = statement.executeQuery("SELECT * FROM sites;");

            while(resultat.next()){
                Site site = new Site();

                site.setName(resultat.getString("name"));
                site.setDescription(resultat.getString("description"));
                site.setLocation(resultat.getString("location"));
                site.setPostcode(resultat.getInt("postcode"));
                site.setLatitude(resultat.getFloat("latitude"));
                site.setLongitude(resultat.getFloat("longitude"));

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

}

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
    public void create(Site site){
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
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Site> findAllSites(){
        List<Site> sites = new ArrayList<Site>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try{
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
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
            e.printStackTrace();
        }
        return sites;
    }

}

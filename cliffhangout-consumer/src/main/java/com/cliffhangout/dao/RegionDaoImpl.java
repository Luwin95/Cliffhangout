package com.cliffhangout.dao;

import com.cliffhangout.beans.Region;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegionDaoImpl implements RegionDao{
    private DaoFactory daoFactory;

    public RegionDaoImpl(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    @Override
    public Region find(int id) throws DaoException {
        Region region = new Region();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM region WHERE region_id=?;");
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            while(resultat.next()){
                region = buildRegion(resultat);
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
        return region;
    }

    @Override
    public List<Region> findAll() throws DaoException {
        List<Region> regions = new ArrayList<Region>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            resultat = statement.executeQuery("SELECT * FROM region ORDER BY region_id;");

            while(resultat.next()){
                Region region = buildRegion(resultat);
                regions.add(region);
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
        return regions;
    }

    @Override
    public Region buildRegion(ResultSet resultSet) throws DaoException {
        Region region = new Region();
        try{
            region.setId(resultSet.getInt("region_id"));
            region.setName(resultSet.getString("region_name"));
        }catch(SQLException e){
            e.printStackTrace();
        }
        return region;
    }
}

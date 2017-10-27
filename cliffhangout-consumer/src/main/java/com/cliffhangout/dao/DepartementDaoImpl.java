package com.cliffhangout.dao;

import com.cliffhangout.beans.Departement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartementDaoImpl implements DepartementDao {
    private DaoFactory daoFactory;
    private RegionDao regionDao;

    public DepartementDaoImpl(DaoFactory daoFactory, RegionDao regionDao)
    {
        this.daoFactory = daoFactory;
        this.regionDao = regionDao;
    }


    @Override
    public Departement find(String code) throws DaoException {
        Departement departement = new Departement();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM departement WHERE departement_code= ?;");
            preparedStatement.setString(1, code);
            resultat = preparedStatement.executeQuery();
            while(resultat.next()){
                departement = buildDepartement(resultat);
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
        return departement;
    }

    @Override
    public List<Departement> findAll() throws DaoException {
        List<Departement> departements = new ArrayList<Departement>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            resultat = statement.executeQuery("SELECT * FROM departement ORDER BY departement_code;");

            while(resultat.next()){
                Departement departement = buildDepartement(resultat);
                departements.add(departement);
            }
        }catch (SQLException e){
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
        return departements;
    }

    @Override
    public Departement buildDepartement(ResultSet resultSet) throws DaoException {
        Departement departement = new Departement();
        try{
            departement.setCode(resultSet.getString("departement_code"));
            departement.setName(resultSet.getString("departement_name"));
            departement.setRegion(regionDao.find(resultSet.getInt("region_id")));
        }catch(SQLException e){
            e.printStackTrace();
        }
        return departement;
    }
}

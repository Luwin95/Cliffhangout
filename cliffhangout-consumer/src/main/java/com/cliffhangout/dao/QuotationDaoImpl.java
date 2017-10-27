package com.cliffhangout.dao;

import com.cliffhangout.beans.Quotation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuotationDaoImpl implements QuotationDao {
    private DaoFactory daoFactory;

    public QuotationDaoImpl(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    @Override
    public Quotation find(int difficulty) throws DaoException {
        Quotation quotation = new Quotation();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM quotation WHERE quotation_difficulty=? ;");
            preparedStatement.setInt(1, difficulty);
            resultat = preparedStatement.executeQuery();
            while(resultat.next()){
                quotation = buildQuotation(resultat);
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
        return quotation;
    }

    @Override
    public Quotation findByName(String name) throws DaoException {
        Quotation quotation = new Quotation();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM quotation WHERE quotation_name=? ;");
            preparedStatement.setString(1, name);
            resultat = preparedStatement.executeQuery();
            while(resultat.next()){
                quotation = buildQuotation(resultat);
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
        return quotation;
    }

    @Override
    public Quotation buildQuotation(ResultSet resultSet) throws DaoException {
        Quotation quotation = new Quotation();
        try{
            quotation.setDifficulty(resultSet.getInt("quotation_difficulty"));
            quotation.setName(resultSet.getString("quotation_name"));
        }catch(SQLException e){
            e.printStackTrace();
        }
        return quotation;
    }

}

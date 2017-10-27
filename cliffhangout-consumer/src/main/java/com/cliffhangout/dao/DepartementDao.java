package com.cliffhangout.dao;

import com.cliffhangout.beans.Departement;

import java.sql.ResultSet;
import java.util.List;

public interface DepartementDao {
    Departement find(String code) throws DaoException;
    List<Departement> findAll() throws DaoException;
    Departement buildDepartement(ResultSet resultSet) throws DaoException;
}

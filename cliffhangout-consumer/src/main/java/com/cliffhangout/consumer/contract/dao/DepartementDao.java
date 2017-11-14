package com.cliffhangout.consumer.contract.dao;

import com.cliffhangout.beans.Departement;
import com.cliffhangout.consumer.impl.dao.DaoException;

import java.sql.ResultSet;
import java.util.List;

public interface DepartementDao {
    Departement find(String code);
    List<Departement> findAll();
}

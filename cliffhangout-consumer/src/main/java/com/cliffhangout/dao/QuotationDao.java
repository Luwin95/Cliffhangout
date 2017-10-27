package com.cliffhangout.dao;

import com.cliffhangout.beans.Quotation;

import java.sql.ResultSet;

public interface QuotationDao {
    Quotation find(int difficulty) throws DaoException;
    Quotation findByName(String name) throws DaoException;
    Quotation buildQuotation(ResultSet resultSet) throws DaoException;
}

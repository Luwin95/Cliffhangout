package com.cliffhangout.consumer.contract.dao;

import com.cliffhangout.beans.Quotation;
import com.cliffhangout.consumer.impl.dao.DaoException;

import java.sql.ResultSet;

public interface QuotationDao {
    Quotation find(int difficulty);
    Quotation findByName(String name);
}

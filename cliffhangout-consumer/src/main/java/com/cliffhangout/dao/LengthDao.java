package com.cliffhangout.dao;

import com.cliffhangout.beans.Length;
import com.cliffhangout.beans.Way;

import java.sql.ResultSet;
import java.util.Set;

public interface LengthDao {
    void create(Length length) throws DaoException;
    void update(Length length) throws DaoException;
    void delete(Length length) throws DaoException;
    void deleteAllByWay(Way way) throws DaoException;
    Length find(int id) throws DaoException;
    Set<Length> findAllByWay(Way way) throws DaoException;
    Length buildLength(ResultSet resultSet) throws DaoException;
}

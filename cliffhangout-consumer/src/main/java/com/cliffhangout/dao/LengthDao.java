package com.cliffhangout.dao;

import com.cliffhangout.beans.Length;

public interface LengthDao {
    void create(Length length) throws DaoException;
    void update(Length length) throws DaoException;
    void delete(Length length) throws DaoException;
}

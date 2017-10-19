package com.cliffhangout.dao;

import com.cliffhangout.beans.Way;

public interface WayDao {
    void create(Way way) throws DaoException;
    void update(Way way) throws DaoException;
    void delete(Way way) throws DaoException;
}

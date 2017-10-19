package com.cliffhangout.dao;

import com.cliffhangout.beans.Point;

public interface PointDao {
    void create(Point point) throws DaoException;
    void update(Point point) throws DaoException;
    void delete(Point point) throws DaoException;
}

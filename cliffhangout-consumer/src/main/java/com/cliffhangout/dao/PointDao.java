package com.cliffhangout.dao;

import com.cliffhangout.beans.Length;
import com.cliffhangout.beans.Point;

import java.sql.ResultSet;
import java.util.Set;

public interface PointDao {
    void create(Point point) throws DaoException;
    void update(Point point) throws DaoException;
    void delete(Point point) throws DaoException;
    void deleteAllByLength(Length length) throws DaoException;
    Point find(int id) throws DaoException;
    Set<Point> findAllByLength(Length length) throws DaoException;
    Point buildPoint(ResultSet resultat) throws DaoException;
}

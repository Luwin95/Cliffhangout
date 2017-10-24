package com.cliffhangout.dao;

import com.cliffhangout.beans.Length;
import com.cliffhangout.beans.Point;

import java.sql.ResultSet;
import java.util.List;

public interface PointDao {
    void create(Point point) throws DaoException;
    void update(Point point) throws DaoException;
    void delete(Point point) throws DaoException;
    void deleteAllByLength(Length length) throws DaoException;
    Point find(int id, Length length) throws DaoException;
    List<Point> findAllByLength(Length length) throws DaoException;
    Point buildPoint(ResultSet resultat, Length length) throws DaoException;
}

package com.cliffhangout.consumer.contract.dao;

import com.cliffhangout.beans.Length;
import com.cliffhangout.beans.Point;
import com.cliffhangout.consumer.impl.dao.DaoException;

import java.sql.ResultSet;
import java.util.List;

public interface PointDao {
    void create(Point point);
    void update(Point point);
    void delete(Point point);
    void deleteAllByLength(Length length);
    Point find(int id);
    List<Point> findAllByLength(Length length);
}

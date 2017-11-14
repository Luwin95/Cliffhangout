package com.cliffhangout.consumer.contract.dao;

import com.cliffhangout.beans.Length;
import com.cliffhangout.beans.Way;
import com.cliffhangout.consumer.impl.dao.DaoException;

import java.sql.ResultSet;
import java.util.List;

public interface LengthDao {
    void create(Length length);
    void update(Length length);
    void delete(Length length);
    void deleteAllByWay(Way way);
    Length find(int id);
    List<Length> findAllByWay(Way way);
}

package com.cliffhangout.consumer.contract.dao;

import com.cliffhangout.beans.Topo;
import com.cliffhangout.consumer.impl.dao.DaoException;

import java.sql.ResultSet;

public interface TopoDao {
    void create(Topo topo);
    void update (Topo topo);
    void delete (Topo topo);
    Topo find(int id);
}

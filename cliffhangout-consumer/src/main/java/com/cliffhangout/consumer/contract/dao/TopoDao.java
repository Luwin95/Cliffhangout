package com.cliffhangout.consumer.contract.dao;

import com.cliffhangout.beans.Topo;
import com.cliffhangout.beans.User;
import com.cliffhangout.consumer.impl.dao.DaoException;

import java.sql.ResultSet;
import java.util.List;

public interface TopoDao {
    void create(Topo topo);
    void update (Topo topo);
    void delete (Topo topo);
    void deleteSiteTopo (Topo topo);
    Topo find(int id);
    List<Topo> findAllByUser(User user);
    List<Topo> findAllBorrowed();
}

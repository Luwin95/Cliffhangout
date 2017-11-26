package com.cliffhangout.consumer.contract.dao;

import com.cliffhangout.beans.Topo;
import com.cliffhangout.beans.User;
import com.cliffhangout.consumer.impl.dao.DaoException;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

public interface TopoDao {
    void create(Topo topo);
    void createBorrowing(Topo topo, Date startDate, Date endDate, User user);
    void update (Topo topo);
    void delete (Topo topo);
    void deleteSiteTopo (Topo topo);
    Topo find(int id);
    List<Topo> findAllByUser(User user);
    List<Topo> findAllBorrowed(User user);
}

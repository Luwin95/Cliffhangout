package com.cliffhangout.dao;

import com.cliffhangout.beans.Topo;

import java.sql.ResultSet;

public interface TopoDao {
    void create(Topo topo) throws DaoException;
    void update (Topo topo) throws DaoException;
    void delete (Topo topo) throws DaoException;
    Topo find(int id) throws DaoException;
    Topo buildTopo(ResultSet resultSet) throws DaoException;
}

package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.beans.Topo;
import com.cliffhangout.consumer.contract.dao.SiteDao;
import com.cliffhangout.consumer.contract.dao.UserDao;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TopoRM extends AbstractRM implements RowMapper<Topo> {

    @Override
    public Topo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Topo topo = new Topo();
        topo.setId(rs.getInt("topo_id"));
        topo.setName(rs.getString("name"));
        topo.setDescription(rs.getString("description"));
        topo.setFile(rs.getString("file"));
        topo.setBorrowed(rs.getBoolean("borrowed"));
        topo.setOwner(getDaoFactory().getUserDao().find(rs.getInt("user_account_id")));
        topo.setSites(getDaoFactory().getSiteDao().findAllByTopo(topo));
        return topo;
    }
}

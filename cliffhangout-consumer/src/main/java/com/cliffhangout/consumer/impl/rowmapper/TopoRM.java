package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.beans.Topo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TopoRM extends AbstractRM implements RowMapper<Topo> {
    private UserRM userRM;

    private UserRM getUserRM() {
        return userRM;
    }

    public void setUserRM(UserRM userRM) {
        this.userRM = userRM;
    }

    @Override
    public Topo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Topo topo = new Topo();
        topo.setId(rs.getInt("topo_id"));
        topo.setName(rs.getString("name"));
        topo.setDescription(rs.getString("description"));
        topo.setFile(rs.getString("file"));
        topo.setBorrowed(rs.getBoolean("borrowed"));
        topo.setOwner(getUserRM().mapRow(rs, rowNum));
        return topo;
    }
}

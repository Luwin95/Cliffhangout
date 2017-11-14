package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.beans.Topo;
import com.cliffhangout.consumer.contract.dao.SiteDao;
import com.cliffhangout.consumer.contract.dao.UserDao;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TopoRM implements RowMapper<Topo> {
    private UserDao userDao;
    private SiteDao siteDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public SiteDao getSiteDao() {
        return siteDao;
    }

    public void setSiteDao(SiteDao siteDao) {
        this.siteDao = siteDao;
    }

    @Override
    public Topo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Topo topo = new Topo();
        topo.setId(rs.getInt("id"));
        topo.setName(rs.getString("name"));
        topo.setDescription(rs.getString("description"));
        topo.setFile(rs.getString("file"));
        topo.setBorrowed(rs.getBoolean("borrowed"));
        topo.setOwner(userDao.find(rs.getInt("user_account_id")));
        topo.setSites(siteDao.findAllByTopo(topo));
        return null;
    }
}

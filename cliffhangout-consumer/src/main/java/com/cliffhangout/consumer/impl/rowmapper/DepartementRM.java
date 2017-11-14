package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.beans.Departement;
import com.cliffhangout.consumer.contract.dao.RegionDao;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartementRM implements RowMapper<Departement> {
    private RegionDao regionDao;

    public RegionDao getRegionDao() {
        return regionDao;
    }

    public void setRegionDao(RegionDao regionDao) {
        this.regionDao = regionDao;
    }

    @Override
    public Departement mapRow(ResultSet rs, int rowNum) throws SQLException {
        Departement departement = new Departement();
        departement.setCode(rs.getString("departement_code"));
        departement.setName(rs.getString("departement_name"));
        departement.setRegion(regionDao.find(rs.getInt("region_id")));
        return departement;
    }
}

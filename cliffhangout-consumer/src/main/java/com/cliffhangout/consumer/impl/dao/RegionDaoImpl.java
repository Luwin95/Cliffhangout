package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.Region;
import com.cliffhangout.consumer.contract.dao.RegionDao;
import com.cliffhangout.consumer.impl.rowmapper.RegionRM;
import java.util.List;

public class RegionDaoImpl extends AbstractDaoImpl implements RegionDao {
    private RegionRM regionRM;

    protected RegionRM getRegionRM() {
        return regionRM;
    }

    public void setRegionRM(RegionRM regionRM) {
        this.regionRM = regionRM;
    }

    @Override
    public Region find(int id){
        StringBuilder vSQL= new StringBuilder("SELECT * FROM region WHERE 1=1 ");
        if(id>0)
        {
            vSQL.append("AND region_id = :id");
            getvParams().addValue("id", id);
        }
        Region region = getvNamedParameterJdbcTemplate().queryForObject(vSQL.toString(), getvParams(), getRegionRM());
        return region;
    }

    @Override
    public List<Region> findAll(){
        String vSQL= "SELECT * FROM region ORDER BY region_id";
        List<Region> vList = getvNamedParameterJdbcTemplate().query(vSQL,getvParams(),getRegionRM());
        return vList;
    }

}

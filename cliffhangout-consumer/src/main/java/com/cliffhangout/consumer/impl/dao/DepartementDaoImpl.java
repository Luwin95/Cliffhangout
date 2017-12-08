package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.Departement;
import com.cliffhangout.consumer.contract.dao.DepartementDao;
import com.cliffhangout.consumer.impl.rowmapper.DepartementRM;
import java.util.List;

public class DepartementDaoImpl extends AbstractDaoImpl implements DepartementDao {
    private DepartementRM departementRM;

    protected DepartementRM getDepartementRM() {
        return departementRM;
    }

    public void setDepartementRM(DepartementRM departementRM) {
        this.departementRM = departementRM;
    }

    @Override
    public Departement find(String code){
        StringBuilder vSQL= new StringBuilder("SELECT * FROM departement LEFT JOIN region ON region.region_id = departement.region_id WHERE 1=1 ");
        if(code != null && !code.equals(""))
        {
            vSQL.append("AND departement_code=:code");
            getvParams().addValue("code", code);
        }
        Departement departement= getvNamedParameterJdbcTemplate().queryForObject(vSQL.toString(), getvParams(), getDepartementRM());
        return departement;
    }

    @Override
    public List<Departement> findAll(){
        String vSQL= "SELECT * FROM departement LEFT JOIN region ON region.region_id = departement.region_id  ORDER BY departement_code";
        List<Departement> vList = getvNamedParameterJdbcTemplate().query(vSQL,getvParams(),getDepartementRM());
        return vList;
    }
}

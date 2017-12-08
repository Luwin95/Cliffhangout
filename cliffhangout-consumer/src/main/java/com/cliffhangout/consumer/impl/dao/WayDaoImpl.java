package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.Sector;
import com.cliffhangout.beans.Way;
import com.cliffhangout.consumer.contract.dao.WayDao;
import com.cliffhangout.consumer.impl.rowmapper.WayRM;
import java.sql.*;
import java.util.List;

public class WayDaoImpl extends AbstractDaoImpl implements WayDao {
    private WayRM wayRM;

    protected WayRM getWayRM() {
        return wayRM;
    }

    public void setWayRM(WayRM wayRM) {
        this.wayRM = wayRM;
    }

    @Override
    public void create(Way way){
        String vSQL = "INSERT INTO way(name, height, quotation_difficulty, points_nb, sector_id) VALUES(:name, :height, :quotation_difficulty, :points_nb, :sector_id)";
        getvParams().addValue("name", way.getName(), Types.VARCHAR);
        getvParams().addValue("height", way.getHeight(), Types.REAL);
        getvParams().addValue("quotation_difficulty", way.getQuotation().getDifficulty(), Types.INTEGER);
        getvParams().addValue("points_nb", way.getPointsNb(), Types.INTEGER);
        getvParams().addValue("sector_id", way.getSectorId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams(), getvKeyHolder(), new String[] {"way_id"});
        way.setId((int) getvKeyHolder().getKey());
    }

    @Override
    public void update(Way way){
        String vSQL = "UPDATE way SET name=:name, height=:height, quotation_difficulty=:quotation_difficulty, points_nb=:points_nb, sector_id=:sector_id WHERE way_id=:id";
        getvParams().addValue("name", way.getName(), Types.VARCHAR);
        getvParams().addValue("height", way.getHeight(), Types.REAL);
        getvParams().addValue("quotation_difficulty", way.getQuotation().getDifficulty(), Types.INTEGER);
        getvParams().addValue("points_nb", way.getPointsNb(), Types.INTEGER);
        getvParams().addValue("sector_id", way.getSectorId(), Types.INTEGER);
        getvParams().addValue("id", way.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public void delete(Way way){
        String vSQL = "DELETE FROM way WHERE way_id=:id";
        getvParams().addValue("id", way.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public void deleteAllBySector(Sector sector){
        String vSQL = "DELETE FROM way WHERE sector_id=:sector_id";
        getvParams().addValue("sector_id", sector.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public Way find(int id, Sector sector){
        StringBuilder vSQL= new StringBuilder("SELECT * FROM way WHERE 1=1 ");
        if(id>0)
        {
            vSQL.append("AND way_id = :id");
            getvParams().addValue("id", id);
        }
        Way way = getvNamedParameterJdbcTemplate().queryForObject(vSQL.toString(), getvParams(), getWayRM());
        return way;
    }

    @Override
    public List<Way> findAllBySector(Sector sector){
        StringBuilder vSQL= new StringBuilder("SELECT * FROM way LEFT JOIN quotation ON quotation.quotation_difficulty = way.quotation_difficulty WHERE 1=1 ");
        if(sector != null)
        {
            if(sector.getId()!=0)
            {
                vSQL.append("AND sector_id=:sector_id ORDER BY way_id");
                getvParams().addValue("sector_id", sector.getId());
            }
        }
        List<Way> vList = getvNamedParameterJdbcTemplate().query(vSQL.toString(),getvParams(),getWayRM());
        return vList;
    }
}



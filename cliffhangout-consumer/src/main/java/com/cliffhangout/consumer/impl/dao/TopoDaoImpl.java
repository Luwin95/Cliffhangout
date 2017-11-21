package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Topo;
import com.cliffhangout.beans.User;
import com.cliffhangout.consumer.contract.dao.SiteDao;
import com.cliffhangout.consumer.contract.dao.TopoDao;
import com.cliffhangout.consumer.contract.dao.UserDao;
import com.cliffhangout.consumer.impl.rowmapper.TopoRM;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.*;
import java.util.Set;

public class TopoDaoImpl extends AbstractDaoImpl implements TopoDao {

    @Override
    public void create(Topo topo){
        String vSQL = "INSERT INTO topo(name, description, file, borrowed, user_account_id) VALUES(:name, :description, :file, :borrowed, :user_account_id)";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("name", topo.getName(), Types.VARCHAR);
        vParams.addValue("description", topo.getDescription(), Types.VARCHAR);
        vParams.addValue("file", topo.getFile(), Types.VARCHAR);
        vParams.addValue("borrowed", topo.isBorrowed(), Types.BOOLEAN);
        vParams.addValue("user_account_id", topo.getOwner().getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void update(Topo topo){
        String vSQL = "UPDATE topo SET name=:name, description=:description, file=:file, borrowed=:borrowed, user_account_id=:user_account_id WHERE topo_id=:id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("name", topo.getName(), Types.VARCHAR);
        vParams.addValue("description", topo.getDescription(), Types.VARCHAR);
        vParams.addValue("file", topo.getFile(), Types.VARCHAR);
        vParams.addValue("borrowed", topo.isBorrowed(), Types.BOOLEAN);
        vParams.addValue("user_account_id", topo.getOwner().getId(), Types.INTEGER);
        vParams.addValue("id", topo.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void delete(Topo topo){
        String vSQL = "DELETE FROM topo WHERE topo_id=:id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", topo.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public Topo find(int id){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT * FROM length WHERE 1=1 ");
        if(id>0)
        {
            vSQL.append("AND topo_id = :id");
            vParams.addValue("id", id);
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Topo> vRowMapper = new TopoRM();
        Topo topo = vJdbcTemplate.queryForObject(vSQL.toString(), vParams, vRowMapper);
        return topo;
    }
}

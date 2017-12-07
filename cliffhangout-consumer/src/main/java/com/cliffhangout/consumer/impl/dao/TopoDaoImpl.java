package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Topo;
import com.cliffhangout.beans.User;
import com.cliffhangout.consumer.contract.dao.TopoDao;
import com.cliffhangout.consumer.impl.rowmapper.TopoRM;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class TopoDaoImpl extends AbstractDaoImpl implements TopoDao {

    @Override
    public void create(Topo topo){
        String vSQL = "INSERT INTO topo(name, description, file, borrowed, user_account_id) VALUES(:name, :description, :file, :borrowed, :user_account_id)";
        getvParams().addValue("name", topo.getName(), Types.VARCHAR);
        getvParams().addValue("description", topo.getDescription(), Types.VARCHAR);
        getvParams().addValue("file", topo.getFile(), Types.VARCHAR);
        getvParams().addValue("borrowed", topo.isBorrowed(), Types.BOOLEAN);
        getvParams().addValue("user_account_id", topo.getOwner().getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams(), getvKeyHolder(), new String[] {"topo_id"});
        topo.setId((int)getvKeyHolder().getKey());
    }

    @Override
    public void createBorrowing(Topo topo, Date startDate, Date endDate, User user) {
        String vSQL = "INSERT INTO topo_borrowing(topo_id, user_account_id, start_date, end_date) VALUES(:topo_id, :user_account_id, :start_date, :end_date)";
        getvParams().addValue("topo_id", topo.getId(), Types.INTEGER);
        getvParams().addValue("user_account_id", user.getId(), Types.INTEGER);
        getvParams().addValue("start_date", startDate, Types.DATE);
        getvParams().addValue("end_date", endDate, Types.DATE);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public void update(Topo topo){
        String vSQL = "UPDATE topo SET name=:name, description=:description, file=:file, borrowed=:borrowed, user_account_id=:user_account_id WHERE topo_id=:id";
        getvParams().addValue("name", topo.getName(), Types.VARCHAR);
        getvParams().addValue("description", topo.getDescription(), Types.VARCHAR);
        getvParams().addValue("file", topo.getFile(), Types.VARCHAR);
        getvParams().addValue("borrowed", topo.isBorrowed(), Types.BOOLEAN);
        getvParams().addValue("user_account_id", topo.getOwner().getId(), Types.INTEGER);
        getvParams().addValue("id", topo.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public void delete(Topo topo){
        String vSQL = "DELETE FROM topo WHERE topo_id=:id";
        getvParams().addValue("id", topo.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public void deleteSiteTopo(Topo topo) {
        String vSQL = "DELETE FROM site_topo WHERE topo_id=:id";
        getvParams().addValue("id", topo.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public Topo find(int id){
        StringBuilder vSQL= new StringBuilder("SELECT topo.*, user_account.*, user_account.user_account_id AS user_id, image.*, image.image_id AS imageId FROM topo " +
                "LEFT JOIN user_account ON topo.user_account_id = user_account.user_account_id " +
                "LEFT JOIN image ON user_account.image_id = image.image_id " +
                "WHERE 1=1 ");
        if(id>0)
        {
            vSQL.append("AND topo_id = :id");
            getvParams().addValue("id", id);
        }
        RowMapper<Topo> vRowMapper = new TopoRM();
        Topo topo = getvNamedParameterJdbcTemplate().queryForObject(vSQL.toString(), getvParams(), vRowMapper);
        return topo;
    }

    @Override
    public List<Topo> findAll() {
        try{
            StringBuilder vSQL= new StringBuilder("SELECT topo.*, user_account.*, user_account.user_account_id AS user_id, image.*, image.image_id AS imageId " +
                    "FROM topo " +
                    "LEFT JOIN user_account ON topo.user_account_id = user_account.user_account_id " +
                    "LEFT JOIN image ON user_account.image_id = image.image_id ");
            RowMapper<Topo> vRowMapper = new TopoRM();
            List<Topo> vList = getvJdbcTemplate().query(vSQL.toString(),vRowMapper);
            return vList;
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Topo> findAllByUser(User user) {
        StringBuilder vSQL= new StringBuilder("SELECT topo.*, user_account.*, image.*, image.image_id AS imageId, user_account.user_account_id AS user_id " +
                "FROM topo " +
                "LEFT JOIN user_account ON topo.user_account_id = user_account.user_account_id " +
                "LEFT JOIN image ON user_account.image_id = image.image_id " +
                "WHERE 1=1 ");
        if(user != null)
        {
            if(user.getId()!=0)
            {
                vSQL.append("AND topo.user_account_id=:user_id ORDER BY topo.topo_id");
                getvParams().addValue("user_id", user.getId());
            }
        }
        RowMapper<Topo> vRowMapper = new TopoRM();
        List<Topo> vList = getvNamedParameterJdbcTemplate().query(vSQL.toString(),getvParams(),vRowMapper);
        return vList;
    }

    @Override
    public List<Topo> findAllBySite(Site site) {
        StringBuilder vSQL= new StringBuilder("SELECT topo.*, user_account.*, image.*, image.image_id AS imageId, user_account.user_account_id AS user_id " +
                "FROM site_topo " +
                "LEFT JOIN topo ON site_topo.topo_id = topo.topo_id " +
                "LEFT JOIN user_account ON topo.user_account_id = user_account.user_account_id " +
                "LEFT JOIN image ON user_account.image_id = image.image_id " +
                "WHERE 1=1 ");
        if(site != null)
        {
            if(site.getId()!=0)
            {
                vSQL.append("AND site_topo.site_id=:site_id AND topo.borrowed=true ORDER BY topo.topo_id");
                getvParams().addValue("site_id", site.getId());
            }
        }
        RowMapper<Topo> vRowMapper = new TopoRM();
        List<Topo> vList = getvNamedParameterJdbcTemplate().query(vSQL.toString(),getvParams(),vRowMapper);
        return vList;
    }

    @Override
    public List<Topo> findAllBorrowed(User user) {
        StringBuilder vSQL= new StringBuilder("SELECT topo.*, user_account.*, user_account.user_account_id AS user_id,image.*, image.image_id AS imageId " +
                "FROM topo " +
                "LEFT JOIN user_account ON topo.user_account_id = user_account.user_account_id " +
                "LEFT JOIN image ON user_account.image_id = image.image_id " +
                "WHERE 1=1 ");
        if(user != null)
        {
            if(user.getId()!=0)
            {
                vSQL.append("AND topo.user_account_id!=:user_id ");
                getvParams().addValue("user_id", user.getId());
            }
        }
        vSQL.append("AND topo.borrowed=:borrowed ORDER BY topo.topo_id");
        getvParams().addValue("borrowed", true);
        RowMapper<Topo> vRowMapper = new TopoRM();
        List<Topo> vList = getvNamedParameterJdbcTemplate().query(vSQL.toString(),getvParams(),vRowMapper);
        return vList;
    }
}

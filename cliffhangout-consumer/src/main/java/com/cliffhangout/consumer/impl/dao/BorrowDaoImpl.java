package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.Borrow;
import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Topo;
import com.cliffhangout.beans.User;
import com.cliffhangout.consumer.contract.dao.BorrowDao;
import com.cliffhangout.consumer.impl.rowmapper.BorrowRM;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Types;
import java.util.List;

public class BorrowDaoImpl extends AbstractDaoImpl implements BorrowDao {
    @Override
    public List<Borrow> getUserTopoBorrowed(User user) {
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT topo.*, topo.user_account_id AS user_id, user_account.user_account_id AS user_id, user_account.*, topo_borrowing.user_account_id AS borrower_id, start_date, end_date " +
                "FROM topo_borrowing " +
                "LEFT JOIN topo ON topo_borrowing.topo_id = topo.topo_id " +
                "LEFT JOIN user_account ON user_account.user_account_id= topo.user_account_id " +
                "WHERE 1=1 ");
        if(user != null)
        {
            if(user.getId()!=0)
            {
                vSQL.append("AND topo_borrowing.user_account_id =:user_id ORDER BY topo.topo_id");
                vParams.addValue("user_id", user.getId());
            }
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Borrow> vRowMapper = new BorrowRM();
        List<Borrow> vList = vJdbcTemplate.query(vSQL.toString(),vParams,vRowMapper);
        return vList;
    }



    @Override
    public void deleteBorrowByTopo(Topo topo) {
        String vSQL = "DELETE FROM topo_borrowing WHERE topo_id=:id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", topo.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void deleteBorrowByUser(User user) {
        String vSQL = "DELETE FROM topo_borrowing WHERE user_account_id=:id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", user.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void deleteBorrow(Topo topo, User user) {
        String vSQL = "DELETE FROM topo_borrowing WHERE user_account_id=:user_id AND topo_id=:topo_id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("user_id", user.getId(), Types.INTEGER);
        vParams.addValue("topo_id", topo.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public Borrow find(User user, Topo topo) {
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT topo.*, topo.user_account_id AS user_id, user_account.user_account_id AS user_id, user_account.*, topo_borrowing.user_account_id AS borrower_id, start_date, end_date " +
                "FROM topo_borrowing " +
                "LEFT JOIN topo ON topo_borrowing.topo_id = topo.topo_id " +
                "LEFT JOIN user_account ON user_account.user_account_id= topo.user_account_id " +
                "WHERE 1=1 ");
        if(user != null && topo!=null)
        {
            if(user.getId()!=0 && topo.getId()!=0)
            {
                vSQL.append("AND topo_borrowing.user_account_id =:user_id AND topo_borrowing.topo_id=:topo_id ORDER BY topo.topo_id");
                vParams.addValue("user_id", user.getId());
                vParams.addValue("topo_id", topo.getId());
            }
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Borrow> vRowMapper = new BorrowRM();
        Borrow borrow = vJdbcTemplate.queryForObject(vSQL.toString(), vParams, vRowMapper);
        return borrow;
    }
}

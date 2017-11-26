package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.Borrow;
import com.cliffhangout.beans.User;
import com.cliffhangout.consumer.contract.dao.BorrowDao;
import com.cliffhangout.consumer.impl.rowmapper.BorrowRM;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

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
}

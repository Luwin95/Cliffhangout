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
        StringBuilder vSQL= new StringBuilder("SELECT topo.*, topo.user_account_id AS user_id, user_account.user_account_id AS user_id, user_account.*,image.*,image.image_id AS imageId, topo_borrowing.user_account_id AS borrower_id, start_date, end_date " +
                "FROM topo_borrowing " +
                "LEFT JOIN topo ON topo_borrowing.topo_id = topo.topo_id " +
                "LEFT JOIN user_account ON user_account.user_account_id= topo.user_account_id " +
                "LEFT JOIN image ON user_account.image_id=image.image_id "+
                "WHERE 1=1 ");
        if(user != null)
        {
            if(user.getId()!=0)
            {
                vSQL.append("AND topo_borrowing.user_account_id =:user_id ORDER BY topo.topo_id");
                getvParams().addValue("user_id", user.getId());
            }
        }
        RowMapper<Borrow> vRowMapper = new BorrowRM();
        List<Borrow> vList = getvNamedParameterJdbcTemplate().query(vSQL.toString(),getvParams(),vRowMapper);
        return vList;
    }



    @Override
    public void deleteBorrowByTopo(Topo topo) {
        String vSQL = "DELETE FROM topo_borrowing WHERE topo_id=:id";
        getvParams().addValue("id", topo.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL,getvParams());
    }

    @Override
    public void deleteBorrowByUser(User user) {
        String vSQL = "DELETE FROM topo_borrowing WHERE user_account_id=:id";
        getvParams().addValue("id", user.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public void deleteBorrow(Topo topo, User user) {
        String vSQL = "DELETE FROM topo_borrowing WHERE user_account_id=:user_id AND topo_id=:topo_id";
        getvParams().addValue("user_id", user.getId(), Types.INTEGER);
        getvParams().addValue("topo_id", topo.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public Borrow find(User user, Topo topo) {
        StringBuilder vSQL= new StringBuilder("SELECT topo.*, topo.user_account_id AS user_id, user_account.user_account_id AS user_id, user_account.*,image.*,image.image_id AS imageId, topo_borrowing.user_account_id AS borrower_id, start_date, end_date " +
                "FROM topo_borrowing " +
                "LEFT JOIN topo ON topo_borrowing.topo_id = topo.topo_id " +
                "LEFT JOIN user_account ON user_account.user_account_id= topo.user_account_id " +
                "LEFT JOIN image ON user_account.image_id=image.image_id "+
                "WHERE 1=1 ");
        if(user != null && topo!=null)
        {
            if(user.getId()!=0 && topo.getId()!=0)
            {
                vSQL.append("AND topo_borrowing.user_account_id =:user_id AND topo_borrowing.topo_id=:topo_id ORDER BY topo.topo_id");
                getvParams().addValue("user_id", user.getId());
                getvParams().addValue("topo_id", topo.getId());
            }
        }
        RowMapper<Borrow> vRowMapper = new BorrowRM();
        Borrow borrow = getvNamedParameterJdbcTemplate().queryForObject(vSQL.toString(), getvParams(), vRowMapper);
        return borrow;
    }
}

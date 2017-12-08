package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.beans.Borrow;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowRM extends AbstractRM implements RowMapper<Borrow> {
    private TopoRM topoRM;

    private TopoRM getTopoRM() {
        return topoRM;
    }

    public void setTopoRM(TopoRM topoRM) {
        this.topoRM = topoRM;
    }

    @Override
    public Borrow mapRow(ResultSet rs, int rowNum) throws SQLException {
        Borrow borrow = new Borrow();
        borrow.setStartDate(rs.getDate("start_date"));
        borrow.setEndDate(rs.getDate("end_date"));
        borrow.setBorrowerId(rs.getInt("borrower_id"));
        borrow.setTopo(getTopoRM().mapRow(rs,rowNum));
        return borrow;
    }
}

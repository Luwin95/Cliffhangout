package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.Quotation;
import com.cliffhangout.consumer.contract.dao.QuotationDao;
import com.cliffhangout.consumer.impl.rowmapper.QuotationRM;

public class QuotationDaoImpl extends AbstractDaoImpl implements QuotationDao {
    private QuotationRM quotationRM;

    protected QuotationRM getQuotationRM() {
        return quotationRM;
    }

    public void setQuotationRM(QuotationRM quotationRM) {
        this.quotationRM = quotationRM;
    }
    @Override
    public Quotation find(int difficulty){
        StringBuilder vSQL= new StringBuilder("SELECT * FROM quotation WHERE 1=1 ");
        if(difficulty>0)
        {
            vSQL.append("AND  quotation_difficulty=:quotation_difficulty");
            getvParams().addValue("quotation_difficulty", difficulty);
        }
        Quotation quotation= getvNamedParameterJdbcTemplate().queryForObject(vSQL.toString(), getvParams(), getQuotationRM());
        return quotation;

    }

    @Override
    public Quotation findByName(String name){
        StringBuilder vSQL= new StringBuilder("SELECT * FROM quotation WHERE 1=1 ");
        if(name != null)
        {
            if(!name.equals(""))
            {
                vSQL.append("AND quotation_name=:quotation_name");
                getvParams().addValue("quotation_name", name);
            }
        }
        Quotation quotation= getvNamedParameterJdbcTemplate().queryForObject(vSQL.toString(), getvParams(), getQuotationRM());
        return quotation;
    }

}

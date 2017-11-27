package com.cliffhangout.business.impl.manager;

import com.cliffhangout.beans.Borrow;
import com.cliffhangout.beans.Topo;
import com.cliffhangout.beans.User;
import com.cliffhangout.business.contract.manager.BorrowManager;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BorrowManagerImpl extends AbstractManagerImpl implements BorrowManager {
    @Override
    public List<Borrow> displayBorrowByBorrower(User user) {
        try{
            List<Borrow> borrows = getDaoFactory().getBorrowDao().getUserTopoBorrowed(user);
            for(Borrow borrow : borrows)
            {
                buildTopoDependencies(borrow.getTopo());
            }
            return borrows;
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void buildTopoDependencies(Topo topo) {
        topo.setSites(getDaoFactory().getSiteDao().findAllByTopo(topo));
    }

    @Override
    public void checkBorrowedTopos(List<Borrow> borrows, List<Topo> topos, Date now) {
        List<Topo> toposToRemove = new ArrayList<>();
        for(Borrow borrow : borrows)
        {
            for(Topo topo :topos)
            {
                if(borrow.getTopo().getId() == topo.getId())
                {
                    if(!borrow.getEndDate().equals(now))
                    {
                        if(borrow.getEndDate().after(now))
                        {
                            toposToRemove.add(topo);
                        }
                    }
                }
            }
        }
        topos.removeAll(toposToRemove);
    }
}

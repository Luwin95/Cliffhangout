package com.cliffhangout.business.contract.manager;

import com.cliffhangout.beans.Borrow;
import com.cliffhangout.beans.Topo;
import com.cliffhangout.beans.User;

import java.util.List;

public interface BorrowManager {
    List<Borrow> displayBorrowByBorrower(User user);
    void buildTopoDependencies(Topo topo);
    void checkBorrowedTopos(List<Borrow> borrows, List<Topo> topos);
}

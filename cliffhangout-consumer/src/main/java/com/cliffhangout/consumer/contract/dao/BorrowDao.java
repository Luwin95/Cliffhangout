package com.cliffhangout.consumer.contract.dao;

import com.cliffhangout.beans.Borrow;
import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Topo;
import com.cliffhangout.beans.User;

import java.util.List;

public interface BorrowDao {
    List<Borrow> getUserTopoBorrowed(User user);
    void deleteBorrowByTopo(Topo topo);
    void deleteBorrowByUser(User user);
    void deleteBorrow(Topo topo, User user);
    Borrow find(User user, Topo topo);
}

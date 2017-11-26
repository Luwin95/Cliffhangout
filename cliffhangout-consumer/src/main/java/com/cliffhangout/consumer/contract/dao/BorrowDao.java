package com.cliffhangout.consumer.contract.dao;

import com.cliffhangout.beans.Borrow;
import com.cliffhangout.beans.User;

import java.util.List;

public interface BorrowDao {
    List<Borrow> getUserTopoBorrowed(User user);
}

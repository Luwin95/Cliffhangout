package com.cliffhangout.business.contract.manager;

import com.cliffhangout.beans.User;

public interface UserManager {
    User displayUser(int id);
    String hashPassword(String password);
    User getLoginUser(String username);
    boolean validateCredentials(User user, String password);
    boolean isInDatabase(String username);
}

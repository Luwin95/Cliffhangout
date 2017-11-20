package com.cliffhangout.business.contract.manager;

import com.cliffhangout.beans.User;
import java.io.File;

public interface UserManager {
    User displayUser(int id);
    String hashPassword(String password);
    User getLoginUser(String username);
    boolean validateCredentials(User user, String password);
    boolean isInDatabase(String username);
    String signinNewSubscriber(User user);
    void addProfileImage(File profileImage, String profileImageContentType, String profileImageFileName, User user);
}

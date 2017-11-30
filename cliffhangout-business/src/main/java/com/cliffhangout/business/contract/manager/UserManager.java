package com.cliffhangout.business.contract.manager;

import com.cliffhangout.beans.User;
import java.io.File;
import java.util.List;

public interface UserManager {
    User displayUser(int id);
    String hashPassword(String password);
    User getLoginUser(String username);
    boolean validateCredentials(User user, String password);
    boolean isInDatabase(String username);
    boolean checkEmailInDb(String email);
    boolean checkLoginInDb(String login);
    String signinNewSubscriber(User user);
    void addProfileImage(File profileImage, String profileImageContentType, String profileImageFileName, User user);
    void editProfile(User user, User userSession);
    void editProfile(User user, User userSession,File profileImage, String profileImageContentType, String profileImageFileName);
    List<User> displayAllUsers();
    void editUserRights(User user);
    void editAccountActivation(int idUser);
}

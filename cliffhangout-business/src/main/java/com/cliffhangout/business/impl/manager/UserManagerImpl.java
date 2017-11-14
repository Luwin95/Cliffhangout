package com.cliffhangout.business.impl.manager;

import com.cliffhangout.beans.User;
import com.cliffhangout.business.contract.manager.UserManager;
import org.mindrot.jbcrypt.BCrypt;

public class UserManagerImpl extends AbstractManagerImpl implements UserManager {

    @Override
    public User displayUser(int id)
    {
        User user = new User();
        user = getDaoFactory().getUserDao().find(id);
        return user;
    }

    public User getLoginUser(String username)
    {
        User user = new User();
        user = getDaoFactory().getUserDao().findByLogin(username);
        return user;
    }

    @Override
    public String hashPassword(String password)
    {
        String salt = BCrypt.gensalt(16);
        String passwordHash = BCrypt.hashpw(password, salt);
        return passwordHash;
    }


    @Override
    public boolean validateCredentials(User user, String password)
    {
        boolean passwordChecked = false;
        if(user.getPassword() == null || !user.getPassword().startsWith("$2a$")){
            throw new IllegalArgumentException("Le hash n'est pas valide");
        }

        passwordChecked = BCrypt.checkpw(user.getPassword(), password);

        return passwordChecked;
    }

    @Override
    public boolean isInDatabase(String username)
    {
        User user = new User();
        user = getDaoFactory().getUserDao().findByLogin(username);
        return user.getId() != 0;
    }
}

package com.cliffhangout.business.impl.manager;

import com.cliffhangout.beans.User;
import com.cliffhangout.business.contract.manager.UserManager;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.dao.EmptyResultDataAccessException;

public class UserManagerImpl extends AbstractManagerImpl implements UserManager {

    @Override
    public User displayUser(int id)
    {
        User user = new User();
        try{
            user = getDaoFactory().getUserDao().find(id);
        }catch(EmptyResultDataAccessException e){
            e.printStackTrace();
        }
        return user;
    }

    public User getLoginUser(String username)
    {
        User user = new User();
        try{
            user = getDaoFactory().getUserDao().findByLogin(username);
        }catch(EmptyResultDataAccessException e){
            e.printStackTrace();
        }
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

        passwordChecked = BCrypt.checkpw(password, user.getPassword());

        return passwordChecked;
    }

    @Override
    public boolean isInDatabase(String username)
    {
        User user = new User();
        try{
            user = getDaoFactory().getUserDao().findByLogin(username);
            if(user!=null)
            {
                return user.getId() != 0;
            }else{
                return false;
            }
        }catch(EmptyResultDataAccessException e){
            return false;
        }
    }
}

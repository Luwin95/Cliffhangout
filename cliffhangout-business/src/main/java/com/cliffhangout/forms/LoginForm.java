package com.cliffhangout.forms;

import com.cliffhangout.beans.User;
import com.cliffhangout.dao.DaoException;
import com.cliffhangout.dao.DaoFactory;
import com.cliffhangout.dao.UserDao;
import com.cliffhangout.services.Password;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class LoginForm {
    private static final String FIELD_LOGIN = "login";
    private static final String FIELD_PSSWD = "mdp";

    private String result;
    private Map<String, String> errors = new HashMap<String, String>();
    private UserDao userDao;

    public LoginForm()
    {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.userDao = daoFactory.getUserDao();
    }

    public String getResult() {
        return result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    private void addError(String field, String message){
        errors.put(field, message);
    }

    public User connectUser(String login, String password){

        try{
            validateLogin(login);
        }catch(Exception e)
        {
            addError(FIELD_LOGIN, e.getMessage());
        }

        User user = new User();
        user.setLogin(login);

        try{
            validatePassword(password);
        }catch (Exception e){
            addError(FIELD_PSSWD, e.getMessage());
        }

        if(errors.isEmpty())
        {
            try{
                user = userDao.findByLogin(login);
                try{
                    validateLoginExists(user);
                    try{
                        validateCredentials(user, password);
                    }catch (Exception e){
                        addError(FIELD_PSSWD, e.getMessage());
                    }
                }catch(Exception e){
                    addError(FIELD_LOGIN, e.getMessage());
                }
            }catch(DaoException e) {
                e.printStackTrace();
            }
            if(errors.isEmpty())
            {
                result = "Succès de connexion";
            }else{
                result = "Erreur de connexion";
            }
        }else{
            result = "Erreur de connexion";
        }

        return user;
    }

    private void validateLogin( String login) throws Exception {
        if( login != null)
        {
            if( login.length() <=2)
            {
                throw  new Exception("Merci de saisir un login valide");
            }
        }else{
            throw new Exception("Merci de saisir votre login");
        }
    }

    private void validatePassword(String password) throws Exception{
        if(password != null)
        {
            if( password.length() <=4)
            {
                throw new Exception("Le mot de passe doit contenir au moins 5 caractères");
            }
        }else{
            throw new Exception("Merci de saisir votre mot de passe");
        }
    }

    public boolean validateCredentials(User user, String password)
    {
        Password passwordService = new Password();
        if(!passwordService.verifyCredentials(password,user.getPassword()))
        {
            return false;
        }else{
            return true;
        }
    }

    private void validateLoginExists(User user) throws Exception{
        if(user.getLogin() == null)
        {
            throw new Exception("Login inconnu");
        }
    }

    public boolean isInDatabase(String username)
    {
        User user = new User();
        try {
            user = userDao.findByLogin(username);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        if(user.getId() != 0)
        {
            return true;
        }else{
            return false;
        }
    }

    public User getLoginUser(String username)
    {
        User user = new User();
        try{
            user = userDao.findByLogin(username);
        }catch(DaoException e){
            e.printStackTrace();
        }
        return user;
    }

}

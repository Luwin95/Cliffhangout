package com.cliffhangout.services;

import org.mindrot.jbcrypt.BCrypt;

public class Password {

    public String hashPassword(String password)
    {
        String salt = BCrypt.gensalt(16);
        String passwordHash = BCrypt.hashpw(password, salt);
        return passwordHash;
    }

    public boolean verifyCredentials(String passwordPlain, String passwordStored)
    {
        boolean passwordChecked = false;
        if(passwordStored == null || !passwordStored.startsWith("$2a$")){
            throw new IllegalArgumentException("Le hash n'est pas valide");
        }

        passwordChecked = BCrypt.checkpw(passwordPlain, passwordStored);

        return passwordChecked;
    }
}

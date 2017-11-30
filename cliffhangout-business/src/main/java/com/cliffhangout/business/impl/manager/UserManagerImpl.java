package com.cliffhangout.business.impl.manager;

import com.cliffhangout.beans.Image;
import com.cliffhangout.beans.User;
import com.cliffhangout.business.contract.manager.UserManager;
import org.apache.commons.io.FileUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @Override
    public User getLoginUser(String username)
    {
        User user = new User();
        try{
            user = getDaoFactory().getUserDao().findByLoginActive(username);
        }catch(EmptyResultDataAccessException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public String hashPassword(String password)
    {
        String salt = BCrypt.gensalt();
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
            user = getDaoFactory().getUserDao().findByLoginActive(username);
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

    @Override
    public String signinNewSubscriber(User user) {

            TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
            vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus
                                                                    pTransactionStatus) {
                    String encryptedPassword= hashPassword(user.getPassword());
                    user.setPassword(encryptedPassword);
                    user.setRole("USER");
                    user.setActive(true);
                    getDaoFactory().getUserDao().create(user);
                }
            });
            return "success";
    }

    @Override
    public void addProfileImage(File profileImage, String profileImageContentType, String profileImageFileName, User user) {
        /*ServletContext context = ServletActionContext.getServletContext();
            String userDir = context.getRealPath("/");*/
        String userDir = "E:\\P3\\cliffhangout-webapp\\src\\main\\webapp\\";
        userDir = userDir.replaceAll("\\\\", "/");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yy_H_mm_ss");
        Date date = new Date();
        String path=dateFormat.format(date)+ UUID.randomUUID().toString()+"."+profileImageContentType.substring(6);
        Image image = new Image();
        image.setPath(path);
        image.setTitle("Image profil utilisateur");
        image.setAlt("Image profil utilisateur "+profileImageFileName);
        try
        {
            TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
            vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus
                                                                    pTransactionStatus) {
                    getDaoFactory().getImageDao().create(image);
                    user.setImage(image);
                }
            });
        }finally{
            profileImageFileName = "resources/images/user/"+path;
            String fullfilename = userDir+profileImageFileName;
            File importedImage = new File(fullfilename);
            try{
                FileUtils.copyFile(profileImage, importedImage);
            }catch(IOException e){
            }
        }
    }

    @Override
    public void editProfile(User user, User userSession) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus
                                                                pTransactionStatus) {
                if(!user.getPassword().equals(""))
                {
                    String encryptedPassword= hashPassword(user.getPassword());
                    userSession.setPassword(encryptedPassword);
                }
                userSession.setLogin(user.getLogin());
                userSession.setEmail(user.getEmail());

                getDaoFactory().getUserDao().update(userSession);
            }
        });
    }

    @Override
    public void editProfile(User user, User userSession, File profileImage, String profileImageContentType, String profileImageFileName) {
        if(userSession.getImage()!=null)
        {
            String userDir = "E:\\P3\\cliffhangout-webapp\\src\\main\\webapp\\";
            userDir = userDir.replaceAll("\\\\", "/");
            String imageToDeleteName = userDir+"/resources/images/user"+userSession.getImage().getPath();
            File imageToDelete = new File(imageToDeleteName);
            FileUtils.deleteQuietly(imageToDelete);
        }
        User oldUser = userSession;
        addProfileImage(profileImage, profileImageContentType, profileImageFileName, userSession);
        getDaoFactory().getImageDao().delete(oldUser.getImage());
        editProfile(user, userSession);
    }

    @Override
    public List<User> displayAllUsers() {
        return getDaoFactory().getUserDao().findAll();
    }

    @Override
    public void editUserRights(User user) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus
                                                                pTransactionStatus) {
                if(user.getRole().equals("ADMIN"))
                {
                    user.setRole("USER");
                }else if(user.getRole().equals("USER")){
                    user.setRole("ADMIN");
                }
                getDaoFactory().getUserDao().update(user);
            }
        });
    }

    @Override
    public void editAccountActivation(int idUser) {
        User user = displayUser(idUser);
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus
                                                                pTransactionStatus) {
                if(user.isActive())
                {
                    user.setActive(false);
                }else{
                    user.setActive(true);
                }
                getDaoFactory().getUserDao().update(user);
            }
        });
    }
}

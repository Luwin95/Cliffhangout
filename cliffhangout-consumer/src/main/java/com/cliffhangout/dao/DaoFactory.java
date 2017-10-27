package com.cliffhangout.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DaoFactory {
    private String url;
    private String username;
    private String password;

    DaoFactory(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance(){
        Properties properties = new Properties();
        try{
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){

        }

        try{
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("db.properties");
            properties.load(input);
        }catch(IOException e)
        {

        }
        DaoFactory instance = new DaoFactory(properties.getProperty("com.cliffhangout.dao.url"), properties.getProperty("com.cliffhangout.dao.username"), properties.getProperty("com.cliffhangout.dao.password"));
        return instance;
    }

    public Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection(url, username, password);
        connection.setAutoCommit(false);
        return connection;
    }

    public UserDao getUserDao(){
        return new UserDaoImpl(this);
    }

    public SiteDao getSiteDao(){
        return new SiteDaoImpl(this, this.getUserDao(), this.getSectorDao(), this.getDepartementDao());
    }

    public SectorDao getSectorDao() {return new SectorDaoImpl(this, this.getWayDao()); }

    public WayDao getWayDao() { return new WayDaoImpl(this, this.getLengthDao(), this.getQuotationDao());}

    public LengthDao getLengthDao() { return new LengthDaoImpl(this, this.getPointDao()); }

    public PointDao getPointDao() { return new PointDaoImpl(this); }

    public CommentDao getCommentDao() { return new CommentDaoImpl(this, this.getUserDao());}

    public ImageDao getImageDao() { return new ImageDaoImpl(this);}

    public TopoDao getTopoDao(){ return new TopoDaoImpl(this, this.getUserDao(), this.getSiteDao()); }

    public RegionDao getRegionDao(){ return new RegionDaoImpl(this); }

    public DepartementDao getDepartementDao() { return new DepartementDaoImpl(this, this.getRegionDao()); }

    public QuotationDao getQuotationDao() { return new QuotationDaoImpl(this); }
}

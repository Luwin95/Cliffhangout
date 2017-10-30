package com.cliffhangout.dao;

import com.cliffhangout.beans.Image;
import com.cliffhangout.beans.Sector;
import com.cliffhangout.beans.Site;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageDaoImpl implements ImageDao {
    private DaoFactory daoFactory;

    ImageDaoImpl(DaoFactory daoFactory){
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(Image image) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO image(alt, path, title) VALUES(?, ?, ?);");
            preparedStatement.setString(1, image.getAlt());
            preparedStatement.setString(2, image.getPath());
            preparedStatement.setString(3, image.getTitle());

            preparedStatement.executeUpdate();
            connection.commit();
        }
        catch(SQLException e){
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }

    }

    @Override
    public void update(Image image) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE image SET alt=?, path=?, title=? WHERE id=?;");
            preparedStatement.setString(1, image.getAlt());
            preparedStatement.setString(2, image.getPath());
            preparedStatement.setString(3, image.getTitle());
            preparedStatement.setInt(4, image.getId());

            preparedStatement.executeUpdate();
            connection.commit();
        }
        catch(SQLException e){
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
    }

    @Override
    public void delete(Image image) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM image WHERE id=?;");
            preparedStatement.setInt(1, image.getId());

            preparedStatement.executeUpdate();
            connection.commit();
        }
        catch(SQLException e){
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
    }

    @Override
    public Image find(int id) throws DaoException {
        Image image = new Image();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM image WHERE id=?;");
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            while(resultat.next()){
                image = buildImage(resultat);
            }
        }catch (SQLException e){
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
        return image;
    }

    @Override
    public List<Image> findAllBySite(Site site) throws DaoException {
        List<Image> images = new ArrayList<Image>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("Select image.* from site_image INNER JOIN image on site_image.image_id= image.id WHERE site_id =?;");
            preparedStatement.setInt(1, site.getId());
            resultat = preparedStatement.executeQuery();
            while(resultat.next()){
                Image image = buildImage(resultat);
                images.add(image);
            }
        }catch (SQLException e){
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
        return images;
    }

    @Override
    public List<Image> findAllBySector(Sector sector) throws DaoException {
        List<Image> images = new ArrayList<Image>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("Select image.* from sector_image INNER JOIN image on sector_image.image_id= image.id WHERE sector_id =?;");
            preparedStatement.setInt(1, sector.getId());
            resultat = preparedStatement.executeQuery();
            while(resultat.next()){
                Image image = buildImage(resultat);
                images.add(image);
            }
        }catch (SQLException e){
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
        return images;
    }

    @Override
    public Image buildImage(ResultSet resultSet) throws DaoException {
        Image image = new Image();
        try
        {
            image.setId(resultSet.getInt("id"));
            image.setAlt(resultSet.getString("alt"));
            image.setPath(resultSet.getString("path"));
            image.setTitle(resultSet.getString("title"));
        }catch(SQLException e){
            e.printStackTrace();
        }
        return image;
    }
}

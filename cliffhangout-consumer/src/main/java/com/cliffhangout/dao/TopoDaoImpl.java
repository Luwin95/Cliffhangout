package com.cliffhangout.dao;

import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Topo;
import com.cliffhangout.beans.User;

import java.sql.*;
import java.util.Set;

public class TopoDaoImpl implements TopoDao {
    private DaoFactory daoFactory;
    private UserDao userDao;
    private SiteDao siteDao;

    public TopoDaoImpl(DaoFactory daoFactory, UserDao userDao, SiteDao siteDao)
    {
        this.daoFactory = daoFactory;
        this.userDao = userDao;
        this.siteDao = siteDao;
    }

    @Override
    public void create(Topo topo) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO topo(name, description, file, borrowed, user_account_id) VALUES(?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, topo.getName());
            preparedStatement.setString(2, topo.getDescription());
            preparedStatement.setString(3, topo.getFile());
            preparedStatement.setBoolean(4, topo.isBorrowed());
            preparedStatement.setInt(5, topo.getOwner().getId());

            preparedStatement.executeUpdate();
            connection.commit();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys())
            {
                if(generatedKeys.next())
                {
                    topo.setId(generatedKeys.getInt(1));
                }else{
                    throw new SQLException("Creating sector failed, no ID obtained");
                }
            }
        }catch(SQLException e){
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e2) {
            }
            e.printStackTrace();
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
    public void update(Topo topo) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE topo SET name=?, description=?, file=?, borrowed=?, user_account_id=? WHERE id=?;");
            preparedStatement.setString(1, topo.getName());
            preparedStatement.setString(2, topo.getDescription());
            preparedStatement.setString(3, topo.getFile());
            preparedStatement.setBoolean(4, topo.isBorrowed());
            preparedStatement.setInt(5, topo.getOwner().getId());
            preparedStatement.setInt(6, topo.getId());

            preparedStatement.executeUpdate();
            connection.commit();
        }catch(SQLException e){
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
    public void delete(Topo topo) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM topo WHERE id=?;");
            preparedStatement.setInt(1, topo.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        }catch(SQLException e){
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
    public Topo find(int id) throws DaoException {
        Topo topo = new Topo();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM topo WHERE id=?;");
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();

            while(resultat.next()){
                topo = buildTopo(resultat);
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
        return topo;
    }

    @Override
    public Topo buildTopo(ResultSet resultSet) throws DaoException {
        Topo topo = new Topo();
        try{
            topo.setId(resultSet.getInt("id"));
            topo.setName(resultSet.getString("name"));
            topo.setDescription(resultSet.getString("description"));
            topo.setFile(resultSet.getString("file"));
            topo.setBorrowed(resultSet.getBoolean("borrowed"));
            User user = userDao.find(resultSet.getInt("user_account_id"));
            topo.setOwner(user);
            Set<Site> sites = siteDao.findAllByTopo(topo);
            topo.setSites(sites);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return topo;
    }
}

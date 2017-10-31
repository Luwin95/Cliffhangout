package com.cliffhangout.dao;

import com.cliffhangout.beans.Comment;
import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao{

    private DaoFactory daoFactory;
    private UserDao userDao;

    CommentDaoImpl(DaoFactory daoFactory, UserDao userDao){
        this.daoFactory = daoFactory;
        this.userDao = userDao;
    }

    @Override
    public void create(Comment comment) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO comment(content, author_id, parent_id) VALUES(?, ?, ?);");
            preparedStatement.setString(1, comment.getContent());
            preparedStatement.setInt(2, comment.getAuthor().getId());
            preparedStatement.setInt(3, comment.getParent().getId());
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
    public void update(Comment comment) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE comment SET content=?, author_id=?, parent_id=? WHERE id=?;");
            preparedStatement.setString(1, comment.getContent());
            preparedStatement.setInt(2, comment.getAuthor().getId());
            preparedStatement.setInt(3, comment.getParent().getId());
            preparedStatement.setInt(4, comment.getId());

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
    public void delete(Comment comment) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM comment WHERE id=?;");
            preparedStatement.setInt(1, comment.getId());

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
    public Comment find(int id) throws DaoException {
        Comment comment = new Comment();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM comment WHERE id=?;");
            preparedStatement.setInt(1, id);
            resultat = preparedStatement.executeQuery();
            while(resultat.next()){
                comment = buildComment(resultat);
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
        return comment;
    }

    @Override
    public List<Comment> findAllByParent(Comment parent) throws DaoException {
        List<Comment> comments = new ArrayList<Comment>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM comment WHERE parent_id=?;");
            preparedStatement.setInt(1, parent.getId());
            resultat = preparedStatement.executeQuery();
            while(resultat.next()){
                Comment comment = buildComment(resultat);
                comments.add(comment);
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
        return comments;
    }

    @Override
    public List<Comment> findAllBySite(Site site) throws DaoException {
        List<Comment> comments = new ArrayList<Comment>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try{
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("Select comment.* from comment_site INNER JOIN comment on comment_site.comment_id= comment.id WHERE site_id =?;");
            preparedStatement.setInt(1, site.getId());
            resultat = preparedStatement.executeQuery();
            while(resultat.next()){
                Comment comment = buildComment(resultat);
                comments.add(comment);
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
        return comments;
    }

    @Override
    public Comment buildComment(ResultSet resultSet) throws DaoException {

        Comment comment = new Comment();
        try{
            comment.setId(resultSet.getInt("id"));
            comment.setContent(resultSet.getString("content"));
            comment.setAuthor(userDao.find(resultSet.getInt("author_id")));
            if(resultSet.getInt("parent_id")!= 0)
            {
                comment.setParent(this.find(resultSet.getInt("parent_id")));
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return comment;
    }
}

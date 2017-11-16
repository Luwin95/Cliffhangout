package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.Comment;
import com.cliffhangout.beans.Site;
import com.cliffhangout.consumer.contract.dao.CommentDao;
import com.cliffhangout.consumer.contract.dao.UserDao;
import com.cliffhangout.consumer.impl.rowmapper.CommentRM;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl extends AbstractDaoImpl implements CommentDao {
    @Override
    public void create(Comment comment){
        String vSQL = "INSERT INTO comment(content, author_id, parent_id) VALUES(:content, :author_id, :parent_id)";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("content", comment.getContent(), Types.VARCHAR);
        vParams.addValue("author_id", comment.getAuthor().getId(), Types.INTEGER);
        vParams.addValue("parent_id", comment.getParent().getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void createCommentSite(int site_id, Comment comment){
        create(comment);
        String vSQL = "INSERT INTO comment_site(comment_id, site_id) VALUES(:comment_id, :site_id)";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("comment_id", comment.getId(), Types.INTEGER);
        vParams.addValue("site_id", site_id, Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);

    }

    @Override
    public void createCommentTopo(int topo_id, Comment comment){
        create(comment);
        String vSQL = "INSERT INTO comment_topo(comment_id, topo_id) VALUES(:comment_id, :topo_id)";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("comment_id", comment.getId(), Types.INTEGER);
        vParams.addValue("topo_id", topo_id, Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void update(Comment comment){
        String vSQL = "UPDATE comment SET content=:content, author_id=:author_id, parent_id=:parent_id WHERE id=:id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("content", comment.getContent(), Types.VARCHAR);
        vParams.addValue("comment_id", comment.getId(), Types.INTEGER);
        vParams.addValue("parent_id", comment.getParent().getId(), Types.INTEGER);
        vParams.addValue("id", comment.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void delete(Comment comment){
        String vSQL = "DELETE FROM comment WHERE id=:id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", comment.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public Comment find(int id){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT  * FROM comment AS child LEFT JOIN comment AS parent ON child.parent_id = parent.id WHERE 1=1 ");
        if(id>0)
        {
            vSQL.append("AND id = :id");
            vParams.addValue("id", id);
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Comment> vRowMapper = new CommentRM();
        Comment comment = vJdbcTemplate.queryForObject(vSQL.toString(), vParams, vRowMapper);
        return comment;
    }

    @Override
    public List<Comment> findAllByParent(Comment parent){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT  * FROM comment AS child LEFT JOIN comment AS parent ON child.parent_id = parent.id WHERE 1=1 ");
        if(parent != null)
        {
            if(parent.getId()!=0)
            {
                vSQL.append("AND parent_id=:parent_id");
                vParams.addValue("parent_id", parent.getId());
            }
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Comment> vRowMapper = new CommentRM();
        List<Comment> vList = vJdbcTemplate.query(vSQL.toString(),vParams,vRowMapper);
        return vList;
    }

    @Override
    public List<Comment> findAllBySite(Site site){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT child.id AS child_id, child.content AS child_content, parent.id AS parent_id, parent.content AS parent_content, user_account.id AS user_id, user_account.* " +
                "FROM comment_site  " +
                "INNER JOIN comment AS child on comment_site.comment_id= child.id " +
                "LEFT JOIN comment AS parent ON child.parent_id = parent.id " +
                "LEFT JOIN user_account ON child.author_id= user_account.id " +
                "WHERE 1=1 ");
        if(site != null)
        {
            if(site.getId()!=0)
            {
                vSQL.append("AND site_id=:site_id");
                vParams.addValue("site_id", site.getId());
            }
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Comment> vRowMapper = new CommentRM();
        List<Comment> vList = vJdbcTemplate.query(vSQL.toString(),vParams,vRowMapper);
        return vList;
    }
}

package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.Comment;
import com.cliffhangout.beans.Site;
import com.cliffhangout.consumer.contract.dao.CommentDao;
import com.cliffhangout.consumer.contract.dao.UserDao;
import com.cliffhangout.consumer.impl.rowmapper.CommentRM;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl extends AbstractDaoImpl implements CommentDao {
    @Override
    public void create(Comment comment){
        String vSQL = "INSERT INTO comment(content, author_id, parent_id, reported) VALUES(:content, :author_id, :parent_id, :reported)";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("content", comment.getContent(), Types.VARCHAR);
        vParams.addValue("author_id", comment.getAuthor().getId(), Types.INTEGER);
        if(comment.getParent()!=null)
        {
            vParams.addValue("parent_id", comment.getParent().getId(), Types.INTEGER);
        }else{
            vParams.addValue("parent_id", null, Types.NULL);
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        vParams.addValue("reported", comment.isReported(), Types.BOOLEAN);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams,keyHolder, new String[] {"comment_id"});
        comment.setId((int) keyHolder.getKey());
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
        String vSQL = "UPDATE comment SET content=:content, author_id=:author_id, parent_id=:parent_id, reported=:reported WHERE comment_id=:id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("content", comment.getContent(), Types.VARCHAR);
        vParams.addValue("comment_id", comment.getId(), Types.INTEGER);
        vParams.addValue("author_id", comment.getAuthor().getId(), Types.INTEGER);
        if(comment.getParent()!=null)
        {
            vParams.addValue("parent_id", comment.getParent().getId(), Types.INTEGER);
        }else{
            vParams.addValue("parent_id", null, Types.NULL);
        }
        vParams.addValue("reported", comment.isReported(), Types.BOOLEAN);
        vParams.addValue("id", comment.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void delete(Comment comment){
        String vSQL = "DELETE FROM comment WHERE comment_id=:id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("id", comment.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public Comment find(int id){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT child.comment_id AS child_id, child.content AS child_content, child.reported AS child_reported , parent.comment_id AS parent_id, parent.content AS parent_content,parent.reported AS parent_reported, user_account.user_account_id AS user_id, user_account.* " +
                "FROM comment AS child  " +
                "LEFT JOIN comment AS parent ON child.parent_id = parent.comment_id " +
                "LEFT JOIN user_account ON child.author_id= user_account.user_account_id " +
                "WHERE 1=1 ");
        if(id>0)
        {
            vSQL.append("AND child.comment_id = :id");
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
        StringBuilder vSQL= new StringBuilder("SELECT child.comment_id AS child_id, child.content AS child_content, child.reported AS child_reported , parent.comment_id AS parent_id, parent.content AS parent_content,parent.reported AS parent_reported, user_account.user_account_id AS user_id, user_account.* " +
                "FROM comment AS child  " +
                "LEFT JOIN comment AS parent ON child.parent_id = parent.comment_id " +
                "LEFT JOIN user_account ON child.author_id= user_account.user_account_id " +
                "WHERE 1=1 ");
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
        StringBuilder vSQL= new StringBuilder("SELECT child.comment_id AS child_id, child.content AS child_content, child.reported AS child_reported , parent.comment_id AS parent_id, parent.content AS parent_content,parent.reported AS parent_reported, user_account.user_account_id AS user_id, user_account.*, image.image_id AS imageId, image.* " +
                "FROM comment_site  " +
                "INNER JOIN comment AS child on comment_site.comment_id= child.comment_id " +
                "LEFT JOIN comment AS parent ON child.parent_id = parent.comment_id " +
                "LEFT JOIN user_account ON child.author_id= user_account.user_account_id " +
                "LEFT JOIN image ON image.image_id=user_account.image_id "+
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

    @Override
    public List<Comment> findAllSignaled() {
        try{
            MapSqlParameterSource vParams = new MapSqlParameterSource();
            StringBuilder vSQL= new StringBuilder("SELECT child.comment_id AS child_id, child.content AS child_content, child.reported AS child_reported , parent.comment_id AS parent_id, parent.content AS parent_content,parent.reported AS parent_reported, user_account.user_account_id AS user_id, user_account.* " +
                    "FROM comment AS child " +
                    "LEFT JOIN comment AS parent ON child.parent_id = parent.comment_id " +
                    "LEFT JOIN user_account ON child.author_id= user_account.user_account_id " +
                    "WHERE 1=1 ");
            vSQL.append("AND child.reported=:reported");
            vParams.addValue("reported", true);

            NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
            RowMapper<Comment> vRowMapper = new CommentRM();
            List<Comment> vList = vJdbcTemplate.query(vSQL.toString(),vParams,vRowMapper);
            return vList;
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }
}

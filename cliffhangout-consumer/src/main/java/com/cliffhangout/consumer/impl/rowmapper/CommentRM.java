package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.beans.Comment;
import com.cliffhangout.consumer.contract.dao.CommentDao;
import com.cliffhangout.consumer.contract.dao.UserDao;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CommentRM implements RowMapper<Comment> {
    private UserDao userDao;
    private CommentDao commentDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao pUserDao) {
        userDao = pUserDao;
    }

    public CommentDao getCommentDao() {
        return commentDao;
    }

    public void setCommentDao(CommentDao pCommentDao) {
        commentDao = pCommentDao;
    }

    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment vComment = new Comment();
        vComment.setId(rs.getInt("id"));
        vComment.setContent(rs.getString("content"));
        if(rs.getInt("parent_id")!=0)
        {
            vComment.setParent(commentDao.find(rs.getInt("parent_id")));
        }
        vComment.setAuthor(userDao.find(rs.getInt("author_id")));
        return vComment;
    }
}

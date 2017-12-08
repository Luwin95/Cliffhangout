package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.beans.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CommentRM extends AbstractRM implements RowMapper<Comment> {
    private ParentCommentRM parentCommentRM;
    private UserRM userRM;

    private ParentCommentRM getParentCommentRM() {
        return parentCommentRM;
    }

    public void setParentCommentRM(ParentCommentRM parentCommentRM) {
        this.parentCommentRM = parentCommentRM;
    }

    private UserRM getUserRM() {
        return userRM;
    }

    public void setUserRM(UserRM userRM) {
        this.userRM = userRM;
    }

    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment vComment = new Comment();
        vComment.setId(rs.getInt("child_id"));
        vComment.setContent(rs.getString("child_content"));
        vComment.setReported(rs.getBoolean("child_reported"));
        if(rs.getInt("parent_id") !=0)
        {
            vComment.setParent(getParentCommentRM().mapRow(rs, rowNum));
        }
        vComment.setAuthor(getUserRM().mapRow(rs,rowNum));
        return vComment;
    }
}

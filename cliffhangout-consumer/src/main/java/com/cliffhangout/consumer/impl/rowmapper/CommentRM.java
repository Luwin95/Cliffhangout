package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.beans.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CommentRM extends AbstractRM implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment vComment = new Comment();
        vComment.setId(rs.getInt("child_id"));
        vComment.setContent(rs.getString("child_content"));
        vComment.setReported(rs.getBoolean("child_reported"));
        if(rs.getInt("parent_id") !=0)
        {
            ParentCommentRM parentCommentRM = new ParentCommentRM();
            vComment.setParent(parentCommentRM.mapRow(rs, rowNum));
        }
        UserRM userRM = new UserRM();
        vComment.setAuthor(userRM.mapRow(rs,rowNum));
        return vComment;
    }
}

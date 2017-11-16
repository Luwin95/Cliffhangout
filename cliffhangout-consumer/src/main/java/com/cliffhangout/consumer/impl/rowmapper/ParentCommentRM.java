package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.beans.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParentCommentRM implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment vComment = new Comment();
        vComment.setId(rs.getInt("parent_id"));
        vComment.setContent(rs.getString("parent_content"));
        return vComment;
    }
}

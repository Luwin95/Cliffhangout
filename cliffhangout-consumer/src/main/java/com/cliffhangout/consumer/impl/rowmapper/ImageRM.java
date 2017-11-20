package com.cliffhangout.consumer.impl.rowmapper;

import com.cliffhangout.beans.Image;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageRM extends AbstractRM implements RowMapper<Image> {
    @Override
    public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
        Image image = new Image();
        image.setId(rs.getInt("imageId"));
        image.setAlt(rs.getString("alt"));
        image.setTitle(rs.getString("title"));
        image.setPath(rs.getString("path"));
        return image;
    }
}

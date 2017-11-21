package com.cliffhangout.consumer.impl.dao;

import com.cliffhangout.beans.Image;
import com.cliffhangout.beans.Sector;
import com.cliffhangout.beans.Site;
import com.cliffhangout.consumer.contract.dao.ImageDao;
import com.cliffhangout.consumer.impl.rowmapper.ImageRM;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImageDaoImpl extends AbstractDaoImpl implements ImageDao {

    @Override
    public void create(Image image){
        String vSQL = "INSERT INTO image(alt, title, path) VALUES(:alt, :title, :path)";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("alt", image.getAlt(), Types.VARCHAR);
        vParams.addValue("title", image.getTitle(),  Types.VARCHAR);
        vParams.addValue("path", image.getPath(),  Types.VARCHAR);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams,keyHolder, new String[] {"id"});
        image.setId((int) keyHolder.getKey());
    }

    @Override
    public void update(Image image){
        String vSQL = "UPDATE image SET alt=:alt, path=:path, title=:title WHERE image_id=:image_id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("alt", image.getAlt(), Types.VARCHAR);
        vParams.addValue("title", image.getTitle(),  Types.VARCHAR);
        vParams.addValue("path", image.getPath(),  Types.VARCHAR);
        vParams.addValue("image_id", image.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public void delete(Image image){
        String vSQL = "DELETE FROM image WHERE image_id=:image_id";
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("image_id", image.getId(), Types.INTEGER);
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        vJdbcTemplate.update(vSQL, vParams);
    }

    @Override
    public Image find(int id){

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("SELECT * FROM image WHERE 1=1 ");
        if(id>0)
        {
            vSQL.append("AND image_id = :image_id");
            vParams.addValue("image_id", id);
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Image> vRowMapper = new ImageRM();
        Image image = vJdbcTemplate.queryForObject(vSQL.toString(), vParams, vRowMapper);
        return image;
    }

    @Override
    public List<Image> findAllBySite(Site site){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("Select image.*, image.image_id AS imageId from site_image INNER JOIN image on site_image.image_id= image.image_id  WHERE 1=1 ");
        if(site != null)
        {
            if(site.getId()!=0)
            {
                vSQL.append("AND site_id=:site_id");
                vParams.addValue("site_id", site.getId());
            }
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Image> vRowMapper = new ImageRM();
        List<Image> vList = vJdbcTemplate.query(vSQL.toString(),vParams,vRowMapper);
        return vList;
    }

    @Override
    public List<Image> findAllBySector(Sector sector){
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        StringBuilder vSQL= new StringBuilder("Select image.* from sector_image, image.image_id AS imageId INNER JOIN image on sector_image.image_id= image.image_id WHERE 1=1 ");
        if(sector != null)
        {
            if(sector.getId()!=0)
            {
                vSQL.append("AND sector_id=:sector_id");
                vParams.addValue("sector_id", sector.getId());
            }
        }
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Image> vRowMapper = new ImageRM();
        List<Image> vList = vJdbcTemplate.query(vSQL.toString(),vParams,vRowMapper);
        return vList;
    }

}

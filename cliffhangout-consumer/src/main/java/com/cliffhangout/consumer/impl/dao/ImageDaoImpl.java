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
        getvParams().addValue("alt", image.getAlt(), Types.VARCHAR);
        getvParams().addValue("title", image.getTitle(),  Types.VARCHAR);
        getvParams().addValue("path", image.getPath(),  Types.VARCHAR);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams(),getvKeyHolder(), new String[] {"image_id"});
        image.setId((int) getvKeyHolder().getKey());
    }

    @Override
    public void update(Image image){
        String vSQL = "UPDATE image SET alt=:alt, path=:path, title=:title WHERE image_id=:image_id";
        getvParams().addValue("alt", image.getAlt(), Types.VARCHAR);
        getvParams().addValue("title", image.getTitle(),  Types.VARCHAR);
        getvParams().addValue("path", image.getPath(),  Types.VARCHAR);
        getvParams().addValue("image_id", image.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public void delete(Image image){
        String vSQL = "DELETE FROM image WHERE image_id=:image_id";
        getvParams().addValue("image_id", image.getId(), Types.INTEGER);
        getvNamedParameterJdbcTemplate().update(vSQL, getvParams());
    }

    @Override
    public Image find(int id){
        StringBuilder vSQL= new StringBuilder("SELECT * FROM image WHERE 1=1 ");
        if(id>0)
        {
            vSQL.append("AND image_id = :image_id");
            getvParams().addValue("image_id", id);
        }
        RowMapper<Image> vRowMapper = new ImageRM();
        Image image = getvNamedParameterJdbcTemplate().queryForObject(vSQL.toString(), getvParams(), vRowMapper);
        return image;
    }

    @Override
    public List<Image> findAllBySite(Site site){
        StringBuilder vSQL= new StringBuilder("Select image.*, image.image_id AS imageId from site_image INNER JOIN image on site_image.image_id= image.image_id  WHERE 1=1 ");
        if(site != null)
        {
            if(site.getId()!=0)
            {
                vSQL.append("AND site_id=:site_id");
                getvParams().addValue("site_id", site.getId());
            }
        }
        RowMapper<Image> vRowMapper = new ImageRM();
        List<Image> vList = getvNamedParameterJdbcTemplate().query(vSQL.toString(),getvParams(),vRowMapper);
        return vList;
    }

    @Override
    public List<Image> findAllBySector(Sector sector){
        StringBuilder vSQL= new StringBuilder("Select image.* from sector_image, image.image_id AS imageId INNER JOIN image on sector_image.image_id= image.image_id WHERE 1=1 ");
        if(sector != null)
        {
            if(sector.getId()!=0)
            {
                vSQL.append("AND sector_id=:sector_id");
                getvParams().addValue("sector_id", sector.getId());
            }
        }
        RowMapper<Image> vRowMapper = new ImageRM();
        List<Image> vList = getvNamedParameterJdbcTemplate().query(vSQL.toString(),getvParams(),vRowMapper);
        return vList;
    }

}

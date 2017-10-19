package com.cliffhangout.dao;

import com.cliffhangout.beans.Image;

public interface ImageDao {
    void create(Image image) throws DaoException;
    void update(Image image) throws DaoException;
    void delete(Image image) throws DaoException;
}

package com.cliffhangout.consumer.impl.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

public abstract class AbstractDaoImpl {

    private DataSource dataSource;
    protected DataSource getDataSource() {
        return dataSource;
    }
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private MapSqlParameterSource vParams;

    protected MapSqlParameterSource getvParams() {
        return vParams;
    }

    public void setvParams(MapSqlParameterSource vParams) {
        this.vParams = vParams;
    }

    private NamedParameterJdbcTemplate vNamedParameterJdbcTemplate;

    protected NamedParameterJdbcTemplate getvNamedParameterJdbcTemplate() {
        return vNamedParameterJdbcTemplate;
    }

    public void setvNamedParameterJdbcTemplate(NamedParameterJdbcTemplate vNamedParameterJdbcTemplate) {
        this.vNamedParameterJdbcTemplate = vNamedParameterJdbcTemplate;
    }

    private JdbcTemplate vJdbcTemplate;

    protected JdbcTemplate getvJdbcTemplate() {
        return vJdbcTemplate;
    }

    public void setvJdbcTemplate(JdbcTemplate vJdbcTemplate) {
        this.vJdbcTemplate = vJdbcTemplate;
    }

    private KeyHolder vKeyHolder;

    protected KeyHolder getvKeyHolder() {
        return vKeyHolder;
    }

    public void setvKeyHolder(KeyHolder vKeyHolder) {
        this.vKeyHolder = vKeyHolder;
    }
}

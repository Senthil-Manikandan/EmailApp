package com.amrita.se.emailapp.repository;

import com.amrita.se.emailapp.mapper.ComposeEmailMapper;
import com.amrita.se.emailapp.mapper.TemplateRowMapper;
import com.amrita.se.emailapp.models.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;



@Repository
public class TemplateRepos {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public Template findByTemplateId(Long templateId){
        String sql = "select * from template where templateid = ? order by templateid desc limit 1;";
        return  jdbcTemplate.queryForObject(sql,new Object[] {templateId},new TemplateRowMapper());

    }

    @Transactional(readOnly = true)
    public Template findByTemplateName(String templateName){
        String sql = "select * from template where templatename = ? order by id desc limit 1";
        return  jdbcTemplate.queryForObject(sql,new Object[] {templateName},new TemplateRowMapper());
    }
}

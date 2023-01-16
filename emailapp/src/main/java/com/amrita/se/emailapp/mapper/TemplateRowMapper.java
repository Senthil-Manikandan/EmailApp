package com.amrita.se.emailapp.mapper;

import com.amrita.se.emailapp.models.Template;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TemplateRowMapper implements RowMapper<Template> {

    @Override
    public Template mapRow(ResultSet rs,int i) throws SQLException{
        Template newTemp = new Template();
        newTemp.setTemplateId(rs.getLong("templateid"));
        newTemp.setTemplateName(rs.getString("templatename"));
        newTemp.setTemplateSubject(rs.getString("templatesubject"));
        newTemp.setTemplateBody(rs.getString("templatebody"));
        newTemp.setAbstractContent(rs.getString("abstractcontent"));
        return newTemp;
    }
}

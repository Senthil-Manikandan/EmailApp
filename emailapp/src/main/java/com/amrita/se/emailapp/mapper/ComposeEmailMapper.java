package com.amrita.se.emailapp.mapper;

import com.amrita.se.emailapp.models.ComposeEmail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComposeEmailMapper implements RowMapper<ComposeEmail> {

    public ComposeEmail mapRow(ResultSet rs, int rowNum) throws SQLException {
        ComposeEmail newMail = new ComposeEmail();
        newMail.setId(rs.getLong("id"));
        newMail.setSenderEmail(rs.getString("senderemail"));
        newMail.setReceiverEmail(rs.getString("receiveremail"));
        newMail.setCc(rs.getString("cc"));
        newMail.setSubject(rs.getString("subject"));
        newMail.setMsgBody(rs.getString("msgbody"));
        newMail.setTemplateId(rs.getLong("templateid"));
        newMail.setEsc(rs.getLong("esc"));
        newMail.setCurrentEscLevel(rs.getLong("currentesclevel"));
        return newMail;
    }
}

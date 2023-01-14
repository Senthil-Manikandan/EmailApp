package com.amrita.se.emailapp.mapper;

import com.amrita.se.emailapp.models.ComposeEmail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComposeEmailRowMapper implements RowMapper<ComposeEmail> {

    public ComposeEmail mapRow(ResultSet rs, int i) throws SQLException{
        ComposeEmail newMail = new ComposeEmail();
        newMail.setId(rs.getLong("id"));
        newMail.setSenderEmail(rs.getString("sender_email"));
        newMail.setReceiverEmail(rs.getString("receiver_email"));
        newMail.setSubject(rs.getString("subject"));
        newMail.setMsgBody(rs.getString("msg_body"));
        return newMail;
    }

}

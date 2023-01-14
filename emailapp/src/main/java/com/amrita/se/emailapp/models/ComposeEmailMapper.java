package com.amrita.se.emailapp.models;

import com.amrita.se.emailapp.models.ComposeEmail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComposeEmailMapper implements RowMapper<ComposeEmail> {

    public ComposeEmail mapRow(ResultSet rs, int rowNum) throws SQLException {
        ComposeEmail email = new ComposeEmail();
        email.setId(rs.getLong("id"));
        email.setSenderEmail(rs.getString("sender_email"));
        email.setReceiverEmail(rs.getString("receiver_email"));
        email.setSubject(rs.getString(("subject")));
        email.setMsgBody(rs.getString("msg_body"));
        return email;
    }
}

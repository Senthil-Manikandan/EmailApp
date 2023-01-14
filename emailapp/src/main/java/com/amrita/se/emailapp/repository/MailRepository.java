package com.amrita.se.emailapp.repository;

import com.amrita.se.emailapp.models.ComposeEmail;
import com.amrita.se.emailapp.models.ComposeEmailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MailRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<ComposeEmail> findBySenderEmail(String receiverEmail){
        String sql = "select * from compose_email where receiver_email = ? order by id desc limit 25";
        return  jdbcTemplate.query(sql,new Object[] {receiverEmail},new ComposeEmailMapper());
    }

    @Transactional
    public boolean deleteEmail(Long id){
        String deletSql = "delete from compose_email where id = ?";
        return jdbcTemplate.update(deletSql,id) > 0 ;
    }

}

package com.amrita.se.emailapp.repository;

import com.amrita.se.emailapp.models.ComposeEmail;
import com.amrita.se.emailapp.models.ComposeEmailMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComposeEmailRepository extends JpaRepository <ComposeEmail,Long> {

}

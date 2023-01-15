package com.amrita.se.emailapp.repository;

import com.amrita.se.emailapp.models.ComposeEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComposeEmailRepository extends JpaRepository <ComposeEmail,Long> {

}

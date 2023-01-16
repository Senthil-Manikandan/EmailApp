package com.amrita.se.emailapp.repository;

import com.amrita.se.emailapp.models.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemplateRepository extends JpaRepository<Template,Long> {
}

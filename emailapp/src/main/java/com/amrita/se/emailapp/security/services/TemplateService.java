package com.amrita.se.emailapp.security.services;

import com.amrita.se.emailapp.models.Template;
import com.amrita.se.emailapp.payload.request.TemplateRequest;
import com.amrita.se.emailapp.payload.response.MessageResponse;

import java.util.List;
import java.util.Optional;

public interface TemplateService {
    MessageResponse createTemplate(TemplateRequest templateRequest);
    Template updateTemplate(TemplateRequest templateRequest);
    boolean deleteTemplate(Long templateId);
    List<Template> getAllTemplate();
    Template getSingleTemplate(Long TemplateId);
}

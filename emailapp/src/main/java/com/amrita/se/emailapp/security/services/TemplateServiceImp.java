package com.amrita.se.emailapp.security.services;

import com.amrita.se.emailapp.models.Template;
import com.amrita.se.emailapp.payload.request.TemplateRequest;
import com.amrita.se.emailapp.payload.response.MessageResponse;
import com.amrita.se.emailapp.repository.TemplateRepos;
import com.amrita.se.emailapp.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemplateServiceImp implements TemplateService{

    @Autowired
    TemplateRepository templateRepository;

    @Autowired
    TemplateRepos templateRepos;

    @Override
    public MessageResponse createTemplate(TemplateRequest templateRequest){
        Template newTemp = new Template();
        newTemp.setTemplateName(templateRequest.getTemplateName());
        newTemp.setTemplateSubject(templateRequest.getTemplateSubject());
        newTemp.setTemplateBody(templateRequest.getTemplateBody());
        newTemp.setAbstractContent(templateRequest.getAbstractContent());
        templateRepository.save(newTemp);
        return new MessageResponse("template saved successfully");
    }

    @Override
    public Template updateTemplate(TemplateRequest templateRequest){
        Template upTemp = templateRepos.findByTemplateName(templateRequest.getTemplateName());
        upTemp.setTemplateSubject(templateRequest.getTemplateSubject());
        upTemp.setTemplateBody(templateRequest.getTemplateSubject());
        upTemp.setAbstractContent(templateRequest.getAbstractContent());
        return upTemp;
    }

    @Override
    public boolean deleteTemplate(Long templateId) {
        return false;
    }

    @Override
    public List<Template> getAllTemplate() {
        return templateRepository.findAll();
    }
    @Override
    public Template getSingleTemplate(Long TemplateId){
        return templateRepos.findByTemplateId(TemplateId);
    }
}

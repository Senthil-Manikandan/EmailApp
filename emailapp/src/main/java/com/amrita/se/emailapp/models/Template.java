package com.amrita.se.emailapp.models;

import javax.persistence.*;

@Entity
@Table(name = "template")
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "templateid")
    private long templateId;

    @Column(name = "templatename")
    private String templateName;

    @Column(name = "templatesubject")
    private String templateSubject;

    @Column(name = "templatebody", columnDefinition = "TEXT")
    private String templateBody;

    @Column(name = "abstractcontent" , columnDefinition = "TEXT")
    private String abstractContent;

    public Template() {
    }

    public Template(long templateId, String templateName, String templateSubject, String templateBody, String abstractContent) {
        this.templateId = templateId;
        this.templateName = templateName;
        this.templateSubject = templateSubject;
        this.templateBody = templateBody;
        this.abstractContent = abstractContent;
    }

    public long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(long templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateSubject() {
        return templateSubject;
    }

    public void setTemplateSubject(String templateSubject) {
        this.templateSubject = templateSubject;
    }

    public String getTemplateBody() {
        return templateBody;
    }

    public void setTemplateBody(String templateBody) {
        this.templateBody = templateBody;
    }

    public String getAbstractContent() {
        return abstractContent;
    }

    public void setAbstractContent(String abstractContent) {
        this.abstractContent = abstractContent;
    }

}

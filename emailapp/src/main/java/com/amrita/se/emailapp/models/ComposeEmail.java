package com.amrita.se.emailapp.models;

import javax.persistence.*;

@Entity
@Table(name = "composeemail")
public class ComposeEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "senderemail")
    private String senderEmail;

    @Column(name = "receiveremail")
    private String receiverEmail;

    @Column(name = "cc")
    private String cc;

    @Column(name = "subject")
    private String subject;

    @Column(name = "msgbody")
    private String msgBody;

    @Column(name = "templateid")
    private Long templateId;

    @Column(name = "esc")
    private Long esc;

    @Column(name = "currentesclevel")
    private Long currentEscLevel;

    public ComposeEmail(){}

    public ComposeEmail(Long id, String senderEmail, String receiverEmail, String cc, String subject, String msgBody, Long templateId, Long esc, Long currentEscLevel) {
        this.id = id;
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
        this.cc = cc;
        this.subject = subject;
        this.msgBody = msgBody;
        this.templateId = templateId;
        this.esc = esc;
        this.currentEscLevel = currentEscLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Long getEsc() {
        return esc;
    }

    public void setEsc(Long esc) {
        this.esc = esc;
    }

    public Long getCurrentEscLevel() {
        return currentEscLevel;
    }

    public void setCurrentEscLevel(Long currentEscLevel) {
        this.currentEscLevel = currentEscLevel;
    }
}

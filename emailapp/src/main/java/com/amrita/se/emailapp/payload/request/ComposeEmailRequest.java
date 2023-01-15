package com.amrita.se.emailapp.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ComposeEmailRequest {

    @NotBlank
    @NotNull
    private String senderEmail;

    @NotBlank
    @NotNull
    private String receiverEmail;

    private String cc;

    @NotBlank
    @NotNull
    private String subject;

    @NotBlank
    @NotNull
    private String msgBody;

    @NotBlank
    private Long templateId;

    @NotBlank
    private Long esc;

    @NotBlank
    private Long currentEscLevel;

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

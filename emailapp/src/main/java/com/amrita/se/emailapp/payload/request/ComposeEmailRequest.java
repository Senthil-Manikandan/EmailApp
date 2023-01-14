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

    @NotBlank
    @NotNull
    private String subject;

    @NotBlank
    @NotNull
    private String msgBody;

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
}

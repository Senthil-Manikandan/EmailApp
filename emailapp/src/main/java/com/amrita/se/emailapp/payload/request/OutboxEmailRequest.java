package com.amrita.se.emailapp.payload.request;

public class OutboxEmailRequest {

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    private String senderEmail;
}

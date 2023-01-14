package com.amrita.se.emailapp.security.services;

import com.amrita.se.emailapp.models.ComposeEmail;
import com.amrita.se.emailapp.payload.request.ComposeEmailRequest;
import com.amrita.se.emailapp.payload.request.InboxEmailRequest;
import com.amrita.se.emailapp.payload.response.MessageResponse;
import com.amrita.se.emailapp.repository.ComposeEmailRepository;
import com.amrita.se.emailapp.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComposeEmailServiceImpl implements ComposeEmailService {

    @Autowired
    ComposeEmailRepository composeEmailRepository;

    @Autowired
    MailRepository mailRepository;

    @Override
    public MessageResponse createComposeEmail(ComposeEmailRequest composeEmailRequest){
        ComposeEmail newEmail = new ComposeEmail();
        newEmail.setSenderEmail(composeEmailRequest.getSenderEmail());
        newEmail.setReceiverEmail(composeEmailRequest.getReceiverEmail());
        newEmail.setMsgBody(composeEmailRequest.getMsgBody());
        newEmail.setSubject(composeEmailRequest.getSubject());
        composeEmailRepository.save(newEmail);
        return new MessageResponse("Mail sent successfully");
    }

    @Override
    public Optional<ComposeEmail> updateComposeEmail(Integer id, ComposeEmailRequest composeEmailRequest) {
        Optional<ComposeEmail>  mail = composeEmailRepository.findById(Long.valueOf(id));
//        if(mail.isEmpty()){
//            throw new ConfigDataResourceNotFoundException("mail");
//        }
        mail.get().setSenderEmail(composeEmailRequest.getSenderEmail());
        mail.get().setReceiverEmail(composeEmailRequest.getReceiverEmail());
        mail.get().setMsgBody(composeEmailRequest.getMsgBody());
        mail.get().setSubject(composeEmailRequest.getSubject());
        composeEmailRepository.save(mail.get());
        return mail;
    }

    @Override
    public ComposeEmail getSingleEmail(Long id){
        return composeEmailRepository.findById(id).orElseThrow();
    }

    public List<ComposeEmail> getInboxEmail(InboxEmailRequest inboxEmailRequest){
        List<ComposeEmail> mails = mailRepository.findBySenderEmail(inboxEmailRequest.getReceiverEmail());
        return mails;
    }

    @Override
    public List<ComposeEmail> getAllComposeEmail(){
        return composeEmailRepository.findAll();
    }

    @Override
    public boolean deleteComposeEmail(Long id){
            return mailRepository.deleteEmail(id);

    }

}

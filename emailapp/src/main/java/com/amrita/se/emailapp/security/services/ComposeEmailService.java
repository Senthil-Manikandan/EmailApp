package com.amrita.se.emailapp.security.services;

import com.amrita.se.emailapp.models.ComposeEmail;
import com.amrita.se.emailapp.payload.request.ComposeEmailRequest;
import com.amrita.se.emailapp.payload.request.InboxEmailRequest;
import com.amrita.se.emailapp.payload.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ComposeEmailService {
    MessageResponse createComposeEmail(ComposeEmailRequest composeEmailRequest);
    Optional<ComposeEmail> updateComposeEmail(Integer id,ComposeEmailRequest composeEmailRequest);
    boolean deleteComposeEmail(Long id);
    ComposeEmail getSingleEmail(Long id);

    List<ComposeEmail> getInboxEmail(InboxEmailRequest inboxEmailRequest);

    List<ComposeEmail> getAllComposeEmail();
}

package com.amrita.se.emailapp.controllers;

import com.amrita.se.emailapp.models.User;
import com.amrita.se.emailapp.payload.request.LoginRequest;
import com.amrita.se.emailapp.payload.request.OtpRequest;
import com.amrita.se.emailapp.payload.request.ResetPasswordRequest;
import com.amrita.se.emailapp.payload.request.SignupRequest;
import com.amrita.se.emailapp.repository.EmailService;
import com.amrita.se.emailapp.repository.RoleRepository;
import com.amrita.se.emailapp.repository.UserRepository;
import com.amrita.se.emailapp.security.jwt.JwtUtils;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {
    @InjectMocks
    AuthController authController;

    @Mock
    UserRepository userRepository;
//    @Mock
//    AuthController authController;

    @Mock
    RoleRepository roleRepository;

    @Mock
    private EmailService emailService;

    @Mock
    PasswordEncoder encoder;

    @Mock
    JwtUtils jwtUtils;

    @Test
    void verifyUserOtpPositiveTest() throws Exception
    {
        OtpRequest or=new OtpRequest();
        or.setResetToken("99948");
        or.setEmail("senthilmanikandan0780@gmail.com");

        User user = new User("senthil","senthilmanikandan0780@gmail.com","qwerty12345","99948");

        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(user);
        when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(true);
        ResponseEntity<?> result= authController.verifyOtp(or);
        assertTrue(result.getStatusCode().is2xxSuccessful());
    }

    @Test
    void verifyUserOtpNegativeTest() throws Exception
    {
        OtpRequest or=new OtpRequest();
        or.setResetToken("99948");
        or.setEmail("senthilmanikandan0780@gmail.com");
        User user = new User("senthil","senthilmanikandan0780@gmail.com","qwerty12345","99945");
        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(user);
        when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(true);
        ResponseEntity<?> result= authController.verifyOtp(or);
        assertTrue(result.getStatusCode().is4xxClientError());
    }

    @Test
    void verifyResetPasswordPostiveTest() throws Exception
    {
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setEmail("brijesh28@gmail.com");
        resetPasswordRequest.setPassword("qwerty12345");

        User user = new User("brijesh","brijesh28@gmail.com","qwerty123456","12345");

        when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(true);
        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(user);

        ResponseEntity<?> result = authController.resetPassword(resetPasswordRequest);
        assertTrue(result.getStatusCode().is2xxSuccessful());
    }

    @Test
    void verifyResetPasswordNegativeTest() throws Exception
    {
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setEmail("brijesh28@gmail.com");
        resetPasswordRequest.setPassword("qwerty12345");

        User user = new User("brijesh","brijesh28@gmail.com","qwerty123456","12345");

        when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(false);
//        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(user);

        ResponseEntity<?> result = authController.resetPassword(resetPasswordRequest);
        assertTrue(result.getStatusCode().is4xxClientError());
    }

    @Test
    void registerUserNegativeTestUserNameDoesnotExist() throws Exception{
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("vicky");
        signupRequest.setEmail("vicky28@gmail.com");
        signupRequest.setPassword("asdef1234");
        Set<String> role = new HashSet<>();
//        role.add("admin");
        System.out.println(role);
        signupRequest.setRole(role);
        when(userRepository.existsByUsername(Mockito.anyString())).thenReturn(true);
//        when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(false);

        ResponseEntity<?> result = authController.registerUser(signupRequest);
        assertTrue(result.getStatusCode().is4xxClientError());
    }

    @Test
    void registerUserNegativeTestEmailDoesnotExist() throws Exception{
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("vicky");
        signupRequest.setEmail("vicky28@gmail.com");
        signupRequest.setPassword("asdef1234");
        Set<String> role = new HashSet<>();
//        role.add("admin");
        System.out.println(role);
        signupRequest.setRole(role);
        when(userRepository.existsByUsername(Mockito.anyString())).thenReturn(false);
        when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(true);

        ResponseEntity<?> result = authController.registerUser(signupRequest);
        assertTrue(result.getStatusCode().is4xxClientError());
    }

    @Test
    void registerUserPostiveTest() throws Exception{
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("vicky");
        signupRequest.setEmail("vicky28@gmail.com");
        signupRequest.setPassword("asdef1234");
        Set<String> role = new HashSet<>();
//        role.add("admin");
        System.out.println(role);
        signupRequest.setRole(role);
        when(userRepository.existsByUsername(Mockito.anyString())).thenReturn(false);
        when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(false);

        ResponseEntity<?> result = authController.registerUser(signupRequest);
        assertTrue(result.getStatusCode().is2xxSuccessful());
    }

}

package com.amrita.se.emailapp.controllers;


import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.amrita.se.emailapp.models.*;
import com.amrita.se.emailapp.payload.request.*;
import com.amrita.se.emailapp.repository.EmailService;
import com.amrita.se.emailapp.security.services.ComposeEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.amrita.se.emailapp.payload.response.UserInfoResponse;
import com.amrita.se.emailapp.payload.response.MessageResponse;
import com.amrita.se.emailapp.repository.RoleRepository;
import com.amrita.se.emailapp.repository.UserRepository;
import com.amrita.se.emailapp.security.jwt.JwtUtils;
import com.amrita.se.emailapp.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ComposeEmailService composeEmailService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),"");

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
//        user.setResetToken("");
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/forgotpassword")
    public  ResponseEntity<?> forgotpassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest){

        if (!userRepository.existsByEmail(forgotPasswordRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email not found!"));
        }

        Random r = new Random( System.currentTimeMillis() );
        int otp=10000 + r.nextInt(20000);
        User updateUserOtp = userRepository.findByEmail(forgotPasswordRequest.getEmail());

        updateUserOtp.setResetToken(String.valueOf(otp));
        userRepository.save(updateUserOtp);
        String emailBody = "A request for OTP has been raised by the mail id "+forgotPasswordRequest.getEmail()+" and the otp for the same is\n"+String.valueOf(otp);
        String regards = "\nPlease don't share the otp with anyone.\nOTP valid for 10 minutes\n\n\nThank you\nEmail App team\nContact:emailappteam@emailapp.com";
        EmailDetails emailDetails=new EmailDetails();
        emailDetails.setRecipient(forgotPasswordRequest.getEmail());
        emailDetails.setMsgBody(emailBody+regards);
        emailDetails.setSubject("Verification OTP for Amrita Mail App");

        String status = emailService.sendSimpleMail(emailDetails);


        return ResponseEntity.ok(new MessageResponse(status));
    }



    @PostMapping("/forgotpassword/verifyotp")
    public ResponseEntity<?> verifyOtp(@Valid @RequestBody OtpRequest otpRequest){

        if (!userRepository.existsByEmail(otpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email not found"));
        }

        String dataBaseOtp=userRepository.findByEmail(otpRequest.getEmail()).getResetToken();

        if(dataBaseOtp.equals(otpRequest.getResetToken()))
        {
            return ResponseEntity.ok(new MessageResponse("OTP verified sucessfully"));
        }
        else {
            return ResponseEntity.badRequest().body(new MessageResponse("OTP mismatch"));
        }
//        return ResponseEntity.ok(new MessageResponse("otp verified"));
    }

    @PostMapping("/forgotpassword/verifyotp/resetpassword")
    public ResponseEntity resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest){

        if(!userRepository.existsByEmail(resetPasswordRequest.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Mail not found"));
        }

        User userUpdatePassword = userRepository.findByEmail(resetPasswordRequest.getEmail());

        userUpdatePassword.setPassword(encoder.encode(resetPasswordRequest.getPassword()));
        userRepository.save(userUpdatePassword);


        return ResponseEntity.ok(new MessageResponse("password updated"));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ComposeEmail>> getAllEmail(){
        List<ComposeEmail> mails = composeEmailService.getAllComposeEmail();
        return new ResponseEntity<>(mails,HttpStatus.OK);
    }

    @PutMapping("/inbox")
    public  ResponseEntity<ComposeEmail> getInbox(@Valid @RequestBody InboxRequest inboxRequest ){
        ComposeEmail composeEmail = composeEmailService.getSingleEmail(inboxRequest.getId());
        return new ResponseEntity<>(composeEmail,HttpStatus.OK);
    }

    @PutMapping("/inbox/all")
    public ResponseEntity<List<ComposeEmail>> getAllInboxEmail(@Valid @RequestBody InboxEmailRequest inboxEmailRequest){
        List<ComposeEmail> mails = composeEmailService.getInboxEmail(inboxEmailRequest);
        return new ResponseEntity<>(mails,HttpStatus.OK);
    }

    @PostMapping("/composeemail")
    public ResponseEntity<?> addMail(@RequestBody ComposeEmailRequest composeEmailRequest){
        MessageResponse newMail = composeEmailService.createComposeEmail(composeEmailRequest);
        return new ResponseEntity<>(newMail,HttpStatus.CREATED);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteEmail(@Valid @RequestBody InboxRequest inboxRequest){
        boolean i = composeEmailService.deleteComposeEmail(inboxRequest.getId());
        if(!i){
            return new ResponseEntity<>("not exist",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }

}

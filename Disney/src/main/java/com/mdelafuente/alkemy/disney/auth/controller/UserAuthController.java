package com.mdelafuente.alkemy.disney.auth.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdelafuente.alkemy.disney.auth.service.JwtUtils;
import com.mdelafuente.alkemy.disney.auth.dto.AuthenticationRequest;
import com.mdelafuente.alkemy.disney.auth.dto.AuthenticationResponse;
import com.mdelafuente.alkemy.disney.auth.dto.UserDTO;
import com.mdelafuente.alkemy.disney.auth.service.UserDetailsCustomService;

@RestController
@RequestMapping("/auth")
public class UserAuthController {
    private JwtUtils jwtTokenUtil;
    private AuthenticationManager authenticationManager;
    private UserDetailsCustomService userDetailsService;

    @Autowired
    public UserAuthController(
            UserDetailsCustomService userDetails,
            AuthenticationManager authenticationManager,
            JwtUtils jwtTokenUtil
    ) {
        this.userDetailsService = userDetails;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> singUp(@Valid @RequestBody UserDTO user) throws Exception {
        this.userDetailsService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> singIn(@RequestBody AuthenticationRequest authRequest) throws Exception {
        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password");
        }

        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}



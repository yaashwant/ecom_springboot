package com.ecom.project.service;

import com.ecom.project.model.User;
import com.ecom.project.repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class userService {

    @Autowired
    private userRepo repo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public User registerNewUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return repo.save(user);
    }

    public String verify(User user) {
        Authentication auth =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if(auth.isAuthenticated())
            return jwtService.generateToken(user.getEmail());

        return "fail";
    }
}

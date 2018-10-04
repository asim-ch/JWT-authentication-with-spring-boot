package com.example.RestApi;

import com.example.RestApi.Configs.AccountCredentials;
import com.example.RestApi.Configs.CustomUserDetailsService;
import com.example.RestApi.Configs.ResponseToken;
import com.example.RestApi.Configs.TokenProvider;
import com.example.RestApi.Entities.Login;
import com.example.RestApi.Repositories.LoginJPDAO;
import com.example.RestApi.Repositories.UserRoleJPDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;


@RestController
public class Logincontroller {

    @Autowired
    CustomUserDetailsService userDetailsService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    LoginJPDAO userRepository;

    @Autowired
    UserRoleJPDAO roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TokenProvider jwtProvider;

    @PostMapping("/api/auth/signin")
    public ResponseEntity<?> authenticateUser(@RequestParam("username") String username,
                                              @RequestParam("password") String password, HttpServletResponse response) throws UnsupportedEncodingException {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new ResponseToken(jwt, "Bearer"));
    }

    @PostMapping("/api/auth/signup")
    public String signIn(@RequestBody AccountCredentials credentials) {

        Login login = new Login();
        login.setUserName(credentials.getUserName());
        login.setPassword(passwordEncoder.encode(credentials.getPassword()));
        login.setActive(1);
        userRepository.save(login);
        return "success";
    }
    @PostMapping(value = "/api/allusers")
    public List hello() {
        return userDetailsService.findAll();
    }

}

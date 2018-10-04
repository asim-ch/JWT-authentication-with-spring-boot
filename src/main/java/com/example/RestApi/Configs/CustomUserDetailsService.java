package com.example.RestApi.Configs;

import com.example.RestApi.Entities.Login;
import com.example.RestApi.Repositories.LoginJPDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

        @Autowired
        LoginJPDAO loginJPDAO;

        @Override
        public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
            Login user = loginJPDAO.findByUserName(username);
            if (user!=null)
                return UserPrincipal.create(user);
            else
                new UsernameNotFoundException("user not found");
            return null;
        }

        public UserPrincipal loadUserById(Long id) {
            Login user = loginJPDAO.getOne(id);
            if (user != null)
                return UserPrincipal.create(user);
            else
                new UsernameNotFoundException("user not found");
            return null;
    }

    public List<UserPrincipal> findAll(){
            List<UserPrincipal> list = loginJPDAO.findAll().stream().map(UserPrincipal::create).collect(Collectors.toList());
            return list;
    }

    public void saveUser(UserPrincipal userPrincipal){

            Login login = new Login();
            login.setUserName(userPrincipal.getUsername());
            login.setPassword(userPrincipal.getPassword());
            login.setActive(1);

            loginJPDAO.save(login);
    }

    public UserPrincipal findById(Long userId) {
            Login user = loginJPDAO.getOne(userId);
            return UserPrincipal.create(user);
    }
}

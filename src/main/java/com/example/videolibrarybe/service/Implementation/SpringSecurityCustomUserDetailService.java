package com.example.videolibrarybe.service.Implementation;

import com.example.videolibrarybe.dto.AuthenticationUserDTO;
import com.example.videolibrarybe.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SpringSecurityCustomUserDetailService implements UserDetailsService {

    UserService userService;

    @Autowired
    public SpringSecurityCustomUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthenticationUserDTO authenticationUserDTO = userService.getUserByEmailId(username);
        log.info(String.valueOf(authenticationUserDTO));

        return User.withUsername(authenticationUserDTO.emailId())
                .password(authenticationUserDTO.password())
                .build();
    }
}

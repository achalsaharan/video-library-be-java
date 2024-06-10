package com.example.videolibrarybe.config;

import com.example.videolibrarybe.service.Implementation.SpringSecurityCustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    private final SpringSecurityCustomUserDetailService springSecurityCustomUserDetailService;

    @Autowired
    public SecurityConfiguration(SpringSecurityCustomUserDetailService springSecurityCustomUserDetailService) {
        this.springSecurityCustomUserDetailService = springSecurityCustomUserDetailService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(authorize ->
//                        authorize.anyRequest().permitAll()
//                )
                .authorizeHttpRequests(authorise ->
                        authorise
                                .requestMatchers("/user**")
                                .anonymous().requestMatchers(HttpMethod.GET)
                                .authenticated()
                                .anyRequest().permitAll()

                )
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(springSecurityCustomUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;

    }

    // in memory users
//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails normalUser = User.withUsername("userNormal")
//                .password(passwordEncoder().encode("userNormal"))
//                .roles("NORMAL")
//                .build();
//
//        UserDetails adminUser = User.withUsername("userAdmin")
//                .password(passwordEncoder().encode("userAdmin"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(normalUser, adminUser);
//
//    }
}

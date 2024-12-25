package com.example.videolibrarybe.config;

import com.example.videolibrarybe.repository.UserRepository;
import com.example.videolibrarybe.service.Implementation.SpringSecurityCustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ApplicationConfiguration {

    // implements user details service
    private final SpringSecurityCustomUserDetailService springSecurityCustomUserDetailService;
    private final UserRepository userRepository;

    @Autowired
    public ApplicationConfiguration(SpringSecurityCustomUserDetailService springSecurityCustomUserDetailService, UserRepository userRepository) {
        this.springSecurityCustomUserDetailService = springSecurityCustomUserDetailService;
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository
                .getUserByEmailId(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(springSecurityCustomUserDetailService);
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) throws Exception {
//        return new ProviderManager(authenticationProvider);
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

//    @Bean
//    public AuthenticationManager authenticationManager() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
////        provider.setUserDetailsService(springSecurityCustomUserDetailService);
//        provider.setUserDetailsService(userDetailsService());
//        provider.setPasswordEncoder(passwordEncoder());
//        return new ProviderManager(provider);
//    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
//        httpSecurity.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(authorise ->
//                        {
//                            try {
//                                authorise
//                                        .requestMatchers("/auth/**")
//                                        .permitAll()
//                                        .anyRequest()
//                                        .authenticated()
//                                        .and()
//                                        .sessionManagement()
//                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                                        .and()
//                                        .authenticationProvider(authenticationProvider())
//                                        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//                            } catch (Exception e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//
//                )
//                .httpBasic(Customizer.withDefaults());
//
//        return httpSecurity.build();
//    }

}

package com.felipe_dias.backend_java_spring_test.config;

import com.felipe_dias.backend_java_spring_test.config.filter.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultHttpSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;


    @Bean
    WebExpressionAuthorizationManager webExpressionAuthorizationManager(ApplicationContext applicationContext) {
        var securityExpressionHandler = new DefaultHttpSecurityExpressionHandler();

        // Force the usage of the application context where the component is defined
        securityExpressionHandler.setApplicationContext(applicationContext);

        var authorizationManager = new WebExpressionAuthorizationManager(
                "@idChecker.check(authentication,#userId)");
        authorizationManager.setExpressionHandler(securityExpressionHandler);
        return authorizationManager;
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, WebExpressionAuthorizationManager webExpressionAuthorizationManager) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .headers(AbstractHttpConfigurer::disable) //ENABLE H2 CONSOLE TO LOAD
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        //TEST TOOLS

                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/h2-console/**").permitAll()

                        //REGISTER/LOGIN ENDPOINTS

                                .requestMatchers("auth/**").permitAll()

                        //ENDPOINT USERS

                                .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/users/{userId}").access(webExpressionAuthorizationManager)
                                .requestMatchers(HttpMethod.DELETE, "/users").hasRole("ADMIN")


                         //ENDPOINT TASK

                                //.requestMatchers(HttpMethod.POST,"/tasks/{userId}").access(webExpressionAuthorizationManager)
                                //.requestMatchers(HttpMethod.DELETE,"/tasks/{userId}/**").access(webExpressionAuthorizationManager)
                                //.requestMatchers(HttpMethod.PUT, "/tasks/{userId}/**").access(webExpressionAuthorizationManager)
                        .anyRequest().authenticated()





                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}

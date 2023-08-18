package com.example.jonathandewitenterpriseapplications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true, // Allows using @PreAuthorize and @PostAuthorize annotations
        securedEnabled = true, // Allows using @Secured annotation
        jsr250Enabled = true // Allows using @RolesAllowed annotation
)
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        // Configure AuthenticationProvider for DB authentication
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }
    @Bean
    public UserDetailsService userDetailsService() {
        // Service to retrieve user details from the database
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        return manager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Register the authentication provider
                .authenticationProvider(daoAuthenticationProvider())
                // Configure authorization rules
                .authorizeRequests()
                .antMatchers("/home*").permitAll()
                .antMatchers("/products/**").permitAll()
                .antMatchers("/about*").permitAll()
                .antMatchers("/account/login*").permitAll()
                .antMatchers("/account/perform_login*").permitAll()
                .antMatchers("/account/regist*").permitAll()
                .antMatchers("/account/perform_login*").permitAll()
                .antMatchers("/account/accountConfirm*").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/assets/css/**", "assets/js/**", "/img/**").permitAll()
                .antMatchers("/index*").permitAll()
                .antMatchers("/").permitAll()
                // For any request except for the paths above its required to be authenticated.
                .anyRequest().authenticated()



                .and()
                // Configuration for form-based login
                .formLogin()
                .loginPage("/account/login")
                .loginProcessingUrl("/account/perform_login")
                .failureUrl("/account/login?error=true")
                .permitAll()
                .defaultSuccessUrl("/home", true)

                .and().rememberMe()
                // Configuration for "remember me" feature
                .key("supperSecretKey")
                .tokenRepository(tokenRepository())
                .userDetailsService(userDetailsService())

                .and()
                // Configuration for logout
                .logout()
                .logoutSuccessUrl("/account/login?logout=true")
                .logoutRequestMatcher(new AntPathRequestMatcher("/perform_logout", "GET"))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
        ;
        return http.build();
    }

    @Bean
    public PersistentTokenRepository tokenRepository(){
        // Persistent token repository for "remember me" feature
        JdbcTokenRepositoryImpl token = new JdbcTokenRepositoryImpl();
        token.setDataSource(dataSource);
        return token;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Password encoder to hash passwords using BCrypt
        return new BCryptPasswordEncoder();
    }


}

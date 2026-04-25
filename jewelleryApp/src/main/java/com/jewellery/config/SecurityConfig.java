package com.jewellery.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)  throws Exception{
		
		 http
         .csrf(csrf -> csrf.disable())
         .authorizeHttpRequests(auth -> auth
        	 .requestMatchers("/", "/home", "/products", "/css/**", "/images/**").permitAll()
             .requestMatchers("/admin/**").hasRole("ADMIN")
             .anyRequest().authenticated()
         )
         .formLogin(form -> form
             .loginPage("/login")
             .defaultSuccessUrl("/home", true)
             .permitAll()
         )
         .logout(logout -> logout
             .logoutSuccessUrl("/products")
             .permitAll()
         );

     return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {

	    JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

	    users.setUsersByUsernameQuery(
	        "SELECT username, password, enabled FROM users WHERE username=?"
	    );

	    users.setAuthoritiesByUsernameQuery(
	        "SELECT username, role FROM roles WHERE username=?"
	    );

	    return users;
	}
}

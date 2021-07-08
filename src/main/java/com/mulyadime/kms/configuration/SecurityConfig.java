/**
 * 
 */
package com.mulyadime.kms.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mulyadime.kms.services.AccountService;

/**
 * @author Hamid Mulyadi
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AccountService accountService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.eraseCredentials(true).userDetailsService(accountService).passwordEncoder(passwordEncoder());
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/").hasAnyAuthority("USERS", "ADMIN")
				.antMatchers("/resources/**", "/assets/**", "/signUp").permitAll()
				.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/signIn").permitAll()
				.failureUrl("/signIn?error=1").loginProcessingUrl("/authenticate")
			.and()
			.logout().logoutUrl("/logout").permitAll().logoutSuccessUrl("/signIn?logout");
			
	}

}

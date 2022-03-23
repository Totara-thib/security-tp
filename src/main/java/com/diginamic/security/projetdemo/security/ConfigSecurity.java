package com.diginamic.security.projetdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.diginamic.security.projetdemo.dao.AppAuthProvider;
import com.diginamic.security.projetdemo.services.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = true , securedEnabled = true)
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userS;
	
//	Je branche mon service de type UserDetailsService
//	avec AuthenticationManagerBuilder
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userS);
	}
	
	@Bean
	public AuthenticationProvider getProvider() {
		AppAuthProvider provider = new AppAuthProvider();
		provider.setUserDetailsService(userS);
		return provider;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// J'autorise tout !!
		/** http.csrf().disable().
		authorizeRequests().
		anyRequest().
		permitAll().
		and().httpBasic();
		*/
		
		http.csrf().disable()
		.authenticationProvider(getProvider())
		.formLogin()
		.loginProcessingUrl("/login")
		.and()
		.logout()
		.logoutUrl("/logout")
		.invalidateHttpSession(true)
		.and()
		.authorizeRequests()
		.antMatchers("/login").permitAll()
		.antMatchers("/logout").permitAll()
		.antMatchers("api/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.httpBasic();
	}
}

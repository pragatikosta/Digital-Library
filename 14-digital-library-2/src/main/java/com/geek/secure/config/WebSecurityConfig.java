package com.geek.secure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig/* extends WebSecurityConfigurerAdapter*/ {
	
	

	//@Autowired
	//private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	@Autowired
    private PasswordEncoder encoder;
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
	//	AuthenticationManagerBuilder auth= new AuthenticationManagerBuilder();
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(encoder);
	}

	

	/*@Bean
	//@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}*/
	 @Bean
	    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
	        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
	     //   authenticationManagerBuilder.inMemoryAuthentication().withUser("user").password("password").roles("ADMIN");
	        return authenticationManagerBuilder.build();
	    }

	//@Override
	 @Bean
	protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
		System.out.println("---Web security -- configure");
		//httpSecurity.cors().disable();
		// We don't need CSRF for this example
		httpSecurity.cors().and().csrf().disable()
				// dont authenticate this particular request
			.authorizeHttpRequests().
			requestMatchers("/users/register", "/users/login")
			.permitAll().
				// all other requests need to be authenticated
						anyRequest().authenticated();
		/*
		.and().
				// make sure we use stateless session; session won't be used to
				// store user's state.
		
						exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
						.and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				
*/
		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}
}
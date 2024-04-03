package com.j6Store;


import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.j6Store.entity.Account;
import com.j6Store.service.AccountService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	AccountService accountService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable())
				.authorizeHttpRequests((authorize) -> authorize
						.requestMatchers("/order/**").authenticated()
						.requestMatchers("/admin/**").hasAnyRole("STAF", "DIRE")
						.requestMatchers("/rest/authorities").hasRole("DIRE")
						.anyRequest().permitAll())
				
				.formLogin(formLogin -> formLogin
						.loginPage("/security/login/form")
						.loginProcessingUrl("/security/login")
						.defaultSuccessUrl("/security/login/success", false)
						.failureUrl("/security/login/error"))
				
				.rememberMe(rememberme -> rememberme.tokenValiditySeconds(86400))

				.exceptionHandling(denied -> denied.accessDeniedPage("/security/unauthoried"))
				
				.logout(logout -> logout
						.logoutUrl("/security/logoff")
						.logoutSuccessUrl("/security/logoff/success"));
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {

		return new CustomUserDetailsService(accountService);
	}

	@Bean
	public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return new ProviderManager(authenticationProvider);
	}
	
	
	
	
	class CustomUserDetailsService implements UserDetailsService {
	    private final AccountService accountService;

	    public CustomUserDetailsService(AccountService accountService) {
	        this.accountService = accountService;
	    }

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        try {
	            Account user = accountService.findById(username);
	            return org.springframework.security.core.userdetails.User.builder()
	                    .username(username)
	                    .password(passwordEncoder().encode(user.getPassword()))
	                    .roles(user.getAuthorities().stream()
	                            .map(er -> er.getRole().getId())
	                            .collect(Collectors.toList())
	                            .toArray(new String[0]))
	                    .build();
	        } catch (NoSuchElementException e) {
	            throw new UsernameNotFoundException("User not found: " + username);
	        }
	    }
	}	
}

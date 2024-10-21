package org.serratec.serratecpub.util;

import org.springdoc.core.properties.SwaggerUiConfigProperties.Csrf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod; 

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize
			.requestMatchers(HttpMethod.GET, "/pedidos").permitAll()
			.requestMatchers(HttpMethod.POST, "/pedidos").hasRole("ADM"))
			.httpBasic(Customizer.withDefaults())
			.csrf(csrf -> csrf.disable());
		return http.build();
	}
	
	@Bean
	public InMemoryUserDetailsManager userdetalhes() {
		UserDetails usuario1 = User.builder()
				.username("Murilo")
				.password(encoder().encode("teste"))
				.roles("ADM").build();
		UserDetails usuario2 = User.builder()
				.username("Gustavo")
				.password(encoder().encode("teste"))
				.roles("RH").build();
		return new InMemoryUserDetailsManager(usuario1, usuario2);
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}

package org.serratec.serratecpub.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource; 

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize
			.requestMatchers(HttpMethod.GET, "/pedidos/**","/clientes/**","/produtos/**","/itenspedidos/**").permitAll()
			.requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v2/api-docs", "/webjars/**","/swagger-resources/**").permitAll()
			.requestMatchers(HttpMethod.POST, "/pedidos","/clientes","/produtos","/itenspedidos").hasRole("ADM")
			.requestMatchers(HttpMethod.DELETE, "/pedidos/**","/clientes/**","/produtos/**","/itenspedidos/**").hasRole("ADM")
			.requestMatchers(HttpMethod.PUT, "/pedidos/**","/clientes/**","/produtos/**","/itenspedidos/**").hasRole("ADM")
			.requestMatchers(HttpMethod.PATCH, "/pedidos/**","/clientes/**","/produtos/**","/itenspedidos/**").hasRole("ADM"))
			.csrf(csrf -> csrf.disable())
			.httpBasic(Customizer.withDefaults());
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
				.roles("ADM").build();
		return new InMemoryUserDetailsManager(usuario1, usuario2);
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin(""); 
        configuration.addAllowedMethod(""); 
        configuration.addAllowedHeader("*"); 
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

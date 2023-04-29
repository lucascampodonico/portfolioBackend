package com.porfolio.lucascampodonico.config;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity //Habilita la seguridad web en la aplicación, permitiendo la configuración de reglas de seguridad.
@RequiredArgsConstructor //Es una anotación de Lombok que genera automáticamente un constructor con argumentos para las variables finales.
public class SecurityConfiguration {

    //Inyección de dependencias
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    
    @Bean
    //securityFilterChain configura la cadena de filtros de seguridad en la aplicación utilizando el objeto HttpSecurity pasado como parámetro.
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf().disable() //Se deshabilita la protección CSRF (Cross-Site Request Forgery) en la aplicación.
            .cors(Customizer.withDefaults())
            .authorizeHttpRequests() //Se define la autorización de las peticiones HTTP.
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET).permitAll()
                .requestMatchers("/index.html").permitAll() //Se permite el acceso a todas las peticiones que coincidan con el patrón "/api/v1/auth/**", lo que significa que no requieren autenticación.
                .requestMatchers("/api/v1/media/uploads/**").permitAll()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .anyRequest().authenticated() //Se permite el acceso a todas las peticiones que coincidan con el patrón "/api/v1/auth/**", lo que significa que no requieren autenticación.
            .and()
            .exceptionHandling() //Se configura el manejo de excepciones en la seguridad.
            .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Se configura la gestión de sesiones como "sin estado" (stateless), lo que significa que no se almacenará ninguna información de sesión en el servidor.
            .and()
            .authenticationProvider(authenticationProvider) //Se establece el AuthenticationProvider inyectado previamente en la configuración de seguridad.
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) //Se agrega un filtro personalizado de autenticación basado en JWT (JwtAuthenticationFilter) antes del filtro de autenticación de usuario y contraseña (UsernamePasswordAuthenticationFilter).
            .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
	CorsConfiguration configuration = new CorsConfiguration();
	configuration.setAllowedOrigins(Arrays.asList("*"));
	configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	source.registerCorsConfiguration("/**", configuration);
	return source;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Aplica CORS a todas las rutas
                    .allowedOrigins("*") // Permitir todos los orígenes
                    .allowedOrigins("http://localhost:8080")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS"); // Métodos permitidos
            }
        };
    }
}

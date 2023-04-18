package com.porfolio.lucascampodonico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.porfolio.lucascampodonico.user.IUserRepository;

import lombok.RequiredArgsConstructor;


//Indica que esta clase es una clase de configuración de Spring.
@Configuration

//Es una anotación de Lombok que genera automáticamente un constructor con argumentos para las variables finales.
@RequiredArgsConstructor

public class ApplicationConfig {

    //La clase ApplicationConfig tiene una inyección de dependencias a través del constructor para IUserRepository, que es una interfaz de repositorio definida en otra parte del código.
    private final IUserRepository repository;


    //Los métodos anotados con @Bean definen beans de Spring, que son objetos gestionados por el contenedor de Spring y se pueden utilizar en otras partes de la aplicación
    @Bean
    //UserDetailsService se utiliza para cargar los detalles del usuario, en este caso a través de la implementación de una función lambda que busca un usuario por su email utilizando el repositorio IUserRepository.
    public UserDetailsService userDetailsService(){
        return username -> repository.findByEmail(username)
        .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
    
    @Bean
    //AuthenticationProvider proporciona la lógica de autenticación personalizada utilizando el UserDetailsService y un PasswordEncoder para verificar las contraseñas de los usuarios.
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider aProvider = new DaoAuthenticationProvider();
        aProvider.setUserDetailsService(userDetailsService());
        aProvider.setPasswordEncoder(passwordEncoder());
        return aProvider;
    }

    @Bean
    //AuthenticationManager se utiliza para configurar el administrador de autenticación de Spring Security, obteniendo la configuración a través de AuthenticationConfiguration.
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    //PasswordEncoder se utiliza para codificar y descodificar contraseñas usando el algoritmo de encriptación BCrypt.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}



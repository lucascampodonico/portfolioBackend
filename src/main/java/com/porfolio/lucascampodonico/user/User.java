package com.porfolio.lucascampodonico.user;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Es una anotación de Lombok, una biblioteca de Java que reduce la cantidad de código repetitivo, generando automáticamente métodos getter, setter, equals, hashCode y toString. En este caso, la anotación @Data genera automáticamente estos métodos para la clase User.
@Builder //Es una anotación de Lombok que genera automáticamente un constructor de tipo "builder" para la clase User. Este constructor permite construir objetos User utilizando un estilo de programación fluida y encadenada.
@NoArgsConstructor 
@AllArgsConstructor //Son anotaciones de Lombok que generan automáticamente constructores sin argumentos y con todos los argumentos, respectivamente, para la clase User.
@Entity // Es una anotación de JPA que indica que la clase User es una entidad mapeada a una tabla en una base de datos. Esto permite que la clase User sea persistida y manipulada en la base de datos.
// @Table(name= "User")
public class User implements UserDetails{
    //implements UserDetails: Indica que la clase User implementa la interfaz UserDetails de Spring Security, que define los métodos necesarios para representar los detalles de un usuario en un sistema de autenticación y autorización.

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @Column(unique = true)
  
    @Email
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    //son implementaciones de la interfaz UserDetails que definen el comportamiento de un usuario en un sistema de autenticación y autorización, como la obtención de roles, nombre de usuario, contraseña y estado de cuenta.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority(role.name()));

    }

    @Override
    public String getUsername() {

        return email;
    }

    @Override
    public String getPassword(){
        return password;
    }
    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
    
}

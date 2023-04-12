package com.porfolio.lucascampodonico.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer>{

    //extends JpaRepository<User, Integer>: Indica que IUserRepository extiende la interfaz JpaRepository de Spring Data JPA, lo que permite heredar métodos predefinidos para operaciones de CRUD (Create, Read, Update, Delete) en la base de datos. La clase User especifica el tipo de entidad que se manipulará en la base de datos, y Integer especifica el tipo de dato del ID de la entidad.
    
    Optional<User> findByEmail(String email);
    //Este es un método definido en la interfaz IUserRepository para buscar un User en la base de datos por su email. El método utiliza la convención de nomenclatura de Spring Data JPA para definir consultas personalizadas basadas en el nombre del método. En este caso, findByEmail indica que se busca un usuario por su email. El parámetro String email especifica el valor del email que se utilizará en la consulta. El método retorna un Optional<User>, que es una envoltura opcional que puede contener un User o ser vacía (null) si no se encuentra ningún resultado en la base de datos.
}

//En resumen, la interfaz IUserRepository define una interfaz de repositorio que hereda de JpaRepository de Spring Data JPA, y proporciona un método personalizado findByEmail para buscar un usuario por su email en la base de datos.
package com.porfolio.lucascampodonico.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
//Este código define una clase llamada JwtAuthenticationFilter que extiende de la clase OncePerRequestFilter de Spring Security, lo que indica que este filtro será aplicado una vez por cada petición HTTP.
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    //Es una instancia de una clase JwtService, que es responsable de la generación, validación y extracción de tokens JWT (JSON Web Token).
    private final JwtService jwtService;

    //Es una instancia de una clase UserDetailsService, que es una interfaz de Spring Security para cargar los detalles del usuario (como su nombre de usuario, contraseña y roles) a partir de una fuente de datos (como una base de datos o un servicio externo).
    private final UserDetailsService userDetailsService;
    
    @Override
    //Este es el método principal de este filtro y se encarga de implementar la lógica de autenticación basada en JWT.
    protected void doFilterInternal(
      HttpServletRequest request, //Es el objeto que representa la solicitud HTTP entrante.
      HttpServletResponse response, //Es el objeto que representa la respuesta HTTP saliente.
      FilterChain filterChain //Es el objeto que representa la cadena de filtros de seguridad de Spring que será ejecutada después de que este filtro termine de procesar la solicitud.
  ) throws ServletException, IOException {

    //Obtiene el encabezado "Authorization" de la solicitud HTTP, que debe contener el token JWT.
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String userEmail;

    //Verifica si el valor del encabezado comienza con "Bearer ", que es el prefijo estándar para los tokens JWT en los encabezados de autorización.
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }
    try {
      //Si el valor del encabezado comienza con "Bearer ", extrae el token JWT eliminando el prefijo "Bearer " y lo utiliza para obtener el nombre de usuario del token mediante el servicio jwtService.extractUsername(jwt).
    jwt = authHeader.substring(7);

    userEmail = jwtService.extractUsername(jwt);
      if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

        //Si se cumplen estas condiciones, carga los detalles del usuario a partir del servicio userDetailsService.loadUserByUsername(userEmail) y verifica si el token JWT es válido para el usuario mediante el servicio jwtService.isTokenValid(jwt, userDetails).
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
          if (jwtService.isTokenValid(jwt, userDetails)) {
  
            //Si el token es válido, crea un objeto UsernamePasswordAuthenticationToken con los detalles del usuario y lo establece en el contexto de seguridad de Spring mediante SecurityContextHolder.getContext().setAuthentication(authToken).
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
            );
            authToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
            );
            SecurityContextHolder.getContext().setAuthentication(authToken);
          }
       
      }
    //Luego, verifica si el nombre de usuario obtenido del token es diferente de null y si no hay una autenticación previa en el contexto de seguridad de Spring.
    

    //Si el valor del encabezado no comienza con "Bearer ", pasa la solicitud al siguiente filtro en la cadena.
    filterChain.doFilter(request, response);
    } catch (ExpiredJwtException e) {
       // Token expirado
            logger.warn("Token expired: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token expired"); // Mensaje personalizado
    }
    
  }
    
}

//En resumen, este filtro de autenticación basado en JWT verifica el token JWT en el encabezado de autorización de las solicitudes HTTP entrantes, carga los detalles del usuario y establece la autenticación en el contexto de seguridad de Spring si el token es válido.
package com.porfolio.lucascampodonico.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY = "645267556B58703273357638792F423F4528482B4B6250655368566D59713374";

    //Este método toma un token JWT como entrada y extrae el nombre de usuario (subject) del token utilizando una función de resolución de reclamos (claimsResolver) que se pasa como argumento. Devuelve el nombre de usuario extraído del token.
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //Este método generaliza la extracción de reclamos de un token JWT. Toma un token JWT y una función de resolución de reclamos (claimsResolver) como argumentos y utiliza la función de resolución de reclamos para extraer un reclamo específico del token. Devuelve el valor del reclamo extraído del token.
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    //Este método genera un nuevo token JWT basado en los detalles del usuario proporcionados (userDetails) como argumento, junto con reclamos adicionales especificados como un mapa de pares clave-valor (extraClaims). Utiliza la clase Jwts para construir y firmar el token JWT, incluyendo los reclamos adicionales especificados. Devuelve el token JWT generado como una cadena de caracteres.
    public String generateToken(
        Map<String, Object> extraClaims,
        UserDetails userDetails
    ) {
        return Jwts
        .builder() //Este método estático de la clase Jwts crea una instancia del generador de tokens de JWT (JwtBuilder). Se utiliza para construir un nuevo token JWT.
        .setClaims(extraClaims) // Este método establece los datos de las reclamaciones (claims) personalizadas del JWT. Los claims son elementos de información sobre el sujeto del token, como su nombre de usuario, roles, y cualquier otra información relevante. En este caso, se le pasa un mapa de claims personalizados (extraClaims) como parámetro.
        .setSubject(userDetails.getUsername()) //Este método establece el "sujeto" del JWT, que generalmente es el identificador del usuario al que pertenece el token. En este caso, se toma el nombre de usuario (getUsername()) de la instancia de UserDetails proporcionada como parámetro (userDetails).
        .setIssuedAt(new Date(System.currentTimeMillis())) //Este método establece la fecha de emisión del JWT. En este caso, se establece la fecha actual del sistema como la fecha de emisión del token.
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) //Este método establece la fecha de expiración del JWT. En este caso, se establece la fecha y hora actual del sistema más un valor en milisegundos que representa un día (1000 * 60 * 24) como la fecha de expiración del token. Esto significa que el token expirará después de 24 horas desde la emisión.
        .signWith(getSignInKey(), SignatureAlgorithm.HS256) //Este método firma el JWT con una clave secreta y un algoritmo de firma específico. En este caso, se utiliza la clave secreta obtenida mediante el método getSignInKey() y se utiliza el algoritmo de firma HS256 (HMAC SHA-256) para firmar el token.
        .compact(); //Este método finaliza la construcción del token JWT y lo compacta en una cadena de caracteres, que representa el token JWT completo y listo para ser utilizado.
    }

    //Este método verifica si un token JWT dado es válido, es decir, si el nombre de usuario (subject) en el token coincide con el nombre de usuario en los detalles del usuario proporcionados (userDetails) y si el token no ha expirado. Devuelve true si el token es válido y false en caso contrario.
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    //Este método verifica si un token JWT dado ha expirado comparando la fecha de expiración del token con la fecha actual. Devuelve true si el token ha expirado y false en caso contrario.
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    // Este método extrae la fecha de expiración de un token JWT dado utilizando una función de resolución de reclamos (claimsResolver) que se pasa como argumento. Devuelve la fecha de expiración extraída del token.
    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    // Este método extrae todos los reclamos de un token JWT dado utilizando la clase Jwts del paquete io.jsonwebtoken. Devuelve un objeto Claims que contiene todos los reclamos del token.
    private Claims extractAllClaims(String token){
        return Jwts
        .parserBuilder() //Este método estático de la clase Jwts crea una instancia del analizador de tokens JWT (JwtParserBuilder). Se utiliza para configurar y construir un analizador de tokens JWT.
        .setSigningKey(getSignInKey()) //Este método establece la clave de firma utilizada para verificar la firma del token JWT. En este caso, se utiliza la clave secreta obtenida mediante el método getSignInKey() como la clave de firma.
        .build() //Este método finaliza la configuración del analizador de tokens JWT y lo construye en una instancia de JwtParser, que se utiliza para analizar y validar tokens JWT.
        .parseClaimsJws(token) //Este método analiza el token JWT proporcionado (token) y lo convierte en un objeto Jws que contiene la parte de cabecera, la parte de cuerpo (claims), y la firma del token.
        .getBody(); // Este método obtiene el cuerpo (claims) del token JWT analizado como un objeto Claims, que contiene los datos de las reclamaciones (claims) del token.
    }

    //Este método obtiene la clave secreta utilizada para firmar y verificar los tokens JWT. La clave secreta se obtiene a partir de una cadena de caracteres codificada en base64 que se encuentra definida como una constante (SECRET_KEY) en la clase. La clave se decodifica de base64 y se utiliza para crear una clave HMAC (Hash-based Message Authentication Code) utilizando la clase Keys del paquete io.jsonwebtoken. Devuelve la clave HMAC generada.
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
}

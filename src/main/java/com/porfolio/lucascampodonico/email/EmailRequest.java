package com.porfolio.lucascampodonico.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor

public class EmailRequest {
    private String nombre;
    private String email;
    private String mensaje;

    public String getNombre() {
        return nombre;
    }
    public String getEmail() {
        return email;
    }
    public String getMensaje() {
        return mensaje;
    }

}
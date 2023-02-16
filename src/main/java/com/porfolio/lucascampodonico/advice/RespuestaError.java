package com.porfolio.lucascampodonico.advice;

public class RespuestaError {
    private int codigo;
    private String mensaje;
  
    public RespuestaError(int codigo, String mensaje) {
      this.codigo = codigo;
      this.mensaje = mensaje;
    }
  
    public int getCodigo() {
      return codigo;
    }
  
    public String getMensaje() {
      return mensaje;
    }
  }
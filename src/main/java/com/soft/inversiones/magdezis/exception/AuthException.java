package com.soft.inversiones.magdezis.exception;

/** Error de negocio en autenticación o registro (mensaje apto para el usuario). */
public class AuthException extends Exception {
    public AuthException(String message) { super(message); }
    public AuthException(String message, Throwable cause) { super(message, cause); }
}

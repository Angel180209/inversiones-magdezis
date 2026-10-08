package com.soft.inversiones.magdezis.service;

import com.soft.inversiones.magdezis.dto.LoginDTO;
import com.soft.inversiones.magdezis.dto.RegistroDTO;
import com.soft.inversiones.magdezis.exception.AuthException;
import com.soft.inversiones.magdezis.model.Usuario;

public interface AuthService {
    void registrar(RegistroDTO dto) throws AuthException;
    Usuario iniciarSesion(LoginDTO dto) throws AuthException;
}

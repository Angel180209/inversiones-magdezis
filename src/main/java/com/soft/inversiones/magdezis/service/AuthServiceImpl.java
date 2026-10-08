package com.soft.inversiones.magdezis.service;

import com.soft.inversiones.magdezis.dto.LoginDTO;
import com.soft.inversiones.magdezis.dto.RegistroDTO;
import com.soft.inversiones.magdezis.exception.AuthException;
import com.soft.inversiones.magdezis.model.Rol;
import com.soft.inversiones.magdezis.model.Usuario;
import com.soft.inversiones.magdezis.repository.UsuarioRepository;
import com.soft.inversiones.magdezis.repository.UsuarioRepositoryImpl;
import java.sql.SQLException;
import java.util.regex.Pattern;
import org.mindrot.jbcrypt.BCrypt;

public class AuthServiceImpl implements AuthService {

    private static final Pattern EMAIL = Pattern.compile("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$");
    private static final int MIN_PASSWORD = 8;

    private final UsuarioRepository repository;

    public AuthServiceImpl() {
        this(new UsuarioRepositoryImpl());
    }

    public AuthServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void registrar(RegistroDTO dto) throws AuthException {
        validarRegistro(dto);
        String correo = dto.correo().trim().toLowerCase();
        try {
            if (repository.existePorCorreo(correo)) {
                throw new AuthException("Ya existe una cuenta con ese correo.");
            }
            Usuario usuario = new Usuario();
            usuario.setNombre(dto.nombre().trim());
            usuario.setApellido(dto.apellido().trim());
            usuario.setCorreo(correo);
            usuario.setContrasena(BCrypt.hashpw(dto.contrasena(), BCrypt.gensalt(12)));
            usuario.setRol(new Rol(0, Rol.USER)); // todo registro nuevo es USER
            repository.guardar(usuario);
        } catch (SQLException e) {
            throw new AuthException("No se pudo registrar el usuario. Intente más tarde.", e);
        }
    }

    @Override
    public Usuario iniciarSesion(LoginDTO dto) throws AuthException {
        if (esVacio(dto.correo()) || esVacio(dto.contrasena())) {
            throw new AuthException("Ingrese correo y contraseña.");
        }
        try {
            return repository.buscarPorCorreo(dto.correo().trim().toLowerCase())
                    .filter(u -> BCrypt.checkpw(dto.contrasena(), u.getContrasena()))
                    .orElseThrow(() -> new AuthException("Correo o contraseña incorrectos."));
        } catch (SQLException e) {
            throw new AuthException("No se pudo iniciar sesión. Intente más tarde.", e);
        }
    }

    private void validarRegistro(RegistroDTO dto) throws AuthException {
        if (esVacio(dto.nombre()) || esVacio(dto.apellido())
                || esVacio(dto.correo()) || esVacio(dto.contrasena())) {
            throw new AuthException("Todos los campos son obligatorios.");
        }
        if (!EMAIL.matcher(dto.correo().trim()).matches()) {
            throw new AuthException("El correo no tiene un formato válido.");
        }
        if (dto.contrasena().length() < MIN_PASSWORD) {
            throw new AuthException("La contraseña debe tener al menos " + MIN_PASSWORD + " caracteres.");
        }
    }

    private boolean esVacio(String s) {
        return s == null || s.isBlank();
    }
}

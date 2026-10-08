package com.soft.inversiones.magdezis.repository;

import com.soft.inversiones.magdezis.model.Usuario;
import java.sql.SQLException;
import java.util.Optional;

public interface UsuarioRepository {
    Optional<Usuario> buscarPorCorreo(String correo) throws SQLException;
    boolean existePorCorreo(String correo) throws SQLException;
    void guardar(Usuario usuario) throws SQLException;
}

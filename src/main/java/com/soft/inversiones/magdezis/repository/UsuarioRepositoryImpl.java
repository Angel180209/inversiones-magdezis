package com.soft.inversiones.magdezis.repository;

import com.soft.inversiones.magdezis.config.DatabaseConfig;
import com.soft.inversiones.magdezis.model.Rol;
import com.soft.inversiones.magdezis.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    private static final String SELECT_POR_CORREO =
            "SELECT u.id, u.nombre, u.apellido, u.correo, u.contrasena, r.id AS rol_id, r.nombre AS rol_nombre "
            + "FROM usuarios u INNER JOIN roles r ON r.id = u.rol_id WHERE u.correo = ?";

    private static final String EXISTE_CORREO = "SELECT 1 FROM usuarios WHERE correo = ?";

    private static final String INSERT =
            "INSERT INTO usuarios (nombre, apellido, correo, contrasena, rol_id) "
            + "VALUES (?, ?, ?, ?, (SELECT id FROM roles WHERE nombre = ?))";

    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) throws SQLException {
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_POR_CORREO)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapear(rs)) : Optional.empty();
            }
        }
    }

    @Override
    public boolean existePorCorreo(String correo) throws SQLException {
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(EXISTE_CORREO)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
    public void guardar(Usuario usuario) throws SQLException {
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getContrasena());
            ps.setString(5, usuario.getRol().getNombre());
            ps.executeUpdate();
        }
    }

    private Usuario mapear(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId(rs.getInt("id"));
        u.setNombre(rs.getString("nombre"));
        u.setApellido(rs.getString("apellido"));
        u.setCorreo(rs.getString("correo"));
        u.setContrasena(rs.getString("contrasena"));
        u.setRol(new Rol(rs.getInt("rol_id"), rs.getString("rol_nombre")));
        return u;
    }
}

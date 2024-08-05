package repositories;

import entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository {
    Optional<Usuario> findByNomeUsuario(String nomeUsuario);
}

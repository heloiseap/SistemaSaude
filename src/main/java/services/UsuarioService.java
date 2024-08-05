package services;

import dtos.LoginRequest;
import entities.Perfil;
import entities.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import repositories.UsuarioRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final PerfilService perfilService;

    public Usuario validaUsuario(
            LoginRequest loginRequest
    ){
        Usuario usuarioEntity = usuarioRepository
                .findByNomeUsuario(loginRequest.username())
                .orElseThrow(
                        ()->new RuntimeException("Usuário não existe com o nome "
                                +loginRequest.username())
                );
        if(!passwordEncoder.matches(loginRequest.password(),usuarioEntity.getPassword())){
            throw new RuntimeException("Senha errada para usuario com nome "
                    + loginRequest.username());
        }
        return usuarioEntity;
    }

    public void cadastraUsuario(LoginRequest cadastroRequest) {
        if (
                usuarioRepository.findByNomeUsuario(cadastroRequest.username())
                        .isPresent()){
            throw new RuntimeException("Usuário existe com nome: "+cadastroRequest.username());
        }
        Perfil perfil = perfilService.validaPerfil(cadastroRequest.nomePerfil());
        Usuario usuario = new Usuario();
        usuario.setUsername(cadastroRequest.username());
        usuario.setPassword(passwordEncoder.encode(cadastroRequest.password()));
        usuario.setPerfilList(Set.of(perfil));

        usuarioRepository.save(usuario);
    }

}

package controllers;

import dtos.LoginRequest;
import dtos.PerfilRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import services.PerfilService;
import services.UsuarioService;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PerfilService perfilService;

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastraUsuario(
            @RequestBody LoginRequest cadastroRequest
    ) {
        usuarioService.cadastraUsuario(cadastroRequest);

        return new ResponseEntity<>("Usuario Criado", HttpStatus.CREATED);
    }

}
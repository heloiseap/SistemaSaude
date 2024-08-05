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
public class PerfilController {

    private final PerfilService perfilService;

    @PostMapping("/perfil")
    public ResponseEntity<String> cadastraPerfil(
            @RequestBody PerfilRequest perfilRequest
    ){
        perfilService.cadastraPerfil(perfilRequest);
        return new ResponseEntity<>("Perfil criado",HttpStatus.CREATED);
    }
}
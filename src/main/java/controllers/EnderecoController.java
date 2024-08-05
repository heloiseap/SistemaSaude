package controllers;


import dtos.EnderecoRequest;
import dtos.EnderecoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.EnderecoService;

import java.util.List;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoController {
    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping()
    public EnderecoResponse salvarEndereco(@RequestBody EnderecoRequest request) {
        return enderecoService.salvarEndereco(request);
    }

    @GetMapping()
    public List<EnderecoResponse> listarEnderecos() {
        var enderecos = enderecoService.listarEnderecos();
        if (enderecos.isEmpty()){
            return null;
        } else {
            return enderecos;
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponse> search(@PathVariable long id) {
        EnderecoResponse response = enderecoService.buscarEndereco(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            throw new RuntimeException("404");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable long id) {
        enderecoService.removerEndereco(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponse> update(@PathVariable long id, @RequestBody EnderecoRequest request) {
        EnderecoResponse endereco = enderecoService.atualizarEndereco(id, request);
        if (endereco != null) {
            return ResponseEntity.ok(endereco);
        } else {
            throw new RuntimeException("404");
        }
    }
}

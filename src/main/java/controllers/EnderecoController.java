package controllers;

import dtos.EnderecoRequest;
import dtos.EnderecoResponse;
import dtos.PacienteRequest;
import dtos.PacienteResponse;
import entities.Endereco;
import entities.Paciente;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import services.EnderecoService;
import services.PacienteService;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService service;

    public EnderecoController(EnderecoService enderecoService) {this.service = enderecoService;}

    @PostMapping("/cadastrados")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastraEndeco(EnderecoRequest enderecoRequest){
        service.salvarEndereco(enderecoRequest);
    }

    @PutMapping("/alterar")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void alterarEndereco(EnderecoRequest enderecoRequest){
        service.alterarEndereco(enderecoRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluiEndereco(Endereco endereco) {
        service.apagarEndereco(endereco);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EnderecoResponse> listaEndereco(){
        return service.listarEnderecos();
    }
}

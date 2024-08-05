package controllers;

import dtos.ConsultaRequest;
import dtos.ConsultaResponse;

import entities.Consulta;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import services.ConsultaService;


import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    private final ConsultaService service;

    public ConsultaController(ConsultaService consultaService) {this.service = consultaService;}

    @PostMapping("/cadastros")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastraConsulta(ConsultaRequest consultaRequest){
        service.salvarConsulta(consultaRequest);
    }

    @PutMapping("/alterar")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void alteraConsulta(ConsultaRequest consultaRequest){
        service.alterarConsulta(consultaRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluiConsulta(Consulta consulta) {
        service.apagarConsulta(consulta);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ConsultaResponse> listaConsulta(){
        return service.listarConsultas();
    }
}

package controllers;

import dtos.ConsultaRequest;
import dtos.ConsultaResponse;

import entities.Consulta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.ConsultaService;


import java.util.List;

@RestController
@RequestMapping(value = "/consultas")
public class ConsultaController {
    private final ConsultaService service;

    public ConsultaController(ConsultaService consultaService) {this.service = consultaService;}

    @PostMapping("/cadastros")
    @ResponseStatus(HttpStatus.CREATED)
    public ConsultaResponse salvarConsulta(@RequestBody ConsultaRequest request) {
        return service.salvarConsulta(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponse> update(@PathVariable long id, @RequestBody ConsultaRequest request) {
        ConsultaResponse consulta = service.atualizarConsulta(id, request);
        if (consulta != null) {
            return ResponseEntity.ok(consulta);
        } else {
            throw new RuntimeException("404");
        }
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

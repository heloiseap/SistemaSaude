package controllers;

import dtos.PacienteRequest;
import dtos.PacienteResponse;
import entities.Paciente;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import services.PacienteService;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService pacienteService) {this.service = pacienteService;}

    @PostMapping("/cadastrados")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastraPaciente(PacienteRequest pacienteRequest){
        service.salvarPaciente(pacienteRequest);
    }

    @PutMapping("/alterar")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void alteraPaciente(PacienteRequest pacienteRequest){
        service.alterarPaciente(pacienteRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluiPaciente(Paciente paciente) {
        service.apagarPaciente(paciente);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
        public List<PacienteResponse> listaPacientes(){
            return service.listarPacientes();
        }
}


package controllers;

import dtos.PacienteRequest;
import dtos.PacienteResponse;
import entities.Paciente;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.PacienteService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping()
    public PacienteResponse salvarPaciente(@RequestBody PacienteRequest request,
                                              //@RequestParam("date")
                                              @DateTimeFormat(pattern = "dd/MM/yyyy") Date date) {
        return pacienteService.salvarPaciente(request);

//        try {{
//        catch (DateTimeParseException exception) {
//            return ResponseEntity.badRequest().body("Invalid date format. Please use 'dd/MM/yyyy'.");
//        }

    }

    @GetMapping()
    public List<PacienteResponse> listarPacientes() {
        var Pacientes = pacienteService.listarPacientes();
        if (Pacientes.isEmpty()){
            return null;
        } else {
            return Pacientes;
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> search(@PathVariable long id) {
        PacienteResponse response = pacienteService.buscarPaciente(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            throw new RuntimeException("404");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable long id) {
        pacienteService.removerPaciente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> update(@PathVariable long id,
                                                      @RequestBody PacienteRequest request,
                                                      //@RequestParam("date")
                                                      @DateTimeFormat(pattern = "dd/MM/yyyy") Date date) {
        PacienteResponse paciente = pacienteService.atualizarPaciente(id, request);
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        } else {
            throw new RuntimeException("404");
        }
    }
}


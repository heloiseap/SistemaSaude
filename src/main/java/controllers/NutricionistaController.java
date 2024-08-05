package controllers;

import dtos.NutricionistaRequest;
import dtos.NutricionistaResponse;
import entities.Nutricionista;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.NutricionistaService;

import java.util.List;

@RestController
@RequestMapping("/nutri")
public class NutricionistaController {

    private final NutricionistaService service;

    public NutricionistaController(NutricionistaService nutriService) {this.service = nutriService;}


    @PostMapping()
    public NutricionistaResponse salvarNutricionista(@RequestBody NutricionistaRequest request) {
        return service.salvarNutricionista(request);
    }

    @GetMapping()
    public List<NutricionistaResponse> listarnutricionistas() {
        var nutricionistas = service.listarNutricionistas();
        if (nutricionistas.isEmpty()){
            return null;
        } else {
            return nutricionistas;
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<NutricionistaResponse> search(@PathVariable long id) {
        NutricionistaResponse response = service.buscarNutricionista(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            throw new RuntimeException("404");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable long id) {
        service.removerNutricionista(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<NutricionistaResponse> update(@PathVariable long id, @RequestBody NutricionistaRequest request) {
        NutricionistaResponse nutricionista = service.atualizarNutricionista(id, request);
        if (nutricionista != null) {
            return ResponseEntity.ok(nutricionista);
        } else {
            throw new RuntimeException("404");
        }
    }}

package controllers;

import dtos.NutricionistaRequest;
import dtos.NutricionistaResponse;
import entities.Nutricionista;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import services.NutricionistaService;

import java.util.List;

@RestController
@RequestMapping("/nutri")
public class NutricionistaController {

    private final NutricionistaService service;

    public NutricionistaController(NutricionistaService nutriService) {this.service = nutriService;}


    @PostMapping("/cadastrados")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastraNutri(NutricionistaRequest nutriRequest){
        service.salvarNutricionista(nutriRequest);
    }

    @PutMapping("/alterar")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void alteraNutri(NutricionistaRequest nutriRequest){
        service.alterarNutricionista(nutriRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluiNutri(Nutricionista nutri) {
        service.apagarNutricionista(nutri);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<NutricionistaResponse> listaNutri(){
        return service.listarNutricionistas();
    }
}

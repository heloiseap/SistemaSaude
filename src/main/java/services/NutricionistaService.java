package services;

import dtos.NutricionistaRequest;
import dtos.NutricionistaResponse;
import entities.Nutricionista;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import repositories.NutricionistaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NutricionistaService {

    private final NutricionistaRepository repository;

    //create
    public NutricionistaResponse salvarNutricionista(NutricionistaRequest request) {
        if (repository.findByCrn(request.getCrn()).isEmpty()){
            Nutricionista entity = new Nutricionista();
            entity.setCrn(request.getCrn());
            entity.setEspecialidade(request.getEspecialidade());
            entity.setMatricula(request.getMatricula());
            entity.setEndereco(request.getEndereco());
            entity.setTempoExperiencia(request.getTempoExperiencia());
            repository.save(entity);

            NutricionistaResponse response = new NutricionistaResponse();
            response.setCrn(request.getCrn());
            response.setEspecialidade(request.getEspecialidade());
            response.setTempoExperiencia(request.getTempoExperiencia());
            response.setMatricula(request.getMatricula());
            return response;

        }
        else {
            throw new DuplicateKeyException(
              "Nutricionista já no cadastro."
            );

        }
    }


    //read
    public Optional<Nutricionista> buscarNutricionistaPorEspecialidade(String especialidade) {
        return repository.findAllByEspecialidade(especialidade);
    }
    //todo: mapeamento
    //pesquisar parcialmente

    public Optional<Nutricionista> buscarNutricionistaPorCrn(String crn) {
        return repository.findByCrn(crn);
    }
    //todo: mapeamento
    //pesquisar parcialmente

    public List<NutricionistaResponse> listarNutricionistas() {
        return repository.findAll().stream().map(
                nutricionistaEntity -> new NutricionistaResponse(
                        nutricionistaEntity.getCrn(),
                        nutricionistaEntity.getEspecialidade(),
                        nutricionistaEntity.getMatricula(),
                        nutricionistaEntity.getTempoExperiencia()
                )
        ).collect(Collectors.toList());
    }


    //update

    //todo: conseguir id por mapeamento do request

    public NutricionistaResponse alterarNutricionista(NutricionistaRequest request) {
        Nutricionista entity = new Nutricionista();
        entity.setEndereco(request.getEndereco());
        entity.setCrn(request.getCrn());
        entity.setEspecialidade(request.getEspecialidade());
        entity.setTempoExperiencia(request.getTempoExperiencia());
        entity.setMatricula(request.getMatricula());
        repository.save(entity);

        NutricionistaResponse response = new NutricionistaResponse();
        response.setCrn(request.getCrn());
        response.setEspecialidade(request.getEspecialidade());
        response.setTempoExperiencia(request.getTempoExperiencia());
        response.setMatricula(request.getMatricula());
        return response;
    }

    //delete
    public void apagarNutricionista(Nutricionista nutricionista) {
        repository.delete(nutricionista);
    }

    //metodos
    public void adicionarUmAnoExperiencia(Nutricionista nutricionista){
        nutricionista.setTempoExperiencia(nutricionista.getTempoExperiencia() + 1);
    }
    public void adicionarEspecialidade(Nutricionista nutricionista, String especialidade) {
        nutricionista.setEspecialidade(nutricionista.getEspecialidade() + ",\n" + especialidade);
    }


}

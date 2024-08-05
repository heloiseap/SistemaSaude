package services;

import dtos.NutricionistaRequest;
import dtos.NutricionistaResponse;
import entities.Nutricionista;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import repositories.EnderecoRepository;
import repositories.NutricionistaRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NutricionistaService {

    private final NutricionistaRepository nutricionistaRepository;
    private final EnderecoRepository enderecoRepository;


    public NutricionistaService(NutricionistaRepository nutricionistaRepository, EnderecoRepository enderecoRepository) {
        this.nutricionistaRepository = nutricionistaRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public List<NutricionistaResponse> listarNutricionistas() {
        return nutricionistaRepository.findAll().stream().map(
                nutricionista -> new NutricionistaResponse(
                        nutricionista.getId(),
                        nutricionista.getMatricula(),
                        nutricionista.getTempoExperiencia(),
                        nutricionista.getEndereco(),
                        nutricionista.getCrn(),
                        nutricionista.getEspecialidade()
                )
        ).collect(Collectors.toList());
    }

    public NutricionistaResponse buscarNutricionista(Long id){
        Nutricionista nutricionista = nutricionistaRepository.findById(id).orElse(null);
        if (nutricionista != null) {
            return new NutricionistaResponse(
                    nutricionista.getId(),
                    nutricionista.getMatricula(),
                    nutricionista.getTempoExperiencia(),
                    nutricionista.getEndereco(),
                    nutricionista.getCrn(),
                    nutricionista.getEspecialidade()
            );
        }
        return null;
    }

    public NutricionistaResponse salvarNutricionista(NutricionistaRequest request) {
        if (nutricionistaRepository.findByCrn(request.getCrn()).isPresent()) {
            throw new RuntimeException("Já existe cadastro de nutricionista com este crn.");
        }
        Nutricionista nutricionista = mapearRequest(request);
        Nutricionista entitySalva = nutricionistaRepository.save(nutricionista);

        return new NutricionistaResponse(
                entitySalva.getId(),
                entitySalva.getMatricula(),
                entitySalva.getTempoExperiencia(),
                entitySalva.getEndereco(),
                entitySalva.getCrn(),
                entitySalva.getEspecialidade()
        );
    }

    private Nutricionista mapearRequest(NutricionistaRequest source){
        Nutricionista target = new Nutricionista();
        target.setMatricula(source.getMatricula());
        target.setTempoExperiencia(source.getTempoExperiencia());
        target.setCrn(source.getCrn());
        target.setEspecialidade(source.getEspecialidade());
        target.setEndereco(enderecoRepository.findById(source.getEndereco().getId()).orElse(null));
        return target;
    }

    public NutricionistaResponse atualizarNutricionista(Long id, NutricionistaRequest request) {
        Nutricionista nutricionista = nutricionistaRepository.findById(id).orElse(null);

        assert nutricionista != null;
        if (!Objects.equals(nutricionista.getCrn(), request.getCrn()) &&
                nutricionistaRepository.findByCrn(request.getCrn()).isPresent()) {
            throw new RuntimeException("Já existe cadastro de nutricionista com este crn.");
        }

        nutricionista.setMatricula(request.getMatricula());
        nutricionista.setTempoExperiencia(request.getTempoExperiencia());
        nutricionista.setCrn(request.getCrn());
        nutricionista.setEspecialidade(request.getEspecialidade());
        nutricionista.setEndereco(enderecoRepository.findById(request.getEndereco().getId()).orElse(null));

        nutricionistaRepository.save(nutricionista);
        return new NutricionistaResponse(nutricionista.getId(),
                nutricionista.getMatricula(),
                nutricionista.getTempoExperiencia(),
                nutricionista.getEndereco(),
                nutricionista.getCrn(),
                nutricionista.getEspecialidade()
        );
    }

    public void removerNutricionista(Long id) {
        nutricionistaRepository.deleteById(id);
    }

    public void adicionarAnoExperiencia(Long id) {
        Nutricionista nutricionista = nutricionistaRepository.findById(id).orElse(null);
        assert nutricionista != null;
        nutricionista.setTempoExperiencia(nutricionista.getTempoExperiencia() + 1);
    }

    public void adicionarEspecialidade(Nutricionista nutricionista, String especialidade) {
        nutricionista.setEspecialidade(nutricionista.getEspecialidade() + ",\n" + especialidade);
    }

}

package services;

import dtos.PacienteRequest;
import dtos.PacienteResponse;
import entities.Paciente;
import lombok.AllArgsConstructor;
import mappers.PacienteMapper;
import org.springframework.stereotype.Service;
import repositories.PacienteRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PacienteService {

    private final PacienteRepository repository;

    //create
    public PacienteResponse salvarPaciente(PacienteRequest request) {
        Paciente entity = new Paciente();
        entity.setNome(request.getNome());
        entity.setEmail(request.getEmail());
        entity.setCpf(request.getCpf());
        entity.setEndereco(request.getEndereco());
        entity.setTelefone(request.getTelefone());
        entity.setDataNascimento(request.getDataNascimento());
        repository.save(entity);

        PacienteResponse response = new PacienteResponse();
        response.setNome(request.getNome());
        response.setTelefone(request.getTelefone());
        response.setEmail(request.getEmail());
        return response;
    }


    //read
    public Optional<PacienteResponse> buscarPacientePorCpf(String cpf) {
        return Optional.ofNullable(PacienteMapper.map(repository.findByCpf(cpf)));
    }

    public Optional<Paciente> buscarPacientePorNome(String nome) {
        return Optional.ofNullable(repository.findByNome(nome));
    }
    //pesquisar parcialmente

    public List<PacienteResponse> listarPacientes() {
        return repository.findAll().stream().map(
                pacienteEntity -> new PacienteResponse(
                        pacienteEntity.getNome(),
                        pacienteEntity.getTelefone(),
                        pacienteEntity.getEmail()
                )
        ).collect(Collectors.toList());
    }


    //update

    //todo: conseguir id por mapeamento do request

    public PacienteResponse alterarPaciente(PacienteRequest request) {
        Paciente entity = new Paciente();
        entity.setEndereco(request.getEndereco());
        entity.setNome(request.getNome());
        entity.setTelefone(request.getTelefone());
        entity.setEmail(request.getEmail());
        entity.setDataNascimento(request.getDataNascimento());
        repository.save(entity);

        PacienteResponse response = new PacienteResponse();
        response.setNome(request.getNome());
        response.setTelefone(request.getTelefone());
        response.setEmail(request.getEmail());
        return response;
    }

    //delete
    public void apagarPaciente(Paciente paciente) {
        repository.delete(paciente);
    }
}

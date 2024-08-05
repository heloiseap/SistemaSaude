package services;

import dtos.PacienteRequest;
import dtos.PacienteResponse;
import entities.Paciente;
import lombok.AllArgsConstructor;
import mappers.PacienteMapper;
import org.springframework.stereotype.Service;
import repositories.EnderecoRepository;
import repositories.PacienteRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService {


    private final PacienteRepository pacienteRepository;
    private final EnderecoRepository enderecoRepository;


    public PacienteService(PacienteRepository pacienteRepository, EnderecoRepository enderecoRepository) {
        this.pacienteRepository = pacienteRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public List<PacienteResponse> listarPacientes() {
        return pacienteRepository.findAll().stream().map(
                paciente -> new PacienteResponse(
                        paciente.getId(),
                        paciente.getNome(),
                        paciente.getDataNascimento(),
                        paciente.getCpf(),
                        paciente.getTelefone(),
                        paciente.getEmail(),
                        paciente.getEndereco()
                )
        ).collect(Collectors.toList());
    }

    public PacienteResponse buscarPaciente(Long id){
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        if (paciente != null) {
            return new PacienteResponse(
                    paciente.getId(),
                    paciente.getNome(),
                    paciente.getDataNascimento(),
                    paciente.getCpf(),
                    paciente.getTelefone(),
                    paciente.getEmail(),
                    paciente.getEndereco()
            );
        }
        return null;
    }

    public PacienteResponse salvarPaciente(PacienteRequest request) {
        Paciente paciente = mapearRequest(request);
        Paciente entitySalva = pacienteRepository.save(paciente);

        return new PacienteResponse(entitySalva.getId(),
                entitySalva.getNome(),
                entitySalva.getDataNascimento(),
                entitySalva.getCpf(),
                entitySalva.getTelefone(),
                entitySalva.getEmail(),
                entitySalva.getEndereco()
        );
    }

    private Paciente mapearRequest(PacienteRequest source){
        Paciente target = new Paciente();
        target.setNome(source.getNome());
        target.setDataNascimento(source.getDataNascimento());
        target.setCpf(source.getCpf());
        target.setTelefone(source.getTelefone());
        target.setEmail(source.getEmail());
        target.setEndereco(enderecoRepository.findById(source.getEndereco().getId()).orElse(null));
        return target;
    }

    public PacienteResponse atualizarPaciente(Long id, PacienteRequest request) {
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        assert paciente != null;
        paciente.setNome(request.getNome());
        paciente.setDataNascimento(request.getDataNascimento());
        paciente.setCpf(request.getCpf());
        paciente.setTelefone(request.getTelefone());
        paciente.setEmail(request.getEmail());
        paciente.setEndereco(enderecoRepository.findById(request.getEndereco().getId()).orElse(null));

        pacienteRepository.save(paciente);

        return new PacienteResponse(paciente.getId(),
                paciente.getNome(),
                paciente.getDataNascimento(),
                paciente.getCpf(),
                paciente.getTelefone(),
                paciente.getEmail(),
                paciente.getEndereco()
        );
    }

    public void removerPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

}

package services;

import dtos.EnderecoRequest;
import dtos.EnderecoResponse;
import entities.Endereco;
import entities.Paciente;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repositories.EnderecoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<EnderecoResponse> listarEnderecos() {
        return enderecoRepository.findAll().stream().map(
                endereco -> new EnderecoResponse(
                        endereco.getId(),
                        endereco.getLogradouro(),
                        endereco.getEstado(),
                        endereco.getCidade(),
                        endereco.getNumero(),
                        endereco.getCep()
                )
        ).collect(Collectors.toList());
    }

    public EnderecoResponse buscarEndereco(Long id){
        Endereco endereco = enderecoRepository.findById(id).orElse(null);
        if (endereco != null) {
            return new EnderecoResponse(
                    endereco.getId(),
                    endereco.getLogradouro(),
                    endereco.getEstado(),
                    endereco.getCidade(),
                    endereco.getNumero(),
                    endereco.getCep()
            );
        }
        return null;
    }

    public EnderecoResponse salvarEndereco(EnderecoRequest request) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(request.getLogradouro());
        endereco.setNumero(request.getNumero());
        endereco.setCidade(request.getCidade());
        endereco.setEstado(request.getEstado());
        endereco.setCep(request.getCep());

        Endereco entitySalva = enderecoRepository.save(endereco);

        return new EnderecoResponse(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getEstado(),
                endereco.getCidade(),
                endereco.getNumero(),
                endereco.getCep()
        );
    }

    public EnderecoResponse atualizarEndereco(Long id, EnderecoRequest request) {
        Endereco endereco = enderecoRepository.findById(id).orElse(null);
        endereco.setLogradouro(request.getLogradouro());
        endereco.setNumero(request.getNumero());
        endereco.setCidade(request.getCidade());
        endereco.setEstado(request.getEstado());
        endereco.setCep(request.getCep());

        enderecoRepository.save(endereco);

        return new EnderecoResponse(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getEstado(),
                endereco.getCidade(),
                endereco.getNumero(),
                endereco.getCep()
        );
    }

    public void removerEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }

}

package services;

import dtos.EnderecoRequest;
import dtos.EnderecoResponse;
import entities.Endereco;
import entities.Paciente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repositories.EnderecoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository repository;
    //create
    public EnderecoResponse salvarEndereco(EnderecoRequest request){
        Endereco entity = new Endereco();
        entity.setCep(request.getCep());
        entity.setCidade(request.getCidade());
        entity.setEstado(request.getEstado());
        entity.setNumero(request.getNumero());
        repository.save(entity);

        EnderecoResponse response = new EnderecoResponse();
        response.setCep(request.getCep());
        response.setNumero(request.getNumero());

        return response;
    }

    //read

    public Optional<EnderecoResponse> buscarPorCidade(String cidade){
        return repository.findAllByCidade(cidade);
    }

    public Optional<EnderecoResponse> buscarPorEstado(String estado){
        return repository.findAllByEstado(estado);
    }

    public Optional<EnderecoResponse> buscarPorPaciente(Paciente paciente){
        return repository.findByPaciente(paciente);
    }

    public List<EnderecoResponse> listarEnderecos(){
        return repository.findAll().stream().map(
                enderecoEntity -> new EnderecoResponse(
                        enderecoEntity.getCep(),
                        enderecoEntity.getNumero()

                )
        ).collect(Collectors.toList());
    }

    //update

    public EnderecoResponse alterarEndereco(EnderecoRequest request){
        Endereco entity = new Endereco();
        entity.setNumero(request.getNumero());
        entity.setCep(request.getCep());
        entity.setEstado(request.getEstado());
        entity.setCidade(request.getEstado());
        entity.setLogradouro(request.getLogradouro());

        EnderecoResponse response = new EnderecoResponse();
        response.setCep(request.getCep());
        response.setNumero(request.getNumero());
        return response;
    }
    //todo: conseguir id por mapeamento do request
    //delete

    public void apagarEndereco(Endereco endereco){
        repository.delete(endereco);
    }
}

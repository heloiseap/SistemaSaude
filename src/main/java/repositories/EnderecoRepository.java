package repositories;

import dtos.EnderecoResponse;
import entities.Endereco;
import entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Optional<EnderecoResponse> findAllByCidade(String cidade);

    Optional<EnderecoResponse> findAllByEstado(String estado);

    Optional<EnderecoResponse> findByPaciente(Paciente paciente);
}

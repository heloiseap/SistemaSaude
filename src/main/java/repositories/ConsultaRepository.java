package repositories;

import entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta,Long> {
    Optional<Consulta> findAllByNutricionista(String nutricionista);

    Optional<Consulta> findAllByPaciente(String paciente);

    Optional<Consulta> findAllByData(Date data);
}

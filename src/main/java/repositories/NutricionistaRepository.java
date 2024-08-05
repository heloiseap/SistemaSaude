package repositories;

import entities.Nutricionista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NutricionistaRepository extends JpaRepository<Nutricionista, Long> {
    Optional<Nutricionista> findAllByEspecialidade(String especialidade);

    Optional<Nutricionista> findByCrn(String crn);
}

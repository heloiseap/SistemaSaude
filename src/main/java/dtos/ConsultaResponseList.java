package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class ConsultaResponseList {
    private Long id;
    private String nomeNutricionista;
    private String nomePaciente;
    private LocalDate data;
}

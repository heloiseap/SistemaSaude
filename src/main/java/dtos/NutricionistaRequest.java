package dtos;

import entities.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NutricionistaRequest {
    private String crn;
    private String especialidade;
    private String matricula;
    private Integer tempoExperiencia;
    private Endereco endereco;

}

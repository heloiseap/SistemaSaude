package dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class NutricionistaResponse {
    private String crn;
    private String especialidade;
    private String matricula;
    private Integer tempoExperiencia;
    //private Endereco endereco;

    public NutricionistaResponse(String crn, String especialidade, String matricula, Integer tempoExperiencia) {
    }
}

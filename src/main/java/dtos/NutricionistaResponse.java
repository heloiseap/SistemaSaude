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
public class NutricionistaResponse {
    private Long id;
    private String matricula;
    private int tempoExperiencia;
    private Endereco endereco;
    private String crn;
    private String especialidade;

    public NutricionistaResponse() {
    }

    public NutricionistaResponse(Long id, String matricula, int tempoExperiencia, Endereco endereco, String crn, String especialidade) {
        this.id = id;
        this.matricula = matricula;
        this.tempoExperiencia = tempoExperiencia;
        this.endereco = endereco;
        this.crn = crn;
        this.especialidade = especialidade;
    }
}

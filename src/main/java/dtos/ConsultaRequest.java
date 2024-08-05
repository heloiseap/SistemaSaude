package dtos;

import entities.Nutricionista;
import entities.Paciente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class ConsultaRequest {
    private Date dataConsulta;
    private Paciente paciente;
    private Nutricionista nutricionista;
    private String observacoes;
}

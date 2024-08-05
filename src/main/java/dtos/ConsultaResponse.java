package dtos;

import entities.Nutricionista;
import entities.Paciente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ConsultaResponse {
    private Date dataConsulta;
    private Paciente paciente;
    private Nutricionista nutricionista;
    //private String observacoes;
}

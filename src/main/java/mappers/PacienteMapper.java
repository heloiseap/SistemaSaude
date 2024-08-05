package mappers;

import dtos.PacienteRequest;
import dtos.PacienteResponse;
import entities.Paciente;

public class PacienteMapper {

    public static PacienteResponse map(Paciente source) {
        PacienteResponse target = new PacienteResponse();

        target.setNome(source.getNome());
        target.setTelefone(source.getTelefone());
        target.setEmail(source.getEmail());

        return target;
    }

    public static PacienteResponse map(PacienteRequest source) {
        PacienteResponse target = new PacienteResponse();

        target.setNome(source.getNome());
        target.setTelefone(source.getTelefone());
        target.setEmail(source.getEmail());

        return target;
    }
}

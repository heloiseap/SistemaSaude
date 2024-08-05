package dtos;

import entities.Endereco;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor

public class PacienteRequest {
    private String nome;
    private Date dataNascimento;
    private String cpf;
    private String telefone;
    private String email;
    private Endereco endereco;
}

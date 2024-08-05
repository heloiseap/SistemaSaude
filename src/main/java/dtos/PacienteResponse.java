package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PacienteResponse {
    private String nome;
    //private Date dataNascimento;
    //private String cpf;
    private String telefone;
    private String email;
    //private Endereco endereco;

}

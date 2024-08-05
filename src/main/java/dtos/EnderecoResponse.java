package dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoResponse {
    //private String logradouro;
    //private String cidade;
    //private String estado;
    private String cep;
    private Integer numero;
}

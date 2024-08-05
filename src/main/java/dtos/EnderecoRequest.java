package dtos;

import lombok.Data;

@Data
public class EnderecoRequest {
    private String logradouro;
    private String cidade;
    private String estado;
    private String cep;
    private Integer numero;
}

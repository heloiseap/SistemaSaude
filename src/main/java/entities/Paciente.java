package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Paciente")
@Getter
@Setter
@NoArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
    private Date dataNascimento;
    private String cpf;
    @Column(nullable = false)
    private String telefone;
    @Column(nullable = false)
    private String email;

    @OneToOne
    private Endereco endereco;
}

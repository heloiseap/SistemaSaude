package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Nutricionista")
@Getter
@Setter
@NoArgsConstructor
public class Nutricionista extends Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String crn;
    @Column(nullable = false)
    private String especialidade;
}

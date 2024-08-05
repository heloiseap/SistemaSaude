package entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@Entity
@Table(name="perfil")
public class Perfil implements GrantedAuthority {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="perfil_id") //?!
    private long id;

    private String nomePerfil;

    @Override
    public String getAuthority(){
        return this.nomePerfil;
    }

}

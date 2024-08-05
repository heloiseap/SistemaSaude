package services;

import dtos.PerfilRequest;
import entities.Perfil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repositories.PerfilRepository;

@Service
@RequiredArgsConstructor
public class PerfilService {
    private final PerfilRepository repository;

    public void cadastraPerfil(PerfilRequest perfilRequest) {
        if (repository.findByNomePerfil(perfilRequest.nomePerfil()).isPresent()) {
            throw
                    new RuntimeException("Perfil já existe com o nome :" + perfilRequest.nomePerfil());
        }
        Perfil perfil = new Perfil();
        perfil.setNomePerfil(perfilRequest.nomePerfil());
        repository.save(perfil);
    }

    public Perfil validaPerfil(String nomePerfil){
        Perfil perfil = repository.findByNomePerfil(nomePerfil)
                .orElseThrow(
                        () -> new RuntimeException("Perfil não existe com nome : " + nomePerfil)
                );
        return perfil;
    }

}

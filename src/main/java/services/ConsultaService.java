package services;

import dtos.ConsultaRequest;
import entities.Consulta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repositories.ConsultaRepository;
import dtos.ConsultaResponse;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultaService {
    private final ConsultaRepository repository;

    //create
    public ConsultaResponse salvarConsulta(ConsultaRequest request) {
        Consulta entity = new Consulta();
        entity.setDataConsulta(request.getDataConsulta());
        entity.setObservacoes(request.getObservacoes());
        entity.setNutricionista(request.getNutricionista());
        entity.setPaciente(request.getPaciente());
        entity.setObservacoes(request.getObservacoes());
        repository.save(entity);

        ConsultaResponse response = new ConsultaResponse();
        response.setDataConsulta(request.getDataConsulta());
        response.setNutricionista(request.getNutricionista());
        response.setPaciente(request.getPaciente());
        return response;
    }


    //read
    public List<ConsultaResponse> buscarConsultaPorNutri(String nutricionista) {
        return repository.findAllByNutricionista(nutricionista).stream().map(
                consultaEntity -> new ConsultaResponse(
                        consultaEntity.getDataConsulta(),
                        consultaEntity.getPaciente(),
                        consultaEntity.getNutricionista()

                )
        ).collect(Collectors.toList());
    }

    //pesquisar parcialmente

    public List<ConsultaResponse> buscarConsultaPorPaciente(String paciente) {
        return repository.findAllByPaciente(paciente).stream().map(
                consultaEntity -> new ConsultaResponse(
                        consultaEntity.getDataConsulta(),
                        consultaEntity.getPaciente(),
                        consultaEntity.getNutricionista()
                )
        ).collect(Collectors.toList());
    }

    //pesquisar parcialmente

    public List<ConsultaResponse> buscarConsultaPorData(Date data) {
        return repository.findAllByData(data).stream().map(
                consultaEntity -> new ConsultaResponse(
                        consultaEntity.getDataConsulta(),
                        consultaEntity.getPaciente(),
                        consultaEntity.getNutricionista()
                )
        ).collect(Collectors.toList());
    }

    //pesquisar parcialmente

    public List<ConsultaResponse> listarConsultas() {
        return repository.findAll().stream().map(
                consultaEntity -> new ConsultaResponse(
                        consultaEntity.getDataConsulta(),
                        consultaEntity.getPaciente(),
                        consultaEntity.getNutricionista()
                )
        ).collect(Collectors.toList());
    }


    //update

    //todo: conseguir id por mapeamento do request

    public ConsultaResponse alterarConsulta(ConsultaRequest request) {
        Consulta entity = new Consulta();
        entity.setDataConsulta(request.getDataConsulta());
        entity.setObservacoes(request.getObservacoes());
        entity.setNutricionista(request.getNutricionista());
        entity.setPaciente(request.getPaciente());
        entity.setObservacoes(request.getObservacoes());
        repository.save(entity);

        ConsultaResponse response = new ConsultaResponse();
        response.setDataConsulta(request.getDataConsulta());
        response.setNutricionista(request.getNutricionista());
        response.setPaciente(request.getPaciente());
        return response;
    }

    //delete
    public void apagarConsulta(Consulta consulta) {
        repository.delete(consulta);

    }

}

package senai.clinica_api.services;

import org.springframework.stereotype.Service;
import senai.clinica_api.dtos.ConsultaDto;
import senai.clinica_api.entities.ConsultaEntity;
import senai.clinica_api.repositories.ConsultaRepository;
import senai.clinica_api.repositories.PacienteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultaService {

    private final ConsultaRepository repository;

    public ConsultaService(ConsultaRepository repository) {
        this.repository = repository;
    }

    public List<ConsultaDto> obterConsultas(){
        List<ConsultaDto> listaDto = new ArrayList<>();

        List<ConsultaEntity> lista = repository.findAll();

        for (ConsultaEntity consultas: lista){
            ConsultaDto consultaDto = new ConsultaDto();

            consultaDto.setId(consultas.getId());
            consultaDto.setTitulo(consultaDto.getTitulo());
            consultaDto.setDataDaConsulta(consultas.getDataDaConsulta());
            consultaDto.setStatus(consultaDto.getStatus());

            listaDto.add(consultaDto);

        }
        return listaDto;

    }

    public boolean criarConsulta(ConsultaDto consultaDto){

        ConsultaEntity consulta = new ConsultaEntity();

        consulta.setId(consulta.getId());
        consulta.setTitulo(consulta.getTitulo());
        consulta.setDataDaConsulta(consulta.getDataDaConsulta());
        consulta.setStatus(consulta.getStatus());
        repository.save(consulta);

        return true;
    }

    public boolean atualizarConsulta(Long id,ConsultaDto consultaDto){
        ConsultaEntity consulta = new ConsultaEntity();

        consulta.setId(consulta.getId());
        consulta.setTitulo(consulta.getTitulo());
        consulta.setDataDaConsulta(consulta.getDataDaConsulta());
        consulta.setStatus(consulta.getStatus());
        repository.save(consulta);

        return true;
    }

    public boolean excluirConsulta(long id){

        repository.deleteById(id);
        return true;

    }




}

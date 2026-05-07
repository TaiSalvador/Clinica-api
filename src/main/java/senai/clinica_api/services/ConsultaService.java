package senai.clinica_api.services;

import org.springframework.stereotype.Service;
import senai.clinica_api.dtos.ConsultaDto;
import senai.clinica_api.entities.ConsultaEntity;
import senai.clinica_api.entities.PacienteEntity;
import senai.clinica_api.repositories.ConsultaRepository;
import senai.clinica_api.repositories.PacienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    private final ConsultaRepository repository;
    private final PacienteRepository pacienteRepository;

    public ConsultaService(ConsultaRepository repository) {
        this.repository = repository;
        pacienteRepository = null;
    }


    public boolean inserirConsulta(ConsultaDto consultaDto) {

        // Validar se o paciente existe
        Optional<PacienteEntity> pacienteOptional = pacienteRepository.findById(consultaDto.getId());
        // Se não existir retornar erro
        if (pacienteOptional.isEmpty()) {
            throw new RuntimeException("Paciente da consulta não encontrado.");

        }
        // Pegar paciente encontrado
        PacienteEntity paciente = pacienteOptional.get();
        // Validar se já existe consulta
        boolean consultaExistente = repository.existsByPacienteAndDataHora(paciente, consultaDto.getDataDaConsulta());

        // Se já existir retornar erro
        if (consultaExistente) {
            throw new RuntimeException("Paciente já possui consulta agendada para a data e horário informados.");

        }
        // Criar consulta
        ConsultaEntity consulta = new ConsultaEntity();

        consulta.setId(consultaDto.getId());
        consulta.setTitulo(consultaDto.getTitulo());
        consulta.setDataDaConsulta(consultaDto.getDataDaConsulta());
        consulta.setStatus(consultaDto.getStatus());
        repository.save(consulta);

        return true;
    }

    public List<ConsultaDto> obterConsultas() {
        List<ConsultaDto> listaDto = new ArrayList<>();

        List<ConsultaEntity> lista = repository.findAll();

        for (ConsultaEntity consulta : lista) {
            ConsultaDto consultaDto = new ConsultaDto();

            consultaDto.setId(consulta.getId());
            consultaDto.setTitulo(consulta.getTitulo());
            consultaDto.setDataDaConsulta(consulta.getDataDaConsulta());
            consultaDto.setStatus(consulta.getStatus());

            listaDto.add(consultaDto);

        }
        return listaDto;

    }

    public boolean atualizarConsulta(Long id, ConsultaDto consultaDto) {

        Optional<ConsultaEntity> optional = repository.findById(id);

        // Validar se existe
        if (optional.isEmpty()) {
            throw new RuntimeException("Consulta não encontrada.");
        }

        // Pegar consulta do banco
        ConsultaEntity consulta = optional.get();

        // Atualizar dados
        consulta.setTitulo(consultaDto.getTitulo());
        consulta.setDataDaConsulta(consultaDto.getDataDaConsulta());
        consulta.setStatus(consultaDto.getStatus());

        repository.save(consulta);

        return true;
    }


    public boolean excluirConsulta(Long id) {

        Optional<ConsultaEntity> optional = repository.findById(id);

        // Validar se existe
        if (optional.isEmpty()) {
            throw new RuntimeException("Consulta não encontrada.");

        }
        // Excluir consulta
        repository.deleteById(id);

        return true;
    }
}



package senai.clinica_api.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import senai.clinica_api.dtos.ConsultaDto;
import senai.clinica_api.entities.ConsultaEntity;
import senai.clinica_api.entities.PacienteEntity;
import senai.clinica_api.repositories.ConsultaRepository;
import senai.clinica_api.repositories.PacienteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultaService {
    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;

    public ConsultaService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public boolean inserirConsulta(ConsultaDto consultaDto) {

        //verifica se existe paciente pelo o email
        PacienteEntity paciente = pacienteRepository.findByEmail(consultaDto.getEmailPaciente()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado"));


        //verifica se consulta ja foi marcada
        if (consultaRepository.existsByPacienteAndDataDaConsulta(paciente, consultaDto.getDataDaConsulta())) {

            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe consulta nessa data");
        }else {

        }

        ConsultaEntity consultaEntity = new ConsultaEntity();

        consultaEntity.setTitulo(consultaDto.getTitulo());
        consultaEntity.setDataDaConsulta(consultaDto.getDataDaConsulta());
        consultaEntity.setStatus(consultaDto.getStatus());
        consultaEntity.setPaciente(paciente);

        consultaRepository.save(consultaEntity);

        return true;
    }

    public List<ConsultaDto> obterConsulta() {

        List<ConsultaEntity> listaConsulta = consultaRepository.findAll();

        List<ConsultaDto> listaDto = new ArrayList<>();

        for (ConsultaEntity entity : listaConsulta) {
            ConsultaDto consulta = new ConsultaDto();

            consulta.setId(entity.getId());
            consulta.setTitulo(entity.getTitulo());
            consulta.setDataDaConsulta(entity.getDataDaConsulta());
            consulta.setStatus(entity.getStatus());
            consulta.setEmailPaciente(entity.getPaciente().getEmail());

            listaDto.add(consulta);
        }
        return listaDto;
    }

    public boolean atualizarConsulta(long id, ConsultaDto dto) {

        ConsultaEntity consulta = consultaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrada"));


        PacienteEntity paciente = pacienteRepository.findByEmail(dto.getEmailPaciente()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado"));

        if (consultaRepository.existsByPacienteAndDataDaConsultaAndIdNot(paciente, dto.getDataDaConsulta(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe consulta nessa data");
        }

        consulta.setTitulo(dto.getTitulo());
        consulta.setDataDaConsulta(dto.getDataDaConsulta());
        consulta.setStatus(dto.getStatus());
        consulta.setPaciente(paciente);

        consultaRepository.save(consulta);

        return true;
    }

    public boolean excluirConsulta(long id) {
        if (!consultaRepository.existsById(id)) {
            return false;
        }
        consultaRepository.deleteById(id);
        return true;
    }
}
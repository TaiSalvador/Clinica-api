package senai.clinica_api.services;

import org.springframework.stereotype.Service;
import senai.clinica_api.dtos.PacienteDto;
import senai.clinica_api.entities.PacienteEntity;
import senai.clinica_api.repositories.PacienteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public boolean cadastrar(PacienteDto pacienteDto) {
        PacienteEntity paciente = new PacienteEntity();

        paciente.setNome(pacienteDto.getNome());
        paciente.setEmail(pacienteDto.getEmail());
        repository.save(paciente);
        return true;
    }

    public List<PacienteDto> obterPaciente() {
        List<PacienteDto> listaDto = new ArrayList<>();

        List<PacienteEntity> lista = repository.findAll();

        for (PacienteEntity paciente: lista){
            PacienteDto pacienteDto = new PacienteDto();

            pacienteDto.setId(paciente.getId());
            paciente.setNome(paciente.getNome());
            paciente.setEmail(paciente.getEmail());

            listaDto.add(pacienteDto);
        }
        return listaDto;
    }

    public boolean atualizar(String email, PacienteDto pacienteDto) {
        PacienteEntity paciente = new PacienteEntity();

        paciente.setEmail(email);
        paciente.setNome(pacienteDto.getNome());

        repository.save(paciente);

        return true;
    }

    public boolean excluirPaciente(String email) {

    }

}

package senai.clinica_api.services;

import org.springframework.stereotype.Service;
import senai.clinica_api.dtos.PacienteDto;
import senai.clinica_api.entities.PacienteEntity;
import senai.clinica_api.repositories.PacienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public boolean cadastrar(PacienteDto pacienteDto) {

        List<PacienteDto> listaDto = new ArrayList<>();

        List<PacienteEntity> lista = repository.findAll();

        for (PacienteEntity paciente: lista) {
            if (paciente.getEmail().equals(pacienteDto.getEmail())) {
                return false;
            }
        }

        PacienteEntity paciente = new PacienteEntity();

        paciente.setNome(pacienteDto.getNome());
        paciente.setEmail(pacienteDto.getEmail());
        repository.save(paciente);
        return true;
    }

    public List<PacienteDto> obterPacientes() {
        List<PacienteDto> listaDto = new ArrayList<>();

        List<PacienteEntity> lista = repository.findAll();

        for (PacienteEntity paciente : lista) {
            PacienteDto pacienteDto = new PacienteDto();

            pacienteDto.setNome(paciente.getNome());
            pacienteDto.setEmail(paciente.getEmail());

            listaDto.add(pacienteDto);
        }
        return listaDto;
    }

    public PacienteDto obterPaciente(String email) {

        PacienteDto pacienteDto = new PacienteDto();

        Optional<PacienteEntity> pacienteOP = repository.findByEmail(email);

        PacienteEntity paciente = new PacienteEntity();

        if (pacienteOP.isPresent()) {
            paciente = pacienteOP.get();

            pacienteDto.setNome(paciente.getNome());
            pacienteDto.setEmail(paciente.getEmail());
        }
        else {

        }
        return pacienteDto;
    }

    public int atualizar(String email, PacienteDto pacienteDto) {

        Optional<PacienteEntity> optionalPaciente = repository.findByEmail(email);

        //-- validar se paciente existe
        if (optionalPaciente.isEmpty()) {

            return 404;
        }

        //-- validar email duplicado
        List<PacienteEntity> lista = repository.findAll();

        for (PacienteEntity pacienteLista : lista) {

            if (pacienteLista.getEmail().equals(pacienteDto.getEmail())
                    && !pacienteLista.getEmail().equals(email)) {

                return 409;
            }
        }

        //-- atualizar paciente
        PacienteEntity paciente = optionalPaciente.get();

        paciente.setNome(pacienteDto.getNome());
        paciente.setEmail(pacienteDto.getEmail());

        repository.save(paciente);

        return 200;
    }
    public boolean excluirPaciente(String email) {

        Optional<PacienteEntity> pacienteOP = repository.findByEmail(email);

        if (pacienteOP.isPresent()) {
            repository.delete(pacienteOP.get());
            return true;
        }

        return false;
    }
}
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

    public boolean atualizar(String email, PacienteDto pacienteDto) {
        Optional<PacienteEntity> optionalPaciente = repository.findByEmail(email);

        if (optionalPaciente.isPresent()) {
            //--encontrou o paciente e agora precsia atualizar!
            PacienteEntity paciente = optionalPaciente.get();
            paciente.setNome(pacienteDto.getNome());
            paciente.setEmail(pacienteDto.getEmail());
            repository.save(paciente);
            return true;

        } else {
            //--não encontrou o paciente e então não atualiza!
            return false;
        }
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
package senai.clinica_api.services;

import org.springframework.stereotype.Service;
import senai.clinica_api.repositories.PacienteRepository;

@Service
public class PacienteService {

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

}

package senai.clinica_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.clinica_api.entities.ConsultaEntity;
import senai.clinica_api.entities.PacienteEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

    public Optional<PacienteEntity> findByEmail(String email);
}

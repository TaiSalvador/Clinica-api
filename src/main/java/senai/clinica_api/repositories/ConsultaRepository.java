package senai.clinica_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.clinica_api.entities.ConsultaEntity;
import senai.clinica_api.entities.PacienteEntity;

import java.time.LocalDate;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {

    boolean existsByPacienteAndDataHora(
            PacienteEntity paciente,
            LocalDate dataDaConsulta
    );
}

package senai.clinica_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.clinica_api.entities.ConsultaEntity;
import senai.clinica_api.entities.PacienteEntity;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {

    Optional<ConsultaEntity> findByPacienteAndDataDaConsulta(PacienteEntity paciente, LocalDate dataDaConsulta);

    boolean existsByPacienteAndDataDaConsultaAndIdNot(PacienteEntity paciente, LocalDate dataDaConsulta, long id);

    boolean existsByPacienteAndDataDaConsulta(PacienteEntity paciente, LocalDate dataDaConsulta);

    boolean existsByPaciente(PacienteEntity paciente);
}


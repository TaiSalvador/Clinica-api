package senai.clinica_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.clinica_api.entities.ConsultaEntity;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {

}

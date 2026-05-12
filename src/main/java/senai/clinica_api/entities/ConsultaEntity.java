package senai.clinica_api.entities;

import  jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="Consulta")
public class ConsultaEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column( name="titulo")
    private String titulo;

    @Column( name="data da Consulta")
    private LocalDate dataDaConsulta;

    @Enumerated(EnumType.STRING)
    private StatusConsulta status;

    @ManyToOne
    @JoinColumn(name = "Paciente_id")
    private PacienteEntity paciente;

    public ConsultaEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataDaConsulta() {
        return dataDaConsulta;
    }

    public void setDataDaConsulta(LocalDate dataDaConsulta) {
        this.dataDaConsulta = dataDaConsulta;
    }

    public StatusConsulta getStatus() {
        return status;
    }

    public void setStatus(StatusConsulta status) {
        this.status = status;
    }

    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }
}

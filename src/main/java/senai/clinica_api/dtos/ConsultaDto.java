package senai.clinica_api.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import senai.clinica_api.entities.StatusConsulta;

import java.time.LocalDate;

public class ConsultaDto {

    private long id;

    @NotEmpty
    private String titulo;

    @NotNull
    @FutureOrPresent
    private LocalDate dataDaConsulta;

    @NotNull
    private StatusConsulta status;

    @NotEmpty
    private String emailPaciente;

    public ConsultaDto() {
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

    public String getEmailPaciente() {
        return emailPaciente;
    }

    public void setEmailPaciente(String emailPaciente) {
        this.emailPaciente = emailPaciente;
    }
}

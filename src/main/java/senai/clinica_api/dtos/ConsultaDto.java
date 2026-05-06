package senai.clinica_api.dtos;

import jakarta.validation.constraints.NotEmpty;

import java.security.PrivilegedAction;
import java.time.LocalDate;
import java.util.Date;

public class ConsultaDto {

    private long id;

    @NotEmpty
    private String titulo;

    private LocalDate dataDaConsulta;

    @NotEmpty
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

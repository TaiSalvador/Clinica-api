package senai.clinica_api.dtos;

import jakarta.validation.constraints.NotEmpty;

import java.security.PrivilegedAction;
import java.util.Date;

public class ConsultaDto {

    private long id;

    @NotEmpty
    private String titulo;

    @NotEmpty
    private Date dataDaConsulta;

    @NotEmpty
    private String Status;

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

    public Date getDataDaConsulta() {
        return dataDaConsulta;
    }

    public void setDataDaConsulta(Date dataDaConsulta) {
        this.dataDaConsulta = dataDaConsulta;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}

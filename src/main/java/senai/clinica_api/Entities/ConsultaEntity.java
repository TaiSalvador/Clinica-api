package senai.clinica_api.Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "consulta")
public class ConsultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "data da consulta ")
    private Date datadaConsulta;

    @Column(name = "status")
    private String status;

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

    public Date getDatadaConsulta() {
        return datadaConsulta;
    }

    public void setDatadaConsulta(Date datadaConsulta) {
        this.datadaConsulta = datadaConsulta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

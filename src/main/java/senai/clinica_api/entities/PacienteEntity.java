package senai.clinica_api.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Paciente")
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPaciente;

    @Column( name="nome")
    private String nome;

    @Column(name="email")
    private String email;

    public PacienteEntity() {
    }

    public long getId() {
        return idPaciente;
    }

    public void setId(long id) {
        this.idPaciente = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

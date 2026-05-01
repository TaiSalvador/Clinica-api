package senai.clinica_api.dtos;

import jakarta.validation.constraints.NotEmpty;

public class PacienteDto {

    private long id;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String email;

    public PacienteDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

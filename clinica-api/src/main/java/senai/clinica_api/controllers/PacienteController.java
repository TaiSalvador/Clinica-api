package senai.clinica_api.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senai.clinica_api.dtos.PacienteDto;
import senai.clinica_api.services.PacienteService;

@RestController
@RequestMapping("/Clinica-api")
public class PacienteController {

    //--Injeção de dependencia
    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @PostMapping("/paciente")
    public ResponseEntity<String> criarPaciente(@Valid @RequestBody PacienteDto pacienteDto) {

        boolean returno = service.cadastrar(pacienteDto);
        if (returno) {
            return ResponseEntity.status(HttpStatus.OK).body("Sucesso: retornar 200 “Paciente inserido com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: retornar 409 conflict “Já existe paciente”");
        }
    }
}

package senai.clinica_api.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senai.clinica_api.dtos.PacienteDto;
import senai.clinica_api.services.PacienteService;

import java.util.List;

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

    @GetMapping("/paciente")
    public ResponseEntity<List<PacienteDto>> obterPacientes() {
        List<PacienteDto> lista = service.obterPacientes();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/paciente/{email}")
    public ResponseEntity<PacienteDto> obterPaciente(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK).body(service.obterPaciente(email));
    }

    @PutMapping("/paciente/{email}")
    public ResponseEntity<String> atualizarPaciente(@Valid @RequestBody PacienteDto pacienteDto,
                                                    @PathVariable String email) {

        boolean retorno = service.atualizar(email, pacienteDto);

        if (retorno) {
            return ResponseEntity.status(HttpStatus.OK).body(" Sucesso: retornar 200 “Paciente atualizado com sucesso”.");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: sem paciente: retornar 404 : texto “Paciente não encontrado”.");
        }
    }


    // @DeleteMapping("/paciente/{email}")
    //public ResponseEntity<String> excluirPaciente(@PathVariable String email) {

    //    boolean retorno = service.excluirPaciente(email);

    // if (retorno) {
    //   return ResponseEntity.status(HttpStatus.OK).body("] Sucesso: retornar 200 : “Paciente excluído com sucesso”");
    //  } else {
    //   return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: retornar 404:  “Paciente não existe”.");
    //  }
    //* }
    // }
}
package senai.clinica_api.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senai.clinica_api.dtos.ConsultaDto;
import senai.clinica_api.dtos.PacienteDto;
import senai.clinica_api.entities.ConsultaEntity;
import senai.clinica_api.repositories.ConsultaRepository;
import senai.clinica_api.services.ConsultaService;

import java.util.List;

@RestController
@RequestMapping("/clinica-api")
public class ConsultaController {

    private final ConsultaService service;

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }

    @GetMapping("/consulta")
    public ResponseEntity<List<ConsultaDto>> obterConsultas(){

        List<ConsultaDto> lista = service.obterConsultas();
        return ResponseEntity.status(HttpStatus.OK).body(lista);

    }

    @PostMapping("/consulta")
    public ResponseEntity<String> criarConsulta(@Valid @RequestBody ConsultaDto consultaDto) {

        boolean returno = service.inserirConsulta(consultaDto);

        if (returno){
            return ResponseEntity.status(HttpStatus.OK).body("Sucesso: retornar 200 “Paciente inserido com sucesso");
    } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: retornar 404: com texto “Paciente da consulta não encontrado.");
            //return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: retornar 409 conflict “Paciente já possui consulta agendada para a data e horário informados");
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: retornar 400 : com texto de erro.");
        }

    }

    @PutMapping("consulta/{id}")
    public  ResponseEntity<String> atualizarConsulta(@PathVariable Long id, @RequestBody @Valid ConsultaDto consultaDto){

        boolean returno = service.atualizarConsulta(id, consultaDto);

        if (returno){
            return ResponseEntity.status(HttpStatus.OK).body("Sucesso: retornar 200 “Paciente inserido com sucesso");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body("Erro: retornar 409 conflict “Já existe paciente");
        }
    }

    @DeleteMapping("consulta/{id}")
    public ResponseEntity<String>  excluirConsulta(@PathVariable Long id){

        boolean returno = service.excluirConsulta(id);

        if (returno){
            return ResponseEntity.status(HttpStatus.OK).body("Sucesso: retornar 200 “Paciente inserido com sucesso");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body("Erro: retornar 409 conflict “Já existe paciente");
        }

    }
}

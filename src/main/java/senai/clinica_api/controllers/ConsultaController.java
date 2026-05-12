package senai.clinica_api.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/Clinica-api")
public class ConsultaController {


    private final ConsultaService service;

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }


    //esta faltando regra de negocio
    @PostMapping("/consulta")
    public ResponseEntity<String> criarConsulta(@Valid @RequestBody ConsultaDto consultaDto) {

        int resultado = service.inserirConsulta(consultaDto);

        if (resultado == 400){
            return ResponseEntity.status(400).body("Retornar 400 : com texto de erro.");
        }
        if (resultado == 404) {
            return ResponseEntity.status(404).body("Paciente da consulta não encontrado");
        }

        if (resultado == 409) {
            return ResponseEntity.status(409).body("Paciente já possui consulta agendada para a data informada");
        }

        return ResponseEntity.status(200).body("Consulta cadastrada com sucesso");

    }

    //esta tando certo
    @GetMapping("/consultas")
    public ResponseEntity<List<ConsultaDto>> obterConsultas(){

        List<ConsultaDto> lista = service.obterConsultas();
        return ResponseEntity.status(HttpStatus.OK).body(lista);

    }

    /*@GetMapping("/consulta/{id}")
    public ResponseEntity<ConsultaDto> buscarConsulta(@PathVariable long id){
        ConsultaDto consulta = service.buscarConsulta(id);

        if(consulta == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(consulta);

    }*/

    @PutMapping("consulta/{id}")
    public ResponseEntity<String> atualizarConsulta(@PathVariable Long id, @RequestBody @Valid ConsultaDto consultaDto) {
        try {
            service.atualizarConsulta(id, consultaDto);
            return ResponseEntity.status(HttpStatus.OK).body("Consulta atualizada com sucesso.");

        } catch (RuntimeException e) {
            if (e.getMessage().equals("Consulta não encontrada.")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada.");

            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
        }

    }

    //certo
    @DeleteMapping("/consulta/{id}")
    public ResponseEntity<String> excluirConsulta(@PathVariable Long id) {
        try {
            service.excluirConsulta(id);
            return ResponseEntity.status(HttpStatus.OK).body("Consulta excluída com sucesso.");

        } catch (RuntimeException e) {

            if (e.getMessage().equals("Consulta não encontrada.")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada.");

            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());

        }
    }
}


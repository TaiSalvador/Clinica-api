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
@RequestMapping("/clinica-api")
public class ConsultaController {


    private final ConsultaService service;

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }


    @PostMapping("/consulta")
    public ResponseEntity<String> criarConsulta(@RequestBody @Valid ConsultaDto consultaDto) {
        try {
            service.inserirConsulta(consultaDto);

            // SUCESSO 200
            return ResponseEntity.status(HttpStatus.OK).body("Consulta inserida com sucesso.");

        } catch (RuntimeException e) {
            // ERRO 404
            if (e.getMessage().equals("Paciente da consulta não encontrado.")) {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente da consulta não encontrado.");

            }
            // ERRO 409
            if (e.getMessage().equals("Paciente já possui consulta agendada para a data e horário informados.")) {

                return ResponseEntity.status(HttpStatus.CONFLICT).body("Paciente já possui consulta agendada para a data e horário informados.");

            }
            // ERRO 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
        }
    }

    @GetMapping("/consulta")
    public ResponseEntity<List<ConsultaDto>> obterConsultas(){

        List<ConsultaDto> lista = service.obterConsultas();
        return ResponseEntity.status(HttpStatus.OK).body(lista);

    }

    @PutMapping("/consulta/{id}")
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


package senai.clinica_api.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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

    @PostMapping("/consulta")
    public ResponseEntity<String> cadastrarConsulta(@RequestBody @Valid ConsultaDto consultaDto) {

        try {

            service.inserirConsulta(consultaDto);

            // SUCESSO 200
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Consulta inserida com sucesso");

        } catch (ResponseStatusException e) {

            // ERRO 404 → paciente não encontrado
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {

                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Paciente da consulta não encontrado");
            }

            // ERRO 409 → consulta já existe
            if (e.getStatusCode() == HttpStatus.CONFLICT) {

                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Já existe consulta nessa data");
            }

            // OUTROS ERROS
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: " + e.getReason());

        } catch (Exception e) {

            // ERRO 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno do servidor");


            //service.inserirConsulta(consultaDto);
            //return ResponseEntity.ok().body("Consulta inserida com sucesso");

        }
    }

    @GetMapping("/consultas")
    public ResponseEntity<Object> listarConsultas() {
        List<ConsultaDto> consultas = service.obterConsulta();
        if (consultas.isEmpty()) return ResponseEntity.status(404).body("Lista Vazia de Consultas");
        return ResponseEntity.ok(consultas);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarConsulta(@PathVariable @Valid long id, @RequestBody ConsultaDto consultaDto) {
        service.atualizarConsulta(id, consultaDto);
        return ResponseEntity.ok().body("Consulta atualizada com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarConsulta(@PathVariable long id) {
        boolean resposta = service.excluirConsulta(id);
        if (!resposta) return ResponseEntity.status(404).body("Consulta não encontrada!");
        return ResponseEntity.ok("Consulta excluída com sucesso");
    }
}


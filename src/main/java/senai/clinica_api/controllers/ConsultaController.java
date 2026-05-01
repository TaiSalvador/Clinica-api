package senai.clinica_api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senai.clinica_api.dtos.ConsultaDto;
import senai.clinica_api.entities.ConsultaEntity;
import senai.clinica_api.services.ConsultaService;

import java.security.Provider;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/clinica-api")
public class ConsultaController {

    private final ConsultaService service;

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }

    @GetMapping("/consulta")
    public ConsultaEntity<List<ConsultaDto>> obterConsultas(){
       // List<ConsultaDto> lista = service.obterLista();
        //return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

}

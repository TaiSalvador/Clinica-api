package senai.clinica_api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senai.clinica_api.services.PacienteService;

@RestController
@RequestMapping("/Clinica-api")
public class PacienteController {

    //--Injeção de dependencia
    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

}

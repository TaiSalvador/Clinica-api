package senai.clinica_api.services;

import org.springframework.stereotype.Service;
import senai.clinica_api.dtos.ConsultaDto;
import senai.clinica_api.entities.ConsultaEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultaService {

    public List<ConsultaDto> obterConsultas(){

        List<ConsultaDto> listaconsulta = new ArrayList<>();

        //List<ConsultaEntity> lista = repository.findAll();


        for (ConsultaEntity livro : lista){

            ConsultaDto livroDto = new LivroDto();



            listaDto.add(livroDto);
        }

        return listaDto;
    }

}

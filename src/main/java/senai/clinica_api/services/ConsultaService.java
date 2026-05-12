package senai.clinica_api.services;

import org.springframework.stereotype.Service;
import senai.clinica_api.dtos.ConsultaDto;
import senai.clinica_api.dtos.PacienteDto;
import senai.clinica_api.entities.ConsultaEntity;
import senai.clinica_api.entities.PacienteEntity;
import senai.clinica_api.repositories.ConsultaRepository;
import senai.clinica_api.repositories.PacienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;

    public ConsultaService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository){
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
    }


    //falta regra dde negocio
    public int inserirConsulta(ConsultaDto consultaDto) {

        Optional<PacienteEntity> pacienteOP = pacienteRepository.findByEmail(consultaDto.getEmail());

        if (pacienteOP.isEmpty()) {
            return 400;
        }

        // paciente não encontrado
        if (pacienteOP.isEmpty()) {
            return 404;
        }

        List<ConsultaEntity> lista = consultaRepository.findAll();

        // verificar se já existe consulta
        for (ConsultaEntity consultaLista : lista) {

            if (consultaLista.getPaciente().getEmail().equals(consultaDto.getEmail()) && consultaLista.getDataDaConsulta().equals(consultaDto.getDataDaConsulta())) {
                return 409;
            }
        }


        ConsultaEntity consulta = new ConsultaEntity();

        consulta.setTitulo(consultaDto.getTitulo());
        consulta.setDataDaConsulta(consultaDto.getDataDaConsulta());
        consulta.setStatus(consultaDto.getStatus());

        // usar paciente encontrado
        consulta.setPaciente(pacienteOP.get());

        consultaRepository.save(consulta);

        return 200;
    }

    //esta tando certo
    public List<ConsultaDto> obterConsultas() {


        List<ConsultaDto> listaDto = new ArrayList<>();

        List<ConsultaEntity> lista = consultaRepository.findAll();

        for (ConsultaEntity consulta : lista) {
            ConsultaDto consultaDto = new ConsultaDto();

            consultaDto.setId(consulta.getId());
            consultaDto.setTitulo(consulta.getTitulo());
            consultaDto.setDataDaConsulta(consulta.getDataDaConsulta());
            consultaDto.setStatus(consulta.getStatus());
            consultaDto.setEmail(consulta.getPaciente().getNome());

            listaDto.add(consultaDto);

        }
        return listaDto;

    }


    /*public ConsultaDto buscarConsulta(long id){

        ConsultaEntity consulta = consultaRepository.findById(id).orElse(null);

        if(consulta == null){
            return null;
        }

        ConsultaDto consultaDto = new ConsultaDto();

        consultaDto.setDataDaConsulta(consulta.getDataDaConsulta());

        return consultaDto;

    }*/

    //esta tando certo
    public boolean atualizarConsulta(Long id, ConsultaDto consultaDto) {

        Optional<ConsultaEntity> optional = consultaRepository.findById(id);

        // Validar se existe
        if (optional.isEmpty()) {
            throw new RuntimeException("Consulta não encontrada.");
        }

        // Pegar consulta do banco
        ConsultaEntity consulta = optional.get();

        // Atualizar dados
        consulta.setTitulo(consultaDto.getTitulo());
        consulta.setDataDaConsulta(consultaDto.getDataDaConsulta());
        consulta.setStatus(consultaDto.getStatus());

        consultaRepository.save(consulta);

        return true;

        /*Optional<PacienteEntity> optionalPaciente = repository.findByEmail(email);

        if (optionalPaciente.isPresent()) {
            //--encontrou o paciente e agora precsia atualizar!
            PacienteEntity paciente = optionalPaciente.get();
            paciente.setNome(pacienteDto.getNome());
            paciente.setEmail(pacienteDto.getEmail());
            repository.save(paciente);
            return true;

        } else {
            //--não encontrou o paciente e então não atualiza!
            return false;
        }
    }
    */

    }


    //esta funcionando
    public boolean excluirConsulta(Long id) {

        Optional<ConsultaEntity> optional = consultaRepository.findById(id);
        //Validar se existe
        if (optional.isEmpty()) {
           throw new RuntimeException("Consulta não encontrada.");

        }

        // Excluir consulta
        consultaRepository.deleteById(id);

        return true;
    }
}



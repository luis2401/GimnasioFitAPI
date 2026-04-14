package com.gimnasio.fit.api.servicio;


import com.gimnasio.fit.api.dto.ClaseDTO;
import com.gimnasio.fit.api.dto.SocioDTO;
import com.gimnasio.fit.api.modelo.Clase;
import com.gimnasio.fit.api.modelo.Socio;
import com.gimnasio.fit.api.repositorio.ClaseRepositorio;
import com.gimnasio.fit.api.repositorio.InstructorRepositorio;
import com.gimnasio.fit.api.repositorio.SocioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClaseServicio {

    @Autowired
    private SocioRepositorio socioRepositorio;

    @Autowired
    private ClaseRepositorio claseRepositorio;

    @Autowired
    private InstructorRepositorio instructorRepositorio;

    public List<ClaseDTO> listarClase(){
        List<Clase> listaClase = claseRepositorio.findAll();

       return listaClase.stream()
               .map(clase -> {
                   return convertirDTO(clase);
               }).toList();
    }

    public String agregarSocioClase(String dni, Integer id){

        Clase claseEnc = claseRepositorio.findById(id)
                        .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        Socio socioEnc = socioRepositorio.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado!"));

        claseEnc.getSocios().add(socioEnc);
        socioEnc.getClases().add(claseEnc);
        claseRepositorio.save(claseEnc);
        socioRepositorio.save(socioEnc);

        return "Socio agregado a la clase correctamene";
    }

    public ClaseDTO convertirDTO(Clase clase){
        ClaseDTO claseDTO = new ClaseDTO();
        claseDTO.setIdClase(clase.getIdClase());
        claseDTO.setNombreClase(clase.getNombreClase());
        claseDTO.setHorarioClase(clase.getHorarioClase());
        claseDTO.setDiaSemana(clase.getDiaSemana());
        claseDTO.setCupoMax(clase.getCupoMax());
        claseDTO.setActivo(clase.isActivo());
        claseDTO.setIdInstructor(clase.getInstructor().getIdInstructor());
        List<SocioDTO> lista = clase.getSocios().stream()
                .map(socio -> {
                    SocioDTO socioDTO = new SocioDTO();
                    socioDTO.setDni(socio.getDni());
                    socioDTO.setNombre(socio.getNombre());
                    socioDTO.setApellido(socio.getApellido());
                    socioDTO.setActivo(socio.isActivo());
                    return socioDTO;
                }).toList();

        claseDTO.setSocios(lista);

        return claseDTO;
    }

    public ClaseDTO agregarClase(ClaseDTO claseDTO, String nombreIns) {
        Clase claseEntidad = new Clase();

        claseEntidad.setNombreClase(claseDTO.getNombreClase());
        claseEntidad.setHorarioClase(claseDTO.getHorarioClase());
        claseEntidad.setDiaSemana(claseDTO.getDiaSemana());
        claseEntidad.setCupoMax(claseDTO.getCupoMax());
        claseEntidad.setActivo(claseDTO.isActivo());
        claseEntidad.setInstructor(instructorRepositorio.findByNombreInstructorIgnoreCase(nombreIns));
        claseEntidad.setSocios(new ArrayList<>());

        claseRepositorio.save(claseEntidad);

        return  convertirDTO(claseEntidad);

    }

    public List<ClaseDTO> buscarClasePorId(Integer id){
        return claseRepositorio.findById(id).stream()
                .map(clase -> {
                    return convertirDTO(clase);
                }).toList()
                ;
    }

    public ClaseDTO editarClase(Integer id, ClaseDTO claseDTO){
        Clase claseExis = claseRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        claseExis.setNombreClase(claseDTO.getNombreClase());
        claseExis.setHorarioClase(claseDTO.getHorarioClase());
        claseExis.setDiaSemana(claseDTO.getDiaSemana());
        claseExis.setCupoMax(claseDTO.getCupoMax());

        claseRepositorio.save(claseExis);
        return convertirDTO(claseExis);
    }

    public String activarDesClase(Integer id){
        Clase claseExis = claseRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada") );

        claseExis.setActivo(!claseExis.isActivo());
        return "Clase activada o desactiva correctamente!";
    }

    public String eliminarClase(Integer id){
        Clase claseExis = claseRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        claseRepositorio.delete(claseExis);
        return "Clase eliminada con exito!";
    }

    public String quitarSocioClase(String dni, Integer id){
        Clase claseExis = claseRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        Socio socio = socioRepositorio.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Socio no encontrasdo!"));

        claseExis.getSocios().remove(socio);
        socio.getClases().remove(claseExis);
        claseRepositorio.save(claseExis);
        socioRepositorio.save(socio);

        return "Socio eliminado de la clase correctamente!";

    }

    public Integer contarSocioInscritos(Integer id){
        Clase claseExis = claseRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        return claseExis.getSocios().size();
    }


}



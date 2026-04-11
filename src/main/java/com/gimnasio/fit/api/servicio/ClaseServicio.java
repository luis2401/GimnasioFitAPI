package com.gimnasio.fit.api.servicio;


import com.gimnasio.fit.api.dto.ClaseDTO;
import com.gimnasio.fit.api.dto.SocioDTO;
import com.gimnasio.fit.api.modelo.Clase;
import com.gimnasio.fit.api.modelo.Instructor;
import com.gimnasio.fit.api.modelo.Socio;
import com.gimnasio.fit.api.repositorio.ClaseRepositorio;
import com.gimnasio.fit.api.repositorio.InstructorRepositorio;
import com.gimnasio.fit.api.repositorio.SocioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public String agregarSocioClase(String dni, String nombreClase){

        Socio socioEnc = socioRepositorio.findByDni(dni);
        Clase claseEnc = claseRepositorio.findBynombreClase(nombreClase);

        claseEnc.getSocios().add(socioEnc);
        socioEnc.getClases().add(claseEnc);
        claseRepositorio.save(claseEnc);
        socioRepositorio.save(socioEnc);

        return "Socio agregado correctamene";
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
        claseEntidad.setInstructor(instructorRepositorio.findBynombreInstructor(nombreIns));
        claseEntidad.setSocios(new ArrayList<>());

        claseRepositorio.save(claseEntidad);

        return  convertirDTO(claseEntidad);

    }



}



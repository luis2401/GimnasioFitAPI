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

        List<Socio> listaSo = socioRepositorio.findAll();

        return listaClase.stream().map(clase -> {
            ClaseDTO claseDTO = new ClaseDTO();
            claseDTO.setIdClase(clase.getIdClase());
            claseDTO.setNombreClase(clase.getNombreClase());
            claseDTO.setHorarioClase(clase.getHorarioClase());
            claseDTO.setIdInstructor(clase.getInstructor().getIdInstructor());
            claseDTO.setSocios(listaSo.stream().map(socio -> {
                SocioDTO socioDTO = new SocioDTO();
                socioDTO.setDni(socio.getDni());
                socioDTO.setNombre(socio.getNombre());
                socioDTO.setApellido(socio.getApellido());
                socioDTO.setActivo(socio.isActivo());
                return socioDTO;
            }).toList());
            return claseDTO;
        }).toList();
    }

    public ClaseDTO agregarClase(ClaseDTO claseDTO, String nombre){
        Clase entidad = new Clase();

        Instructor entidadIns = instructorRepositorio.findBynombreInstructor(nombre);


        entidad.setNombreClase(claseDTO.getNombreClase());
        entidad.setHorarioClase(claseDTO.getHorarioClase());
        entidad.setInstructor(entidadIns);

        entidadIns.setClases(entidad.getInstructor().getClases());

        instructorRepositorio.save(entidadIns);

        claseRepositorio.save(entidad);

        return claseDTO;

    }

}



package com.gimnasio.fit.api.servicio;

import com.gimnasio.fit.api.dto.ClaseDTO;
import com.gimnasio.fit.api.dto.InstructorDTO;
import com.gimnasio.fit.api.modelo.Clase;
import com.gimnasio.fit.api.modelo.Instructor;
import com.gimnasio.fit.api.repositorio.ClaseRepositorio;
import com.gimnasio.fit.api.repositorio.InstructorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServicio {

    @Autowired
    private InstructorRepositorio instructorRepositorio;

    @Autowired
    private ClaseRepositorio claseRepositorio;

    public List<InstructorDTO> listarInstructor() {
        List<Instructor> listaIns = instructorRepositorio.findAll();
        List<Clase> listaClase = claseRepositorio.findAll();

        List<InstructorDTO> listaInsDto = listaIns.stream().map(instructor -> {
            InstructorDTO instructorDTO = new InstructorDTO();
            instructorDTO.setIdInstructor(instructor.getIdInstructor());
            instructorDTO.setNombreInstructor(instructor.getNombreInstructor());
            instructorDTO.setApellidoInstructor(instructor.getApellidoInstructor());
            instructorDTO.setTelefono(instructor.getTelefono());
            instructorDTO.setEmail(instructor.getEmail());
            instructorDTO.setClaseDTO(listaClase.stream().map(clase -> {
                ClaseDTO claseDTO = new ClaseDTO();
                claseDTO.setIdInstructor(instructor.getIdInstructor());
                claseDTO.setIdClase(clase.getIdClase());
                claseDTO.setNombreClase(clase.getNombreClase());
                claseDTO.setHorarioClase(clase.getHorarioClase());
                return claseDTO;
            }).toList());
            return instructorDTO;
        }).toList();
        return listaInsDto;
    }



    public InstructorDTO agregarInstructor(InstructorDTO instructorDTO){
        Instructor entidad = new Instructor();

        entidad.setNombreInstructor(instructorDTO.getNombreInstructor());
        entidad.setApellidoInstructor(instructorDTO.getApellidoInstructor());
        entidad.setEspecialidadInstructor(instructorDTO.getEspecialidadInstructor());
        entidad.setTelefono(instructorDTO.getTelefono());
        entidad.setEmail(instructorDTO.getEmail());

        instructorRepositorio.save(entidad);

        return instructorDTO;
    }
}


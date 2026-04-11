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

    public InstructorDTO convertirDTO(Instructor instructor){
        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setIdInstructor(instructor.getIdInstructor());
        instructorDTO.setNombreInstructor(instructor.getNombreInstructor());
        instructorDTO.setApellidoInstructor(instructor.getApellidoInstructor());
        instructorDTO.setTelefono(instructor.getTelefono());
        instructorDTO.setEmail(instructor.getEmail());
        instructorDTO.setClaseDTO(instructor.getClases().stream()
                .map(clase -> {
                    ClaseDTO claseDTO = new ClaseDTO();
                    claseDTO.setIdInstructor(instructor.getIdInstructor());
                    claseDTO.setIdClase(clase.getIdClase());
                    claseDTO.setNombreClase(clase.getNombreClase());
                    claseDTO.setHorarioClase(clase.getHorarioClase());
                    return claseDTO;
                }).toList());
        return instructorDTO;
    }

    public Instructor convertirEntidad(InstructorDTO instructorDTO){
        Instructor entidad = new Instructor();
        entidad.setNombreInstructor(instructorDTO.getNombreInstructor());
        entidad.setApellidoInstructor(instructorDTO.getApellidoInstructor());
        entidad.setTelefono(instructorDTO.getTelefono());
        entidad.setEmail(instructorDTO.getEmail());

        return entidad;
    }

    public List<InstructorDTO> listarInstructor() {
        List<Instructor> listaIns = instructorRepositorio.findAll();
        List<Clase> listaClase = claseRepositorio.findAll();

        List<InstructorDTO> listaInsDto = listaIns.stream()
                .map(instructor -> convertirDTO(instructor))
                .toList();
        return listaInsDto;
    }

    public InstructorDTO agregarInstructor(InstructorDTO instructorDTO){
        instructorRepositorio.save(convertirEntidad(instructorDTO));
        return instructorDTO;
    }

    public InstructorDTO editarInstructor(String nombre, InstructorDTO instructorDTO){
        Instructor instructorExistente = instructorRepositorio.findBynombreInstructor(nombre);

        if (instructorExistente == null){
            return null;
        }
        //falta validacion de body instructordto

        instructorRepositorio.save(convertirEntidad(instructorDTO));
        return convertirDTO(instructorExistente);
    }

}


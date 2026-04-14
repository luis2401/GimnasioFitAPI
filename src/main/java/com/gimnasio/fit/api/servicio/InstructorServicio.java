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
        instructorDTO.setEspecialidadInstructor(instructor.getEspecialidadInstructor());
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
        entidad.setEspecialidadInstructor(instructorDTO.getEspecialidadInstructor());
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
        Instructor instructorExistente = instructorRepositorio.findByNombreInstructorIgnoreCase(nombre)
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado con ese nombre!"));

        if(instructorDTO.getNombreInstructor() == null || instructorDTO.getNombreInstructor().isBlank() ||
            instructorDTO.getApellidoInstructor() == null || instructorDTO.getApellidoInstructor().isBlank() ||
                instructorDTO.getEspecialidadInstructor() == null || instructorDTO.getEspecialidadInstructor().isBlank() ||
                instructorDTO.getEmail() == null || instructorDTO.getEmail().isBlank() ||
                instructorDTO.getTelefono() == null || instructorDTO.getTelefono().isBlank())
        {
            return null;
        }

        instructorExistente.setNombreInstructor(instructorDTO.getNombreInstructor());
        instructorExistente.setApellidoInstructor(instructorDTO.getApellidoInstructor());
        instructorExistente.setEspecialidadInstructor(instructorDTO.getEspecialidadInstructor());
        instructorExistente.setTelefono(instructorDTO.getTelefono());
        instructorExistente.setEmail(instructorDTO.getEmail());
        instructorRepositorio.save(instructorExistente);
        return convertirDTO(instructorExistente);
    }


    public String eliminarInsNom(String nombre){
        instructorRepositorio.findByNombreInstructorIgnoreCase(nombre)
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado con ese nombre!"));

        instructorRepositorio.deleteByNombreInstructorIgnoreCase(nombre);
        return "Instructor eliminado con exito!";
    }

    public String eliminarIns(Integer id){
        if (instructorRepositorio.existsById(id)){
            instructorRepositorio.deleteById(id);
            return "Instructor eliminado con exito";
        }
        return "No se encontro un instructor con el id ingresado!";
    }

}


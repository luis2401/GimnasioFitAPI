package com.gimnasio.fit.api.servicio;

import com.gimnasio.fit.api.dto.ClaseDTO;
import com.gimnasio.fit.api.dto.SocioDTO;
import com.gimnasio.fit.api.modelo.Clase;
import com.gimnasio.fit.api.modelo.Instructor;
import com.gimnasio.fit.api.modelo.Socio;
import com.gimnasio.fit.api.repositorio.ClaseRepositorio;
import com.gimnasio.fit.api.repositorio.InstructorRepositorio;
import com.gimnasio.fit.api.repositorio.SocioRepositorio;
import jakarta.transaction.Transactional;
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

    public List<ClaseDTO> listarClase() {
        List<Clase> listaClase = claseRepositorio.findAll();

        return listaClase.stream()
                .map(clase -> {
                    return convertirDTO(clase);
                }).toList();
    }

    @Transactional
    public String agregarSocioClase(String dni, Integer id) {

        Clase claseEnc = claseRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        Socio socioEnc = socioRepositorio.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado!"));


        if (!validarCupoDisponible(claseEnc)) {
            return "No se pueden agregar mas socios porque la clase esta llena";
        }

        if (validarSocioClase(socioEnc, claseEnc)) {
            return "El socio ya esta inscrito en esta clase!";
        }

        claseEnc.getSocios().add(socioEnc);
        socioEnc.getClases().add(claseEnc);
        claseRepositorio.save(claseEnc);
        socioRepositorio.save(socioEnc);

        return "Socio agregado a la clase correctamene";
    }

    public ClaseDTO convertirDTO(Clase clase) {
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

    @Transactional
    public ClaseDTO agregarClase(ClaseDTO claseDTO, String nombreIns) {
        Clase claseEntidad = new Clase();
        Instructor instructor = instructorRepositorio.findByNombreInstructorIgnoreCase(nombreIns)
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado!"));

        claseEntidad.setNombreClase(claseDTO.getNombreClase());
        claseEntidad.setHorarioClase(claseDTO.getHorarioClase());
        claseEntidad.setDiaSemana(claseDTO.getDiaSemana());
        claseEntidad.setCupoMax(claseDTO.getCupoMax());
        claseEntidad.setActivo(true);
        claseEntidad.setInstructor(instructor);
        claseEntidad.setSocios(new ArrayList<>());

        claseRepositorio.save(claseEntidad);

        return convertirDTO(claseEntidad);

    }

    public List<ClaseDTO> buscarClasePorId(Integer id) {
        return claseRepositorio.findById(id).stream()
                .map(clase -> {
                    return convertirDTO(clase);
                }).toList()
                ;
    }

    public ClaseDTO editarClase(Integer id, ClaseDTO claseDTO) {
        Clase claseExis = claseRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        claseExis.setNombreClase(claseDTO.getNombreClase());
        claseExis.setHorarioClase(claseDTO.getHorarioClase());
        claseExis.setDiaSemana(claseDTO.getDiaSemana());
        claseExis.setCupoMax(claseDTO.getCupoMax());

        claseRepositorio.save(claseExis);
        return convertirDTO(claseExis);
    }

    public String activarDesClase(Integer id) {
        Clase claseExis = claseRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        claseExis.setActivo(!claseExis.isActivo());
        claseRepositorio.save(claseExis);
        return "Clase activada o desactiva correctamente!";
    }

    @Transactional
    public String eliminarClase(Integer id) {
        Clase claseExis = claseRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        claseRepositorio.delete(claseExis);
        return "Clase eliminada con exito!";
    }

    @Transactional
    public String quitarSocioClase(String dni, Integer id) {
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

    public Integer contarSocioInscritos(Integer id) {
        Clase claseExis = claseRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        return claseExis.getSocios().size();
    }

    public boolean validarCupoDisponible(Clase clase) {
        return clase.getCupoMax() > clase.getSocios().size();
    }

    public boolean validarSocioClase(Socio socio, Clase clase) {
        return clase.getSocios().contains(socio);
    }

    @Transactional
    public String cambiarInstructorClase(Integer id, String nombreIns){
        Clase claseExis = claseRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        Instructor instructorExis = instructorRepositorio.findByNombreInstructorIgnoreCase(nombreIns)
                .orElseThrow(() -> new RuntimeException("Instructor con ese nombre no encontrado!"));

        claseExis.setInstructor(instructorExis);
        claseRepositorio.save(claseExis);
        return "Instructor cambiado con exito!";
    }

}



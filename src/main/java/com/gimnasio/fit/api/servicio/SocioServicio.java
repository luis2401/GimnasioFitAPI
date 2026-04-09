package com.gimnasio.fit.api.servicio;

import com.gimnasio.fit.api.dto.SocioDTO;
import com.gimnasio.fit.api.modelo.Socio;
import com.gimnasio.fit.api.repositorio.SocioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SocioServicio {

    @Autowired
    private SocioRepositorio socioRepositorio;

    public SocioDTO convertirDTO(Socio socio){
        SocioDTO dto = new SocioDTO();
        dto.setDni(socio.getDni());
        dto.setNombre(socio.getNombre());
        dto.setApellido(socio.getApellido());
        dto.setActivo(socio.isActivo());

        return dto;
    }


    public Socio convertirEntidad(SocioDTO socioDTO){
        Socio entidad = new Socio();
        entidad.setDni(socioDTO.getDni());
        entidad.setNombre(socioDTO.getNombre());
        entidad.setApellido(socioDTO.getApellido());
        entidad.setActivo(socioDTO.isActivo());

        return entidad;
    }

    public List<SocioDTO> obtenerTodosLosSocios() {
        List<Socio> listaEntidades = socioRepositorio.findAll();

        return listaEntidades.stream()
                .map(socio -> {
                   return convertirDTO(socio);
                })
                .toList();
    }

    public SocioDTO guardarSocio(SocioDTO socioDTO){
        if(socioRepositorio.existsByDni(socioDTO.getDni())){
           throw new RuntimeException("El DNI " + socioDTO.getDni() + " ya está registrado.");
        }
        Socio entidad = convertirEntidad(socioDTO);

        socioRepositorio.save(entidad);
        return socioDTO;
    }

    public String eliminarSocio(String dni){
        Socio socioExistente = socioRepositorio.findByDni(dni);

        if (socioExistente != null){
            socioRepositorio.delete(socioExistente);
            return "Elimnado con exito";
        }
        return "No se encontro socio con ese dni";
    }

//    public String editarMembresia(String dni, LocalDate nuevaFecha){
//        Socio socioExistente = socioRepositorio.findByDni(dni);
//
//        if (socioExistente != null){
//            socioExistente.setFechaFinMembresia(nuevaFecha);
//            socioRepositorio.save(socioExistente);
//            return "Socio editado correctamente";
//        }
//        return "No se encontro un socio";
//    }

//    public String tieneAcceso(String dni){
//        Socio socioExistente = socioRepositorio.findByDni(dni);
//
//        if (socioExistente !=null){
//            if(!socioExistente.getFechaFinMembresia().isBefore(LocalDate.now())){
//                return "Socio tiene acceso";
//            }
//            else {
//                return "Socio no tiene acceso";
//            }
//        }
//        return "Socio no encontrado";
//    }

    public SocioDTO buscarPorDni(String dni){
        Socio socioExistente = socioRepositorio.findByDni(dni);

        if (socioExistente !=null){
            SocioDTO socioDTO= convertirDTO(socioExistente);
            return socioDTO;
        }
        return null;
    }

//    public List<SocioDTO> sociosVencidos(){
//        List<Socio> lista = socioRepositorio.findAll();
//
//
//        return lista.stream()
//                .filter(socio -> socio.getFechaFinMembresia().isBefore(LocalDate.now()))
//                .map(socio -> convertirDTO(socio))
//                .toList();
//    }


}

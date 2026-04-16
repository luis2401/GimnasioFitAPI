package com.gimnasio.fit.api.servicio;

import com.gimnasio.fit.api.dto.SocioDTO;
import com.gimnasio.fit.api.modelo.Socio;
import com.gimnasio.fit.api.repositorio.PagoRepositorio;
import com.gimnasio.fit.api.repositorio.SocioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class SocioServicio {

    @Autowired
    private SocioRepositorio socioRepositorio;

    @Autowired
    private PagoRepositorio pagoRepositorio;

    public SocioDTO convertirDTO(Socio socio){
        SocioDTO dto = new SocioDTO();
        dto.setDni(socio.getDni());
        dto.setNombre(socio.getNombre());
        dto.setApellido(socio.getApellido());
        dto.setFechaFinMembresia(socio.getFechaFinMembresia());
        dto.setActivo(socio.isActivo());

        return dto;
    }


    public Socio convertirEntidad(SocioDTO socioDTO){
        Socio entidad = new Socio();
        entidad.setDni(socioDTO.getDni());
        entidad.setNombre(socioDTO.getNombre());
        entidad.setApellido(socioDTO.getApellido());
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

    public List<SocioDTO> obtenerSociosActivos(){
        List<Socio> lista = socioRepositorio.findAll();

        return lista
                .stream()
                .filter(socio -> {
                    return socio.isActivo();
                })
                .map(socio -> {
                    return convertirDTO(socio);
                }).toList();
    }

    public List<SocioDTO> obtenerSociosInactivos(){
        List<Socio> lista = socioRepositorio.findAll();

        return lista.stream()
                .filter(socio -> {
                    return !socio.isActivo();
                })
                .map(socio -> {
                    return convertirDTO(socio);
                })
                .toList();
    }

    public SocioDTO guardarSocio(SocioDTO socioDTO){

        if (socioDTO.getNombre() == null || socioDTO.getNombre().isBlank() ||
                socioDTO.getApellido() == null || socioDTO.getApellido().isBlank() ||
                socioDTO.getDni() == null || socioDTO.getDni().isBlank())
        {
            return null;
        }

        if (!socioDTO.getDni().isEmpty()) {
            if (socioRepositorio.existsByDni(socioDTO.getDni())) {
                throw new RuntimeException("El DNI " + socioDTO.getDni() + " ya está registrado.");
            }
        }

        Socio entidad = convertirEntidad(socioDTO);
        Socio socioGuardado = socioRepositorio.save(entidad);
        return convertirDTO(socioGuardado);
    }

    public String eliminarSocio(String dni){
        Socio socioExistente = socioRepositorio.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado!"));

        socioRepositorio.delete(socioExistente);
        return "Socio eliminado con exito!";
    }

    public SocioDTO editarSocioActivo(String dni) {
        Socio socioExistente = socioRepositorio.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado!"));

        socioExistente.setActivo(!socioExistente.isActivo());
        socioRepositorio.save(socioExistente);
        return convertirDTO(socioExistente);

    }

    public SocioDTO editarSocio(String dni, SocioDTO socioDTO){
        Socio socioExistente = socioRepositorio.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado!"));

        if (socioDTO.getNombre() == null || socioDTO.getNombre().isBlank() ||
                socioDTO.getApellido() == null || socioDTO.getApellido().isBlank() ||
                socioDTO.getDni() == null || socioDTO.getDni().isBlank())
        {
            return null;
        }

        socioExistente.setDni(socioDTO.getDni());
        socioExistente.setNombre(socioDTO.getNombre());
        socioExistente.setApellido(socioDTO.getApellido());

        socioRepositorio.save(socioExistente);
        return convertirDTO(socioExistente);
    }

    public SocioDTO buscarPorDni(String dni){

        if (dni == null || dni.isBlank()){
            throw new RuntimeException("Ingrese dni porfavor!");
        }

        Socio socioExistente = socioRepositorio.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado!"));

        SocioDTO socioDTO= convertirDTO(socioExistente);
        return socioDTO;
    }

    public boolean validarAccesoSocio(String dni){
        Socio socioExistente = socioRepositorio.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado!"));

        if (socioExistente.isActivo()){
            return true;
        }
        return false;
    }

    public List<SocioDTO> buscarPorNombreOApellido(String nombre, String apellido){
        List<Socio> sociosExistente = socioRepositorio.findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(nombre,apellido);

        if (sociosExistente.isEmpty()){
            return null;
        }
        return sociosExistente.stream().map(socio -> {
            SocioDTO socioDTO = new SocioDTO();
            return convertirDTO(socio);
        }).toList();
    }

    public List<SocioDTO> sociosMembresiaPorVencer(){
        LocalDate hoy = LocalDate.now();
        LocalDate limite = hoy.plusDays(7);
        List<Socio> proximos = socioRepositorio.findByFechaFinMembresiaBetween(hoy, limite);
        return proximos.stream()
                .map(this::convertirDTO)
                .toList();
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

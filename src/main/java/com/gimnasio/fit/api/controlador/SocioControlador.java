package com.gimnasio.fit.api.controlador;


import com.gimnasio.fit.api.dto.SocioDTO;
import com.gimnasio.fit.api.repositorio.SocioRepositorio;
import com.gimnasio.fit.api.servicio.SocioServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/socios")
public class SocioControlador {

    @Autowired
    private SocioServicio socioServicio;

    @GetMapping
    public ResponseEntity<List<SocioDTO>> listarTodos() {
        List<SocioDTO> lista = socioServicio.obtenerTodosLosSocios();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/activos")
    public ResponseEntity<?> listarSociosActivos(){
        return ResponseEntity.ok( socioServicio.obtenerSociosActivos());
    }

    @GetMapping("/inactivos")
    public ResponseEntity<?> listarSociosInactivos(){
        return ResponseEntity.ok( socioServicio.obtenerSociosInactivos());
    }

    @GetMapping("/{dni}")
    public ResponseEntity<?> listarPorId(@PathVariable String dni){
        SocioDTO socio = socioServicio.buscarPorDni(dni);
       if (socio != null){
           return ResponseEntity.ok(socio);
       }
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Socio no encontrado");
    }

    @DeleteMapping("/{dni}/eliminar")
    public ResponseEntity<?> eliminarSocio(@PathVariable String dni){
        String resultado = socioServicio.eliminarSocio(dni);

        if (resultado.contains("error")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay un socio con ese dni!");
        }

        return ResponseEntity.ok(resultado);
    }

    @PostMapping
    public ResponseEntity<?> guardarSocio(@Valid @RequestBody SocioDTO nuevoSocio){
        SocioDTO socioDTO = socioServicio.guardarSocio(nuevoSocio);
        if (!nuevoSocio.getDni().isEmpty()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(socioDTO);
        } else if (socioDTO== null){
            return ResponseEntity.badRequest().body("Porfavor ingrese los datos del socio!");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ingrese un dni porfavor");
    }

    @PatchMapping("/editar/{dni}")
    public ResponseEntity<?> editarSocio(@PathVariable String dni, @RequestBody SocioDTO socioDTO){
        SocioDTO resultado = socioServicio.editarSocio(dni, socioDTO);
        if (resultado==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Socio no encontrado o no ingreso los datos del socio correctamente!");
        }
        return ResponseEntity.ok(resultado);
    }

    @PatchMapping("/editarEstado/{dni}")
    public ResponseEntity<?> editarSocioAcIn(@PathVariable String dni){
       SocioDTO resultado = socioServicio.editarSocioActivo(dni);
       if (resultado == null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro socio con el dni ingresado");
       }
       return ResponseEntity.ok(resultado);
    }

//    @GetMapping("/vencidos")
//    public ResponseEntity<?> sociosVencidos(){
//        List<SocioDTO> socios = socioServicio.sociosVencidos();
//        if (!socios.isEmpty()) {
//            return ResponseEntity.ok(socios);
//        }
//     return ResponseEntity.status(HttpStatus.CONFLICT).body("No se encontraron socios con la matricula vencida");
//    }

//    @GetMapping("/buscar/{nombre}")
//    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombre){
//        List<SocioDTO> socios = socioServicio.buscarSocios(nombre);
//        if (!socios.isEmpty()) {
//            return ResponseEntity.ok(socios);
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron socios con esos caracteres");
//    }

//    @GetMapping("/buscar")
//    public ResponseEntity<?> buscarPorNomApe(
//            @RequestParam(required = false) String nombre,
//            @RequestParam(required = false) String apellido)
//    {
//        List<SocioDTO> socios = socioServicio.buscarSociosPorNomApe(nombre, apellido);
//        if (!socios.isEmpty()) {
//            return ResponseEntity.ok(socios);
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron socios con esos caracteres");
//    }
//    @GetMapping("/{dni}/acceso")
//    public ResponseEntity<?> tieneAcceso(@PathVariable String dni){
//        return ResponseEntity.ok(socioServicio.tieneAcceso(dni));
//    }
//
//    @PatchMapping("/{dni}/actualizar")
//    public ResponseEntity<?> actualizarFechaMembresia(@PathVariable String dni, @RequestBody LocalDate nuevaFecha){
//        return ResponseEntity.ok(socioServicio.editarMembresia(dni, nuevaFecha));
//    }
}

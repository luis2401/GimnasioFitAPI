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
    private SocioRepositorio socioRepositorio;

    @Autowired
    private SocioServicio socioServicio;


    @GetMapping
    public ResponseEntity<List<SocioDTO>> listarTodos() {
        List<SocioDTO> lista = socioServicio.obtenerTodosLosSocios();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{dni}")
    public ResponseEntity<?> listarPorId(@PathVariable String dni){
        SocioDTO socio = socioServicio.buscarPorDni(dni);
       if (socio != null){
           return ResponseEntity.ok(socio);
       }
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Socio no encontrado");
    }

//    @GetMapping("/{dni}/acceso")
//    public ResponseEntity<?> tieneAcceso(@PathVariable String dni){
//        return ResponseEntity.ok(socioServicio.tieneAcceso(dni));
//    }
//
//    @PatchMapping("/{dni}/actualizar")
//    public ResponseEntity<?> actualizarFechaMembresia(@PathVariable String dni, @RequestBody LocalDate nuevaFecha){
//        return ResponseEntity.ok(socioServicio.editarMembresia(dni, nuevaFecha));
//    }

    @DeleteMapping("/{dni}/eliminar")
    public ResponseEntity<?> eliminarSocio(@PathVariable String dni){
        return ResponseEntity.ok(socioServicio.eliminarSocio(dni));
    }

    @PostMapping
    public ResponseEntity<?> guardarSocio(@Valid @RequestBody SocioDTO nuevoSocio){

        if (!nuevoSocio.getDni().isEmpty()) {
            SocioDTO socioDTO = socioServicio.guardarSocio(nuevoSocio);
            return ResponseEntity.status(HttpStatus.CREATED).body(socioDTO);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ingrese un dni porfavorrrr");
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
}

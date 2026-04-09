package com.gimnasio.fit.api.controlador;


import com.gimnasio.fit.api.dto.ClaseDTO;
import com.gimnasio.fit.api.repositorio.ClaseRepositorio;
import com.gimnasio.fit.api.servicio.ClaseServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clase")
public class ClaseControlador {

    @Autowired
    private ClaseServicio claseServicio;

    @Autowired
    private ClaseRepositorio claseRepositorio;

    @GetMapping
    public ResponseEntity<?> listarClase() {
        return ResponseEntity.ok(claseServicio.listarClase());

    }

    @PostMapping("/{nombre}")
    public ResponseEntity<?> agregarClase(@RequestBody ClaseDTO claseDTO, @PathVariable String nombre){
        return ResponseEntity.ok(claseServicio.agregarClase(claseDTO,nombre));
    }

    @PatchMapping("/{dni}/{nombreClase}")
    public ResponseEntity<?> agregarSocioClase(@PathVariable String nombreClase, @PathVariable String dni){
        return ResponseEntity.ok(claseServicio.agregarSocioClase(dni,nombreClase));
    }

}

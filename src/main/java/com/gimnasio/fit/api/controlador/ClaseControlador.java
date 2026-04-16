package com.gimnasio.fit.api.controlador;

import com.gimnasio.fit.api.dto.ClaseDTO;
import com.gimnasio.fit.api.servicio.ClaseServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clase")
public class ClaseControlador {

    @Autowired
    private ClaseServicio claseServicio;

    @GetMapping
    public ResponseEntity<?> listarClase() {
        return ResponseEntity.ok(claseServicio.listarClase());

    }

    @PostMapping("/{nombre}")
    public ResponseEntity<?> agregarClase(@RequestBody ClaseDTO claseDTO, @PathVariable String nombre){
        return ResponseEntity.ok(claseServicio.agregarClase(claseDTO,nombre));
    }

    @PatchMapping("/{dni}/{id}")
    public ResponseEntity<?> agregarSocioClase(@PathVariable Integer id, @PathVariable String dni){
        return ResponseEntity.ok(claseServicio.agregarSocioClase(dni,id));
    }

    @PatchMapping("/editar/{id}")
    public ResponseEntity<?> editarClase(@PathVariable Integer id, @RequestBody ClaseDTO claseDTO){
        return ResponseEntity.ok(claseServicio.editarClase(id, claseDTO));
    }

    @GetMapping("/contar/{id}")
    public ResponseEntity<?> contarSociosClase(@PathVariable Integer id){
        return ResponseEntity.ok(claseServicio.contarSocioInscritos(id));
    }

    @DeleteMapping("/eliminarSocio/{dni}/{id}")
    public ResponseEntity<?> eliminarSocioClase(@PathVariable String dni, @PathVariable Integer id){
        return ResponseEntity.ok(claseServicio.quitarSocioClase(dni, id));
    }

}

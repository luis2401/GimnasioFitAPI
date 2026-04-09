package com.gimnasio.fit.api.controlador;

import com.gimnasio.fit.api.dto.InstructorDTO;
import com.gimnasio.fit.api.repositorio.InstructorRepositorio;
import com.gimnasio.fit.api.servicio.InstructorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/instructor")
public class InstructorControlador {

    @Autowired
    private InstructorServicio instructorServicio;

    @Autowired
    private InstructorRepositorio instructorRepositorio;

    @GetMapping
    public ResponseEntity<?> listarInstructores(){
      return ResponseEntity.ok(instructorServicio.listarInstructor());
    }

    @PostMapping
    public ResponseEntity<?> agregarInstructor(@RequestBody InstructorDTO instructorDTO){
        return ResponseEntity.ok(instructorServicio.agregarInstructor(instructorDTO));
    }
}

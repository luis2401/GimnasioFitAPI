package com.gimnasio.fit.api.controlador;

import com.gimnasio.fit.api.dto.InstructorDTO;
import com.gimnasio.fit.api.servicio.InstructorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/instructor")
public class InstructorControlador {

    @Autowired
    private InstructorServicio instructorServicio;

    @GetMapping
    public ResponseEntity<?> listarInstructores(){
      return ResponseEntity.ok(instructorServicio.listarInstructor());
    }

    @PostMapping
    public ResponseEntity<?> agregarInstructor(@RequestBody InstructorDTO instructorDTO){
        return ResponseEntity.ok(instructorServicio.agregarInstructor(instructorDTO));
    }

    @PatchMapping("/editar/{nombre}")
    public ResponseEntity<?> editarInstructor(@PathVariable String nombre, @RequestBody InstructorDTO instructorDTO){
        InstructorDTO resultado = instructorServicio.editarInstructor(nombre,instructorDTO);
        if (resultado == null){
            return ResponseEntity.badRequest().body("Porfavor ingrese los datos del instructor que desea editar correctamente");
        }
        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/eliminar/porNombre/{nombre}")
    public ResponseEntity<?> eliminarInstructorNom(@PathVariable String nombre){
        return ResponseEntity.ok(instructorServicio.eliminarInsNom(nombre));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarInstructor(@PathVariable Integer id){
       return ResponseEntity.ok( instructorServicio.eliminarIns(id));
    }

}

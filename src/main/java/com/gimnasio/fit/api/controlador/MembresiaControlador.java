package com.gimnasio.fit.api.controlador;


import com.gimnasio.fit.api.dto.MembresiaDTO;
import com.gimnasio.fit.api.servicio.MembresiaServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/membresia")
public class MembresiaControlador {

    @Autowired
    private MembresiaServicio membresiaServicio;

    @GetMapping
    public ResponseEntity<?> listarMembresia(){
        return ResponseEntity.ok(membresiaServicio.listar());
    }

    @PostMapping("/crear")
    public ResponseEntity<?> asignarMembresia(@Valid @RequestBody MembresiaDTO membresiaDTO, @RequestParam String dni){
        return ResponseEntity.ok(membresiaServicio.crearMembresia(membresiaDTO,dni));
    }
}

package com.gimnasio.fit.api.controlador;

import com.gimnasio.fit.api.dto.PagoDTO;
import com.gimnasio.fit.api.servicio.PagoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagos")
public class PagoControlador {

    @Autowired
    private PagoServicio pagoServicio;

    @GetMapping
    public ResponseEntity<?> listarPagos(){
        return ResponseEntity.ok(pagoServicio.listarPagos());
    }

    @PostMapping("/crear/{tipoPlan}/{dni}/{metodoPago}")
    public ResponseEntity<PagoDTO> guardarPago(@PathVariable String tipoPlan, @PathVariable String dni, @PathVariable String metodoPago){
       return ResponseEntity.ok(pagoServicio.guardarPago(tipoPlan,dni, metodoPago));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPago(@PathVariable Integer id){
        return ResponseEntity.ok(pagoServicio.eliminarPago(id));
    }

}

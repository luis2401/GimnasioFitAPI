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

//    @PostMapping("/crear")
//    public ResponseEntity<PagoDTO> guardarPago(@RequestBody PagoDTO pagoDTO, @RequestParam String dni){
//       return ResponseEntity.ok(pagoServicio.guardarPago(pagoDTO, dni));
//    }

}

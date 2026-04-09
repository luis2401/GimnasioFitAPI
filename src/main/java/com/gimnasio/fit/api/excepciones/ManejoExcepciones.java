package com.gimnasio.fit.api.excepciones;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


//CODIGO NO IMPLEMENTADO
@RestController
public class ManejoExcepciones {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> manejoExcepciones(RuntimeException ex){
        Map<String, Object> cuerpo = new HashMap<>();
        cuerpo.put("timestamp", LocalDateTime.now());
        cuerpo.put("mensaje", ex.getMessage());
        cuerpo.put("estado", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(cuerpo, HttpStatus.BAD_REQUEST);
    }
}

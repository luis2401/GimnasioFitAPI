package com.gimnasio.fit.api.servicio;

import com.gimnasio.fit.api.dto.PagoDTO;
import com.gimnasio.fit.api.dto.SocioDTO;
import com.gimnasio.fit.api.modelo.Membresia;
import com.gimnasio.fit.api.modelo.Pago;
import com.gimnasio.fit.api.modelo.Socio;
import com.gimnasio.fit.api.repositorio.PagoRepositorio;
import com.gimnasio.fit.api.repositorio.SocioRepositorio;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PagoServicio {

    @Autowired
    private PagoRepositorio pagoRepositorio;


    public Double calcularMontoSegunPlan(String tipoPlan) {
        int meses = obtenerMeses(tipoPlan);
        return meses * 100.0;
    }

    private int obtenerMeses(String tipoPlan) {
        switch (tipoPlan.toUpperCase()) {
            case "UNO": return 1;
            case "DOS": return 2;
            case "TRES": return 3;
            case "CUATRO": return 4;
            case "CINCO": return 5;
            case "SEIS": return 6;
            case "SIETE": return 7;
            case "OCHO": return 8;
            case "NUEVE": return 9;
            case "DIEZ": return 10;
            case "ONCE": return 11;
            case "DOCE": return 12;
            default: return 1;
        }
    }

    public PagoDTO guardarPago(Membresia membresia, Socio socio){
        Pago pago = new Pago();

        pago.setFechaPago(LocalDate.now());
        pago.setMonto(calcularMontoSegunPlan(membresia.getTipoPlan()));
        pago.setMetodo("efectivo");
        pago.setMembresia(membresia);
        pago.setSocio(socio);

        pagoRepositorio.save(pago);
        return convertirDTO(pago);
    }

    public PagoDTO convertirDTO(Pago pago){
        PagoDTO dto = new PagoDTO();
        dto.setFechaPago(pago.getFechaPago());
        dto.setMonto(pago.getMonto());
        dto.setMetodo(pago.getMetodo());
        dto.setDniSocio(pago.getSocio().getDni());
        dto.setIdMembresia(pago.getMembresia().getIdMembresia());

        return dto;
    }

    public List<PagoDTO> listarPagos(){
        return pagoRepositorio.findAll().stream().map(pago -> convertirDTO(pago)).toList();
    }
}

package com.gimnasio.fit.api.dto;


import com.gimnasio.fit.api.modelo.Socio;

import java.time.LocalDate;


public class PagoDTO {
    private LocalDate fechaPago;
    private Double monto;
    private String metodo;
    private Integer idMembresia;
    private Integer idSocio;

    public PagoDTO() {
    }

    public PagoDTO(LocalDate fechaPago, Double monto, String metodo, Integer idMembresia, Integer idSocio) {
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.metodo = metodo;
        this.idMembresia = idMembresia;
        this.idSocio = idSocio;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public Integer getIdMembresia() {
        return idMembresia;
    }

    public void setIdMembresia(Integer idMembresia) {
        this.idMembresia = idMembresia;
    }

    public Integer getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(Integer idSocio) {
        this.idSocio = idSocio;
    }
}
package com.gimnasio.fit.api.dto;


import com.gimnasio.fit.api.modelo.Socio;

import java.time.LocalDate;


public class PagoDTO {
    private Integer idPago;
    private LocalDate fechaPago;
    private Double monto;
    private String metodo;
    private Integer idMembresia;
    private String dniSocio;
    private LocalDate fechaIni;
    private LocalDate fechaFin;

    public PagoDTO() {
    }

    public PagoDTO(Integer idPago, LocalDate fechaPago, Double monto, String metodo, Integer idMembresia, String dniSocio, LocalDate fechaIni, LocalDate fechaFin) {
        this.idPago = idPago;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.metodo = metodo;
        this.idMembresia = idMembresia;
        this.dniSocio = dniSocio;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
    }

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
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

    public String getDniSocio() {
        return dniSocio;
    }

    public void setDniSocio(String dniSocio) {
        this.dniSocio = dniSocio;
    }

    public LocalDate getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(LocalDate fechaIni) {
        this.fechaIni = fechaIni;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
}
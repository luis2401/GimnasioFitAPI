package com.gimnasio.fit.api.dto;

import com.gimnasio.fit.api.modelo.Socio;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class MembresiaDTO {

        private Integer idMembresia;

        private String tipoPlan;

        private LocalDate fechaInicio;

        private LocalDate fechaFin;

        private boolean estado;

        private String dniSocio;

        private String nombreape;

    public MembresiaDTO() {
    }

    public MembresiaDTO(Integer idMembresia ,String tipoPlan, LocalDate fechaInicio, LocalDate fechaFin, boolean estado, String dniSocio, String nombreape) {
        this.idMembresia=idMembresia;
        this.tipoPlan = tipoPlan;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.dniSocio = dniSocio;
        this.nombreape = nombreape;
    }

    public Integer getIdMembresia() {
        return idMembresia;
    }

    public void setIdMembresia(Integer idMembresia) {
        this.idMembresia = idMembresia;
    }

    public String getTipoPlan() {
        return tipoPlan;
    }

    public void setTipoPlan(String tipoPlan) {
        this.tipoPlan = tipoPlan;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getDniSocio() {
        return dniSocio;
    }

    public void setDniSocio(String dniSocio) {
        this.dniSocio = dniSocio;
    }

    public String getNombreape() {
        return nombreape;
    }

    public void setNombreape(String nombreape) {
        this.nombreape = nombreape;
    }
}

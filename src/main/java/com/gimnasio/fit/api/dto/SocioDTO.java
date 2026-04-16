package com.gimnasio.fit.api.dto;

import java.time.LocalDate;

public class SocioDTO {
    private String dni;
    private String nombre;
    private String apellido;
    private LocalDate fechaFinMembresia;
    private boolean activo;

    public SocioDTO() {

    }

    public SocioDTO(String dni, String nombre, String apellido, LocalDate fechaFinMembresia) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaFinMembresia = fechaFinMembresia;
    }

    public LocalDate getFechaFinMembresia() {
        return fechaFinMembresia;
    }

    public void setFechaFinMembresia(LocalDate fechaFinMembresia) {
        this.fechaFinMembresia = fechaFinMembresia;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}

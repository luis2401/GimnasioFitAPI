package com.gimnasio.fit.api.dto;


import java.util.List;

public class ClaseDTO {

    private Integer idClase;

    private String nombreClase;

    private String horarioClase;

    private Integer idInstructor;

    private List<SocioDTO> socios;

    public ClaseDTO() {
    }

    public ClaseDTO(Integer idClase, String nombreClase, String horarioClase, Integer idInstructor, List<SocioDTO> socios) {
        this.idClase = idClase;
        this.nombreClase = nombreClase;
        this.horarioClase = horarioClase;
        this.idInstructor = idInstructor;
        this.socios = socios;
    }

    public Integer getIdClase() {
        return idClase;
    }

    public void setIdClase(Integer idClase) {
        this.idClase = idClase;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public String getHorarioClase() {
        return horarioClase;
    }

    public void setHorarioClase(String horarioClase) {
        this.horarioClase = horarioClase;
    }

    public Integer getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(Integer idInstructor) {
        this.idInstructor = idInstructor;
    }

    public List<SocioDTO> getSocios() {
        return socios;
    }

    public void setSocios(List<SocioDTO> socios) {
        this.socios = socios;
    }
}

package com.gimnasio.fit.api.dto;

import com.gimnasio.fit.api.modelo.Clase;

import java.util.List;

public class InstructorDTO {

    private Integer idInstructor;

    private String nombreInstructor;

    private String apellidoInstructor;

    private String especialidadInstructor;

    private String telefono;

    private String email;

    private List<ClaseDTO> claseDTO;


    public InstructorDTO() {
    }

    public InstructorDTO(Integer idInstructor, String nombreInstructor, String apellidoInstructor, String especialidadInstructor, String telefono, String email, List<ClaseDTO> claseDTO) {
        this.idInstructor = idInstructor;
        this.nombreInstructor = nombreInstructor;
        this.apellidoInstructor = apellidoInstructor;
        this.especialidadInstructor = especialidadInstructor;
        this.telefono = telefono;
        this.email = email;
        this.claseDTO = claseDTO;
    }

    public List<ClaseDTO> getClaseDTO() {
        return claseDTO;
    }

    public void setClaseDTO(List<ClaseDTO> claseDTO) {
        this.claseDTO = claseDTO;
    }

    public Integer getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(Integer idInstructor) {
        this.idInstructor = idInstructor;
    }

    public String getNombreInstructor() {
        return nombreInstructor;
    }

    public void setNombreInstructor(String nombreInstructor) {
        this.nombreInstructor = nombreInstructor;
    }

    public String getApellidoInstructor() {
        return apellidoInstructor;
    }

    public void setApellidoInstructor(String apellidoInstructor) {
        this.apellidoInstructor = apellidoInstructor;
    }

    public String getEspecialidadInstructor() {
        return especialidadInstructor;
    }

    public void setEspecialidadInstructor(String especialidadInstructor) {
        this.especialidadInstructor = especialidadInstructor;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

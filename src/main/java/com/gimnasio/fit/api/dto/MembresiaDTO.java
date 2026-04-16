package com.gimnasio.fit.api.dto;

public class MembresiaDTO {

        private Integer idMembresia;

        private String tipoPlan;

        private Integer duracionDias;

        private boolean estado;


    public MembresiaDTO() {
    }

    public MembresiaDTO(Integer idMembresia, String tipoPlan, Integer duracionDias, boolean estado) {
        this.idMembresia = idMembresia;
        this.tipoPlan = tipoPlan;
        this.duracionDias = duracionDias;
        this.estado = estado;
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

    public Integer getDuracionDias() {
        return duracionDias;
    }

    public void setDuracionDias(Integer duracionDias) {
        this.duracionDias = duracionDias;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}

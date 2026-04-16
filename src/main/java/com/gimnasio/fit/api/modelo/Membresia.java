package com.gimnasio.fit.api.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "membresia")
public class Membresia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMembresia;

    @NotBlank(message = "El plan del socio no puede estar vacio")
    private String tipoPlan;

    private Integer duracionDias;

    private boolean estado;

}

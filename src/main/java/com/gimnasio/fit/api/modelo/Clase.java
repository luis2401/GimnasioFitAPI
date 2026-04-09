package com.gimnasio.fit.api.modelo;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clase")
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClase;

    @NotBlank(message = "El nombre de la clase no puede estar vacio")
    private String nombreClase;

    @NotBlank(message = "El horario de la clase no puede estar vacio")
    private String horarioClase;

    @NotNull(message = "Se necesita asignar un instructor")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", referencedColumnName = "idInstructor", nullable = false)
    private Instructor instructor;

    @ManyToMany(mappedBy = "clases")
    private List<Socio> socios;


}
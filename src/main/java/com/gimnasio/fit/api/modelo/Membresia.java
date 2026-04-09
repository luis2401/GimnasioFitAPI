package com.gimnasio.fit.api.modelo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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

    @NotNull(message = "La fecha de inicio del socio no puede estar vacia")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin del socio no puede estar vacia")
    private LocalDate fechaFin;

    private boolean estado;

    @OneToOne(mappedBy = "membresia")
    private Pago pago;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "socio_id", referencedColumnName = "id", nullable = false)
    private Socio socio;


}

package com.gimnasio.fit.api.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pago;

    @NotNull(message = "Fecha pago no puede estar vacio")
    private LocalDate fechaPago;

    @NotNull(message = "Monto no puede estar vacio")
    private Double monto;

    @NotBlank(message = "Metodo de pago nno puede estar vacio")
    private String metodo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membresia_id", referencedColumnName = "idMembresia", nullable = false)
    private Membresia membresia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "socio_id", referencedColumnName = "id", nullable = false)
    private Socio socio;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

}

package com.gimnasio.fit.api.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "socios")
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El dni no puede estar vacio")
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener exactamente 8 números")
    private String dni;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(min = 3, max = 30, message = "El nombre debe tener entre 3 y 30 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacio")
    @Size(min = 3, max = 30, message = "El nombre debe tener entre 3 y 30 caracteres")
    private String apellido;

    private boolean activo;

    @JsonIgnore
    @OneToMany(mappedBy = "socio")
    private List<Membresia> membresias;

    @JsonIgnore
    @OneToMany(mappedBy = "socio")
    private List<Pago> pagos;

    @ManyToMany
    @JoinTable(
            name = "socio_clase",
            joinColumns = @JoinColumn(name = "socio_id"),
            inverseJoinColumns = @JoinColumn(name = "clase_id")
    )
    private List<Clase> clases;




}

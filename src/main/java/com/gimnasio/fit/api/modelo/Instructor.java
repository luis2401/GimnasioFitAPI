package com.gimnasio.fit.api.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInstructor;

    @NotBlank(message = "El nombre de instructor no puede estar vacio")
    private String nombreInstructor;

    @NotBlank(message = "El apellido de instructor no puede estar vacio")
    private String apellidoInstructor;

    private String especialidadInstructor;

    @Pattern(regexp = "^[9]\\d{8}$", message = "El numero de telefno debe tener exactamente 9 números")
    private String telefono;

    @Email
    private String email;

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY)
    private List<Clase> clases;


}
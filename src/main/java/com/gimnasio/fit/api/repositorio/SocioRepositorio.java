package com.gimnasio.fit.api.repositorio;

import com.gimnasio.fit.api.modelo.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SocioRepositorio extends JpaRepository<Socio, Integer> {

    boolean existsByDni(String dni);

    Optional<Socio> findByDni(String dni);

    List<Socio> findByNombreContainingIgnoreCase(String nombre);

    List<Socio> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(String nombre, String apellido);


    List<Socio> findByFechaFinMembresiaBetween(LocalDate fechaFinMembresiaAfter, LocalDate fechaFinMembresiaBefore);
}

package com.gimnasio.fit.api.repositorio;

import com.gimnasio.fit.api.modelo.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SocioRepositorio extends JpaRepository<Socio, Integer>{

    boolean existsByDni(String dni);

    Socio findByDni(String dni);

    List<Socio> findByNombreContainingIgnoreCase(String nombre);

    List<Socio> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(String nombre, String apellido);

}

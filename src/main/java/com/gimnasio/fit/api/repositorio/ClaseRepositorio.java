package com.gimnasio.fit.api.repositorio;

import com.gimnasio.fit.api.modelo.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaseRepositorio extends JpaRepository<Clase, Integer> {

    Clase findBynombreClase(String nombreclase);

}

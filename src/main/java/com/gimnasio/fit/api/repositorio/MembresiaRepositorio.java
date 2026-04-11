package com.gimnasio.fit.api.repositorio;

import com.gimnasio.fit.api.modelo.Membresia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembresiaRepositorio extends JpaRepository<Membresia, Integer> {

    Membresia findBytipoPlan(String tipoPlan);

}

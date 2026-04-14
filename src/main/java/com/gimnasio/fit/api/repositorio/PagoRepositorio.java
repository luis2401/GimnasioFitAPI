package com.gimnasio.fit.api.repositorio;

import com.gimnasio.fit.api.modelo.Pago;
import com.gimnasio.fit.api.modelo.Socio;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PagoRepositorio extends JpaRepository<Pago, Integer> {

    List<Pago> findBySocio(Socio socio);

}

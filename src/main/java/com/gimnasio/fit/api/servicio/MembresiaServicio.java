package com.gimnasio.fit.api.servicio;

import com.gimnasio.fit.api.dto.MembresiaDTO;
import com.gimnasio.fit.api.dto.SocioDTO;
import com.gimnasio.fit.api.modelo.Membresia;
import com.gimnasio.fit.api.modelo.Pago;
import com.gimnasio.fit.api.modelo.Socio;
import com.gimnasio.fit.api.repositorio.MembresiaRepositorio;
import com.gimnasio.fit.api.repositorio.PagoRepositorio;
import com.gimnasio.fit.api.repositorio.SocioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MembresiaServicio {

    @Autowired
    private MembresiaRepositorio membresiaRepositorio;

    public int obtenerMesesSegunPlan(String tipoPlan) {
        switch (tipoPlan.trim().toUpperCase()) {
            case "UNO":    return 1;
            case "DOS":    return 2;
            case "TRES":   return 3;
            case "DOCE":   return 12;
            default:       return 1; // Un valor seguro por defecto
        }
    }

//    public Membresia convertirEntidad(MembresiaDTO membresiaDTO){
//        Membresia membresia = new Membresia();
//        membresia.setTipoPlan(membresiaDTO.getTipoPlan());
//        membresia.setFechaInicio(LocalDate.now());
//        calcularFechaFinSegunPlan(membresia, membresiaDTO.getTipoPlan());
//        membresia.setEstado(true);
//
//        return membresia;
//    }

    public MembresiaDTO convertirDTO(Membresia membresia){
        MembresiaDTO membresiaDTO = new MembresiaDTO();
        membresiaDTO.setIdMembresia(membresia.getIdMembresia());
        membresiaDTO.setTipoPlan(membresia.getTipoPlan());
        membresiaDTO.setDuracionDias(membresia.getDuracionDias());
        membresiaDTO.setEstado(membresia.isEstado());

        return membresiaDTO;
    }

    public MembresiaDTO crearMembresia(MembresiaDTO membresiaDTO){
        Membresia membresia = new Membresia();
        membresia.setTipoPlan(membresiaDTO.getTipoPlan());
        membresia.setDuracionDias(membresiaDTO.getDuracionDias());
        membresia.setEstado(membresiaDTO.isEstado());

        membresiaRepositorio.save(membresia);

        return convertirDTO(membresia);
    }

    public List<MembresiaDTO> listar() {
        List<Membresia> lista = membresiaRepositorio.findAll();

        return lista.stream()
                .map(membresia -> {
                    return convertirDTO(membresia);
                })
                .toList();
    }
}

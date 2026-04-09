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

    @Autowired
    private SocioRepositorio socioRepositorio;

    @Autowired
    private PagoRepositorio pagoRepositorio;

    @Autowired
    private PagoServicio pagoServicio;


    public void calcularFechaFinSegunPlan(Membresia membresia, String tipoPlan) {
        int meses = obtenerMesesSegunPlan(tipoPlan);
        membresia.setFechaFin(LocalDate.now().plusMonths(meses));
    }


    public int obtenerMesesSegunPlan(String tipoPlan) {
        switch (tipoPlan.trim().toUpperCase()) {
            case "UNO":    return 1;
            case "DOS":    return 2;
            case "TRES":   return 3;
            case "DOCE":   return 12;
            default:       return 1; // Un valor seguro por defecto
        }
    }

    public Membresia convertirEntidad(MembresiaDTO membresiaDTO){
        Membresia membresia = new Membresia();
        membresia.setTipoPlan(membresiaDTO.getTipoPlan());
        membresia.setFechaInicio(LocalDate.now());
        calcularFechaFinSegunPlan(membresia, membresiaDTO.getTipoPlan());
        membresia.setEstado(true);

        return membresia;
    }

    public MembresiaDTO convertirDTO(Membresia membresia){
        MembresiaDTO membresiaDTO = new MembresiaDTO();
        membresiaDTO.setIdMembresia(membresia.getIdMembresia());
        membresiaDTO.setTipoPlan(membresia.getTipoPlan());
        membresiaDTO.setFechaInicio(membresia.getFechaInicio());
        membresiaDTO.setFechaFin(membresia.getFechaFin());
        membresiaDTO.setEstado(true);

        membresiaDTO.setDniSocio(membresia.getSocio().getDni());
        membresiaDTO.setNombreape(membresia.getSocio().getNombre()+" "+membresia.getSocio().getApellido());

        return membresiaDTO;
    }

    public MembresiaDTO crearMembresia(MembresiaDTO membresiaDTO, String dni){
        Membresia membresia= convertirEntidad(membresiaDTO);

        Socio socioencontrado = socioRepositorio.findByDni(dni);
        membresia.setSocio(socioencontrado);

        membresiaRepositorio.save(membresia);
        socioRepositorio.save(socioencontrado);

        switch (membresia.getTipoPlan().toUpperCase()){
            case "UNO": membresia.setEstado(true); membresiaDTO.setEstado(true); break;
        }

        if (membresia.isEstado()){
            socioencontrado.setActivo(true);
        }

        pagoServicio.guardarPago(membresia, socioencontrado);

        socioencontrado.getMembresias().add(membresia);

        return convertirDTO(membresia);
    }

    public List<MembresiaDTO> listar(){
        List<Membresia> lista = membresiaRepositorio.findAll();

      return lista.stream().map(membresia -> {
            MembresiaDTO membresiaDTO = new MembresiaDTO();
            membresiaDTO.setIdMembresia(membresia.getIdMembresia());
            membresiaDTO.setTipoPlan(membresia.getTipoPlan());
            membresiaDTO.setFechaInicio(membresia.getFechaInicio());
            membresiaDTO.setFechaFin(membresia.getFechaFin());
            membresiaDTO.setEstado(membresia.isEstado());
            membresiaDTO.setDniSocio(membresia.getSocio().getDni());
            membresiaDTO.setNombreape(membresia.getSocio().getNombre()+" "+membresia.getSocio().getApellido());
            return membresiaDTO;
        }).toList();

    }
}

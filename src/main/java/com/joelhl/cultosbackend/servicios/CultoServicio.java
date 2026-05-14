package com.joelhl.cultosbackend.servicios;

import com.joelhl.cultosbackend.entidades.Culto;
import com.joelhl.cultosbackend.repositorios.CultoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CultoServicio {

    private final CultoRepositorio cultoRepo;

    public List<Culto> obtenerTodos() {
        return cultoRepo.findAllByOrderByFechaAsc();
    }

    public List<Culto> obtenerPorMes(int anio, int mes) {
        return cultoRepo.findByAnioAndMes(anio, mes);
    }

    public Culto obtenerPorId(Long id) {
        return cultoRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Culto no encontrado"));
    }

    public Culto crear(Culto culto) {
        if (culto.getPrograma() != null) {
            culto.getPrograma().forEach(
                    item -> item.setCulto(culto));
        }
        return cultoRepo.save(culto);
    }

    public Culto actualizar(Long id, Culto datos) {
        Culto culto = obtenerPorId(id);
        culto.setTitulo(datos.getTitulo());
        culto.setFecha(datos.getFecha());
        culto.setTipo(datos.getTipo());
        culto.setEstado(datos.getEstado());
        culto.setLugar(datos.getLugar());
        culto.setDescripcion(datos.getDescripcion());
        culto.getPrograma().clear();
        if (datos.getPrograma() != null) {
            datos.getPrograma().forEach(item -> {
                item.setCulto(culto);
                culto.getPrograma().add(item);
            });
        }
        return cultoRepo.save(culto);
    }

    public void eliminar(Long id) {
        cultoRepo.deleteById(id);
    }
}
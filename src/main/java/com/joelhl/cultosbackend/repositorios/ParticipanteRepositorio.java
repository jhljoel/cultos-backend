package com.joelhl.cultosbackend.repositorios;

import com.joelhl.cultosbackend.entidades.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ParticipanteRepositorio
        extends JpaRepository<Participante, Long> {

    List<Participante> findAllByOrderByNombreAsc();

    List<Participante> findByRolOrderByNombreAsc(
            Participante.Rol rol);

    List<Participante> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(
            String nombre, String apellido);

    boolean existsByEmail(String email);
}
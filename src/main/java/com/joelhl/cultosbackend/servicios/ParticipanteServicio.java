package com.joelhl.cultosbackend.servicios;

import com.joelhl.cultosbackend.entidades.Participante;
import com.joelhl.cultosbackend.entidades.Usuario;
import com.joelhl.cultosbackend.repositorios.ParticipanteRepositorio;
import com.joelhl.cultosbackend.repositorios.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipanteServicio {

    private final ParticipanteRepositorio participanteRepo;
    private final UsuarioRepositorio usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    public List<Participante> obtenerTodos() {
        return participanteRepo.findAllByOrderByNombreAsc();
    }

    public List<Participante> obtenerPorRol(Participante.Rol rol) {
        return participanteRepo.findByRolOrderByNombreAsc(rol);
    }

    public List<Participante> buscar(String texto) {
        return participanteRepo
                .findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(
                        texto, texto);
    }

    public Participante obtenerPorId(Long id) {
        return participanteRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Participante no encontrado"));
    }

    public Participante crear(Participante participante) {
        Participante guardado = participanteRepo.save(participante);

        // Si tiene email y no existe ya como usuario, crear usuario
        if (participante.getEmail() != null
                && !participante.getEmail().isEmpty()
                && !usuarioRepo.existsByEmail(participante.getEmail())) {

            Usuario usuario = new Usuario();
            usuario.setNombre(participante.getNombre()
                    + " " + participante.getApellido());
            usuario.setEmail(participante.getEmail());
            usuario.setPassword(
                    passwordEncoder.encode("iglesia123"));
            usuario.setRol(Usuario.Rol.valueOf(
                    participante.getRol().name()));
            usuario.setTelefono(participante.getTelefono());
            usuario.setActivo(true);
            usuarioRepo.save(usuario);
        }

        return guardado;
    }

    public Participante actualizar(Long id, Participante datos) {
        Participante p = obtenerPorId(id);
        p.setNombre(datos.getNombre());
        p.setApellido(datos.getApellido());
        p.setRol(datos.getRol());
        p.setTelefono(datos.getTelefono());
        p.setEmail(datos.getEmail());
        p.setActivo(datos.isActivo());
        return participanteRepo.save(p);
    }

    public void eliminar(Long id) {
        participanteRepo.deleteById(id);
    }
}
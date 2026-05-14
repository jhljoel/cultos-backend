package com.joelhl.cultosbackend.controladores;

import com.joelhl.cultosbackend.entidades.Participante;
import com.joelhl.cultosbackend.servicios.ParticipanteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/participantes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ParticipanteControlador {

    private final ParticipanteServicio participanteServicio;

    @GetMapping
    public ResponseEntity<List<Participante>> obtenerTodos(
            @RequestParam(required = false) String rol,
            @RequestParam(required = false) String buscar) {
        if (buscar != null && !buscar.isEmpty()) {
            return ResponseEntity.ok(
                    participanteServicio.buscar(buscar));
        }
        if (rol != null && !rol.isEmpty()) {
            return ResponseEntity.ok(
                    participanteServicio.obtenerPorRol(
                            Participante.Rol.valueOf(rol)));
        }
        return ResponseEntity.ok(
                participanteServicio.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participante> obtenerPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                participanteServicio.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Participante> crear(
            @RequestBody Participante participante) {
        return ResponseEntity.ok(
                participanteServicio.crear(participante));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participante> actualizar(
            @PathVariable Long id,
            @RequestBody Participante participante) {
        return ResponseEntity.ok(
                participanteServicio.actualizar(
                        id, participante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {
        participanteServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
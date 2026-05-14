package com.joelhl.cultosbackend.controladores;

import com.joelhl.cultosbackend.entidades.Culto;
import com.joelhl.cultosbackend.entidades.ProgramaItem;
import com.joelhl.cultosbackend.repositorios.CultoRepositorio;
import com.joelhl.cultosbackend.repositorios.ProgramaItemRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProgramaItemControlador {

    private final ProgramaItemRepositorio programaItemRepositorio;
    private final CultoRepositorio cultoRepositorio;

    @GetMapping("/api/cultos/{cultoId}/programa")
    public ResponseEntity<List<ProgramaItem>> obtenerPrograma(
            @PathVariable Long cultoId) {
        return ResponseEntity.ok(
                programaItemRepositorio.findByCultoIdOrderByOrdenAsc(cultoId));
    }

    @PostMapping("/api/cultos/{cultoId}/programa")
    public ResponseEntity<ProgramaItem> agregar(
            @PathVariable Long cultoId,
            @RequestBody ProgramaItem item) {
        Culto culto = cultoRepositorio.findById(cultoId)
                .orElseThrow(() -> new RuntimeException("Culto no encontrado"));
        item.setCulto(culto);
        return ResponseEntity.ok(programaItemRepositorio.save(item));
    }

    @PutMapping("/api/programa-items/{id}")
    public ResponseEntity<ProgramaItem> actualizar(
            @PathVariable Long id,
            @RequestBody ProgramaItem itemActualizado) {
        ProgramaItem item = programaItemRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Item no encontrado"));
        item.setActividad(itemActualizado.getActividad());
        item.setResponsable(itemActualizado.getResponsable());
        item.setDuracionMinutos(itemActualizado.getDuracionMinutos());
        item.setOrden(itemActualizado.getOrden());
        return ResponseEntity.ok(programaItemRepositorio.save(item));
    }

    @DeleteMapping("/api/programa-items/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        programaItemRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
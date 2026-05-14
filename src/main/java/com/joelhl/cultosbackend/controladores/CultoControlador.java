package com.joelhl.cultosbackend.controladores;

import com.joelhl.cultosbackend.entidades.Culto;
import com.joelhl.cultosbackend.servicios.CultoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cultos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CultoControlador {

    private final CultoServicio cultoServicio;

    @GetMapping
    public ResponseEntity<List<Culto>> obtenerTodos(
            @RequestParam(required = false) Integer año,
            @RequestParam(required = false) Integer mes) {
        if (año != null && mes != null) {
            return ResponseEntity.ok(
                    cultoServicio.obtenerPorMes(año, mes));
        }
        return ResponseEntity.ok(
                cultoServicio.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Culto> obtenerPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                cultoServicio.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Culto> crear(
            @RequestBody Culto culto) {
        return ResponseEntity.ok(
                cultoServicio.crear(culto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Culto> actualizar(
            @PathVariable Long id,
            @RequestBody Culto culto) {
        return ResponseEntity.ok(
                cultoServicio.actualizar(id, culto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {
        cultoServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
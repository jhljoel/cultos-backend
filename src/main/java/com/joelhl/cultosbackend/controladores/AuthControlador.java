package com.joelhl.cultosbackend.controladores;

import com.joelhl.cultosbackend.dto.LoginRequest;
import com.joelhl.cultosbackend.dto.LoginResponse;
import com.joelhl.cultosbackend.entidades.Usuario;
import com.joelhl.cultosbackend.servicios.AuthServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthControlador {

    private final AuthServicio authServicio;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request) {
        return ResponseEntity.ok(
                authServicio.login(request));
    }

    @PostMapping("/registro")
    public ResponseEntity<Usuario> registro(
            @RequestBody Usuario usuario) {
        return ResponseEntity.ok(
                authServicio.registrar(usuario));
    }
    @PutMapping("/cambiar-password")
    public ResponseEntity<Void> cambiarPassword(
            @RequestBody java.util.Map<String, String> body) {
        authServicio.cambiarPassword(
                body.get("email"),
                body.get("passwordActual"),
                body.get("passwordNueva")
        );
        return ResponseEntity.ok().build();
    }
    @PutMapping("/resetear-password")
    public ResponseEntity<Void> resetearPassword(
            @RequestBody java.util.Map<String, String> body) {
        authServicio.resetearPassword(body.get("email"));
        return ResponseEntity.ok().build();
    }
}
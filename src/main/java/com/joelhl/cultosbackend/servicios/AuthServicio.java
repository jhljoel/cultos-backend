package com.joelhl.cultosbackend.servicios;

import com.joelhl.cultosbackend.config.JwtUtil;
import com.joelhl.cultosbackend.dto.LoginRequest;
import com.joelhl.cultosbackend.dto.LoginResponse;
import com.joelhl.cultosbackend.entidades.Usuario;
import com.joelhl.cultosbackend.repositorios.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServicio {

    private final UsuarioRepositorio usuarioRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepo
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtUtil.generarToken(
                usuario.getEmail(),
                usuario.getRol().name()
        );

        return new LoginResponse(
                token,
                usuario.getRol().name(),
                new LoginResponse.UsuarioDto(
                        usuario.getId(),
                        usuario.getNombre(),
                        usuario.getEmail()
                )
        );
    }

    public Usuario registrar(Usuario usuario) {
        if (usuarioRepo.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException(
                    "El email ya está registrado");
        }
        usuario.setPassword(
                passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepo.save(usuario);
    }
    public void cambiarPassword(String email,
                                String passwordActual, String passwordNueva) {
        Usuario usuario = usuarioRepo.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(
                passwordActual, usuario.getPassword())) {
            throw new RuntimeException("La contraseña actual es incorrecta");
        }

        usuario.setPassword(
                passwordEncoder.encode(passwordNueva));
        usuarioRepo.save(usuario);
    }
    public void resetearPassword(String email) {
        Usuario usuario = usuarioRepo.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));
        usuario.setPassword(
                passwordEncoder.encode("iglesia123"));
        usuarioRepo.save(usuario);
    }
}
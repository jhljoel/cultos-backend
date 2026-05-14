package com.joelhl.cultosbackend;

import com.joelhl.cultosbackend.entidades.Usuario;
import com.joelhl.cultosbackend.repositorios.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepositorio usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (!usuarioRepo.existsByEmail("admin@iglesia.com")) {
            Usuario admin = new Usuario();
            admin.setNombre("Administrador");
            admin.setEmail("admin@iglesia.com");
            admin.setPassword(
                    passwordEncoder.encode("admin123"));
            admin.setRol(Usuario.Rol.ADMIN);
            usuarioRepo.save(admin);
            System.out.println(
                    "✅ Admin creado: admin@iglesia.com / admin123");
        }

    }
}
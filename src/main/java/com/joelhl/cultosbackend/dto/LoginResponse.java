package com.joelhl.cultosbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String rol;
    private UsuarioDto usuario;

    @Data
    @AllArgsConstructor
    public static class UsuarioDto {
        private Long id;
        private String nombre;
        private String email;
    }
}
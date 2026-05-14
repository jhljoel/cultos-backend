package com.joelhl.cultosbackend.entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "participantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol = Rol.MIEMBRO;

    private String telefono;
    private String email;
    private boolean activo = true;

    public enum Rol {
        ADMIN, LIDER, MIEMBRO
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public String getIniciales() {
        String n = nombre != null && !nombre.isEmpty()
                ? String.valueOf(nombre.charAt(0)) : "";
        String a = apellido != null && !apellido.isEmpty()
                ? String.valueOf(apellido.charAt(0)) : "";
        return (n + a).toUpperCase();
    }
}
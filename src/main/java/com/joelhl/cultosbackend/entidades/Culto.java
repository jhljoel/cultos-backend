package com.joelhl.cultosbackend.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cultos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Culto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private String tipo = "SEMANA";

    @Column(nullable = false)
    private String estado = "PROGRAMADO";

    private String lugar;
    private String descripcion;

    @Column(name = "hora_inicio", length = 5)
    private String horaInicio;

    @OneToMany(mappedBy = "culto",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    @OrderBy("orden ASC")
    private List<ProgramaItem> programa = new ArrayList<>();
}
package com.joelhl.cultosbackend.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "programa_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String actividad;

    private String responsable;
    private int duracionMinutos = 5;
    private int orden = 0;

    @ManyToOne
    @JoinColumn(name = "culto_id", nullable = false)
    @JsonIgnore
    private Culto culto;
}
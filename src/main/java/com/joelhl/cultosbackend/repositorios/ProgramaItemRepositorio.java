package com.joelhl.cultosbackend.repositorios;

import com.joelhl.cultosbackend.entidades.ProgramaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProgramaItemRepositorio
        extends JpaRepository<ProgramaItem, Long> {
    List<ProgramaItem> findByCultoIdOrderByOrdenAsc(Long cultoId);
}
package com.joelhl.cultosbackend.repositorios;

import com.joelhl.cultosbackend.entidades.Culto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CultoRepositorio
        extends JpaRepository<Culto, Long> {

    List<Culto> findAllByOrderByFechaAsc();

    @Query("SELECT c FROM Culto c WHERE " +
            "YEAR(c.fecha) = :anio AND MONTH(c.fecha) = :mes " +
            "ORDER BY c.fecha ASC")
    List<Culto> findByAnioAndMes(
            @Param("anio") int anio,
            @Param("mes") int mes
    );
}
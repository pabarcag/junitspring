package com.pato.pruebasunitariasspring.repository;

import com.pato.pruebasunitariasspring.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long> {

    Optional<Empleado> findByEmail(String email);
}

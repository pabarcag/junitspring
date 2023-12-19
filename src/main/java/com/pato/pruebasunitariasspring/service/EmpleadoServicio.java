package com.pato.pruebasunitariasspring.service;

import com.pato.pruebasunitariasspring.models.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoServicio {

    Empleado saveEmpleado(Empleado empleado);
    List<Empleado> getAllEmpleados();

    Optional<Empleado> getEmpleadoById(Long id);

    Empleado updateEmpleado(Empleado empleadoActualizado);

    void deleteEmpleado(Long id);



}

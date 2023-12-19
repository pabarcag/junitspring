package com.pato.pruebasunitariasspring.service.impl;


import com.pato.pruebasunitariasspring.exceptions.ResourceNotFoundException;
import com.pato.pruebasunitariasspring.models.Empleado;
import com.pato.pruebasunitariasspring.repository.EmpleadoRepositorio;
import com.pato.pruebasunitariasspring.service.EmpleadoServicio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoServicio {


    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;


    @Override
    @Transactional
    public Empleado saveEmpleado(Empleado empleado) {


        empleadoRepositorio.findByEmail(empleado.getEmail())
                .ifPresent(presentEmpleado -> {
                    throw new ResourceNotFoundException("El empleado con el email: " + presentEmpleado.getEmail() + " ya existe");
                });

        return empleadoRepositorio.save(empleado);
    }

    @Override
    public List<Empleado> getAllEmpleados() {
        return empleadoRepositorio.findAll();
    }

    @Override
    public Empleado updateEmpleado(Empleado empleadoActualizado) {

        return empleadoRepositorio.save(empleadoActualizado);
    }


    @Override
    public Optional<Empleado> getEmpleadoById(Long id) {
        return empleadoRepositorio.findById(id);
    }

    @Override
    public void deleteEmpleado(Long id) {

        empleadoRepositorio.deleteById(id);

    }


}

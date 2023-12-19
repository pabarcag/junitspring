package com.pato.pruebasunitariasspring.controller;


import com.pato.pruebasunitariasspring.models.Empleado;
import com.pato.pruebasunitariasspring.service.impl.EmpleadoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoServiceImpl empleadoService;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empleado guardarEmpleado(
            @RequestBody
            Empleado empleado
    ){
        return empleadoService.saveEmpleado(empleado);
    }

    @GetMapping
    public List<Empleado> listarEmpelados(){
        return empleadoService.getAllEmpleados();
    }





    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(
            @PathVariable("id") Long id
    ){
        return empleadoService.getEmpleadoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(
            @PathVariable("id") Long id,
            @RequestBody Empleado empleado
    ){

        Optional<Empleado> empleadoBaseDeDatos = empleadoService.getEmpleadoById(id);

        if(!empleadoBaseDeDatos.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Empleado empleadoExistente = empleadoBaseDeDatos.get();

        empleadoExistente.setNombre(empleado.getNombre());
        empleadoExistente.setApellido(empleado.getApellido());
        empleadoExistente.setEmail(empleado.getEmail());

        return ResponseEntity.ok(empleadoService.saveEmpleado(empleadoExistente));


    }




    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEMpleado(
            @PathVariable("id") Long id
    ){

        empleadoService.deleteEmpleado(id);
        return new ResponseEntity<String>("Elemento con el id: " + id + " eliminado exitosamente", HttpStatus.OK);
    }





}

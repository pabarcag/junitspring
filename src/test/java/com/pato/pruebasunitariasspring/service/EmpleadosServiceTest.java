package com.pato.pruebasunitariasspring.service;


import com.pato.pruebasunitariasspring.exceptions.ResourceNotFoundException;
import com.pato.pruebasunitariasspring.models.Empleado;
import com.pato.pruebasunitariasspring.repository.EmpleadoRepositorio;
import com.pato.pruebasunitariasspring.service.impl.EmpleadoServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmpleadosServiceTest {

    @Mock
    private EmpleadoRepositorio empleadoRepositorio;

    @InjectMocks
    private EmpleadoServiceImpl empleadoService;
    private Empleado empleado;


    @BeforeEach
    void setup() {
        empleado = Empleado.builder()
                .nombre("Fernando")
                .apellido("Fonseca")
                .email("fer@gmail.com")
                .build();
    }

    @DisplayName("probar guardar empleado")
    @Test
    public void testSaveEmpleado() {

        // given
        BDDMockito.given(empleadoRepositorio.findByEmail(empleado.getEmail()))
                .willReturn(Optional.empty());
        BDDMockito.given(empleadoRepositorio.save(empleado)).willReturn(empleado);

        //when
        Empleado empleadoGuardado = empleadoService.saveEmpleado(empleado);

        //then
        Assertions.assertThat(empleadoGuardado).isNotNull();
    }

    //con throw
    @DisplayName("probar guardar empleado con throw exception")
    @Test
    public void testSaveEmpleadoConThrowException() {

        // given
        BDDMockito.given(empleadoRepositorio.findByEmail(empleado.getEmail()))
                .willReturn(Optional.of(empleado));

        //when
        assertThrows(ResourceNotFoundException.class, () -> {
            empleadoService.saveEmpleado(empleado);
        });


        //then
        BDDMockito.verify(empleadoRepositorio, Mockito.never()).save(Mockito.any(Empleado.class));
    }


    @Test
    @DisplayName("test para lsitar empelados")
    public void testListarEmpleados(){

        // given
        Empleado carlos = Empleado.builder()
                .nombre("Carlos")
                .apellido("Santana")
                .email("carlos@gmail.com")
                .build();

        BDDMockito.given(empleadoRepositorio.findAll()).willReturn(List.of(empleado, carlos));
        //when

        List<Empleado> listarEmpleados = empleadoService.getAllEmpleados();

        //then
        Assertions.assertThat(listarEmpleados).isNotNull();
        Assertions.assertThat(listarEmpleados.size()).isEqualTo(2);
    }




    @Test
    @DisplayName("Test para probar una lista vacia")
    public void testListarColeccionVacia(){

        // given
        Empleado carlos = Empleado.builder()
                .nombre("Carlos")
                .apellido("Santana")
                .email("carlos@gmail.com")
                .build();
        BDDMockito.given(empleadoRepositorio.findAll()).willReturn(Collections.emptyList());

        // when

        List<Empleado> empleados = empleadoService.getAllEmpleados();

        //then

        Assertions.assertThat(empleados).isEmpty();
        Assertions.assertThat(empleados.size()).isEqualTo(0);

    }


    // obtener empelado por id
    @Test
    @DisplayName("Tes de traer empleado por id")
    public void testGetEmpleadoById(){

        // given
        Empleado carlos = Empleado.builder()
                .id(1L)
                .nombre("Carlos")
                .apellido("Santana")
                .email("carlos@gmail.com")
                .build();

        BDDMockito.given(empleadoRepositorio.findById(1L)).willReturn(Optional.of(empleado));

        // when
        Empleado empleadoGuardado = empleadoService.getEmpleadoById(empleado.getId()).get();

        // then

        Assertions.assertThat(empleadoGuardado).isNotNull();
        //Assertions.assertThat(empleadoGuardado).isEqualTo(1L);

    }



    // eliminar metodo

    @Test
    @DisplayName("Test para probar la eliminacion")
    public void testParaEliminarEmpleado(){
        // given

        Long id = 1L;

        BDDMockito.willDoNothing().given(empleadoRepositorio).deleteById(id);
        // when
        empleadoService.deleteEmpleado(id);


        //then
        Mockito.verify(empleadoRepositorio, Mockito.times(1)).deleteById(id);
    }










}

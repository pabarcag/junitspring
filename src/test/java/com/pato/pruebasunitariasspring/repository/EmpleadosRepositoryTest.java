package com.pato.pruebasunitariasspring.repository;

import com.pato.pruebasunitariasspring.models.Empleado;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmpleadosRepositoryTest {

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;
    private Empleado empleado;


    @BeforeEach
    void setup() {
        empleado = Empleado.builder()
                .nombre("Fernando")
                .apellido("Fonseca")
                .email("fer@gmail.com")
                .build();
    }


    @Test
    @DisplayName("Metodo para guardar un empelado")
    public void guardarEmpleadoTest() {
        // given - precondicion dado un usuario
        Empleado empleado = Empleado.builder()
                .nombre("Pepe")
                .apellido("Loez")
                .email("pepe@gmail.com")
                .build();

        //when - cuando el usuario es guardado
        Empleado empleadoGuardado = empleadoRepositorio.save(empleado);

        //then -> esperamos que el empelado no sea null y que su id sea mayor a 0
        Assertions.assertThat(empleadoGuardado).isNotNull();
        Assertions.assertThat(empleadoGuardado.getId()).isGreaterThan(0);


    }


    @Test
    @DisplayName("Test para listar empleados")
    void testListarEmpleados() {
        //given
        Empleado eduardo = Empleado.builder()
                .nombre("Edurdo")
                .apellido("Cabrera")
                .email("eduardo@gmail.com")
                .build();

        empleadoRepositorio.save(eduardo);
        empleadoRepositorio.save(empleado);

        // when
        List<Empleado> listaEmpleados = empleadoRepositorio.findAll();

        //then
        Assertions.assertThat(listaEmpleados).isNotNull();
        Assertions.assertThat(listaEmpleados.size()).isEqualTo(2);
    }


    @Test
    @DisplayName("Probar metodo para buscar empleado por id")
    void testBuscarPorId() {


        // given
        empleadoRepositorio.save(empleado);

        // when
        Empleado empleado1 = empleadoRepositorio.findById(empleado.getId()).get();

        // then
        Assertions.assertThat(empleado1).isNotNull();
    }


    @Test
    @DisplayName("Test de eliminar por id")
    public void  testEliminarPorId(){

        empleadoRepositorio.save(empleado);

        //whem
        empleadoRepositorio.deleteById(empleado.getId());

        Optional<Empleado> empleadoOptional = empleadoRepositorio.findById(empleado.getId());

        //then
        Assertions.assertThat(empleadoOptional).isEmpty();

    }

}

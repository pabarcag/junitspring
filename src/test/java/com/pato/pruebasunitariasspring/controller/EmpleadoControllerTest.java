package com.pato.pruebasunitariasspring.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pato.pruebasunitariasspring.service.EmpleadoServicio;
import com.pato.pruebasunitariasspring.service.impl.EmpleadoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class EmpleadoControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmpleadoServicio empleadoServicio;


}

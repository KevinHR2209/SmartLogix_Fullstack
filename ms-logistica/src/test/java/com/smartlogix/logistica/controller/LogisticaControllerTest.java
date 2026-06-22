package com.smartlogix.logistica.controller;

import com.smartlogix.mslogistica.controller.DespachoController;
import com.smartlogix.mslogistica.model.Despacho;
import com.smartlogix.mslogistica.service.DespachoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DespachoController.class)
public class LogisticaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DespachoService despachoService;

    @Test
    void listarDespachos_debeRetornarLista() throws Exception {
        Despacho d1 = new Despacho();
        Despacho d2 = new Despacho();
        List<Despacho> lista = Arrays.asList(d1, d2);

        when(despachoService.listarTodos()).thenReturn(lista);

        mockMvc.perform(get("/api/despachos"))
                .andExpect(status().isOk());
    }

    @Test
    void listarDespachos_listaVacia_debeRetornarOk() throws Exception {
        when(despachoService.listarTodos()).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api/despachos"))
                .andExpect(status().isOk());
    }

    @Test
    void listarDespachos_debeRetornarJson() throws Exception {
        Despacho d = new Despacho();
        when(despachoService.listarTodos()).thenReturn(Arrays.asList(d));

        mockMvc.perform(get("/api/despachos"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"));
    }
}

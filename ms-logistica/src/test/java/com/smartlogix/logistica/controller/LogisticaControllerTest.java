package com.smartlogix.logistica.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartlogix.logistica.entity.Despacho;
import com.smartlogix.logistica.service.LogisticaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LogisticaController.class)
class LogisticaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LogisticaService logisticaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAll_retornaLista200() throws Exception {
        Despacho d = new Despacho();
        when(logisticaService.listarTodos()).thenReturn(List.of(d));

        mockMvc.perform(get("/api/despachos"))
                .andExpect(status().isOk());
    }

    @Test
    void getById_retorna200() throws Exception {
        Despacho d = new Despacho();
        when(logisticaService.buscarPorId(1L)).thenReturn(d);

        mockMvc.perform(get("/api/despachos/1"))
                .andExpect(status().isOk());
    }

    @Test
    void crear_retorna200() throws Exception {
        Despacho d = new Despacho();
        when(logisticaService.guardar(any())).thenReturn(d);

        mockMvc.perform(post("/api/despachos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(d)))
                .andExpect(status().isOk());
    }

    @Test
    void eliminar_retorna200() throws Exception {
        mockMvc.perform(delete("/api/despachos/1"))
                .andExpect(status().isOk());
    }
}

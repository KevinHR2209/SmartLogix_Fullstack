package com.smartlogix.ventas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartlogix.ventas.entity.Pedido;
import com.smartlogix.ventas.service.VentaService;
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

@WebMvcTest(VentaController.class)
class VentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VentaService ventaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAll_retornaLista200() throws Exception {
        Pedido p = new Pedido();
        when(ventaService.listarTodos()).thenReturn(List.of(p));

        mockMvc.perform(get("/api/pedidos"))
                .andExpect(status().isOk());
    }

    @Test
    void getById_retorna200() throws Exception {
        Pedido p = new Pedido();
        when(ventaService.buscarPorId(1L)).thenReturn(p);

        mockMvc.perform(get("/api/pedidos/1"))
                .andExpect(status().isOk());
    }

    @Test
    void crear_retorna200() throws Exception {
        Pedido p = new Pedido();
        when(ventaService.guardar(any())).thenReturn(p);

        mockMvc.perform(post("/api/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p)))
                .andExpect(status().isOk());
    }

    @Test
    void eliminar_retorna200() throws Exception {
        mockMvc.perform(delete("/api/pedidos/1"))
                .andExpect(status().isOk());
    }
}

package com.smartlogix.ventas.controller;

import com.smartlogix.msventas.controller.PedidoController;
import com.smartlogix.msventas.model.Pedido;
import com.smartlogix.msventas.service.PedidoService;
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

@WebMvcTest(PedidoController.class)
public class VentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Test
    void listarPedidos_debeRetornarLista() throws Exception {
        Pedido p1 = new Pedido();
        Pedido p2 = new Pedido();
        List<Pedido> lista = Arrays.asList(p1, p2);

        when(pedidoService.listarTodos()).thenReturn(lista);

        mockMvc.perform(get("/api/pedidos"))
                .andExpect(status().isOk());
    }

    @Test
    void listarPedidos_listaVacia_debeRetornarOk() throws Exception {
        when(pedidoService.listarTodos()).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api/pedidos"))
                .andExpect(status().isOk());
    }

    @Test
    void listarPedidos_debeRetornarJson() throws Exception {
        Pedido p = new Pedido();
        when(pedidoService.listarTodos()).thenReturn(Arrays.asList(p));

        mockMvc.perform(get("/api/pedidos"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"));
    }
}

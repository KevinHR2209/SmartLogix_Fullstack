package com.smartlogix.ventas.service;

import com.smartlogix.msventas.model.Pedido;
import com.smartlogix.msventas.repository.PedidoRepository;
import com.smartlogix.msventas.service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VentaServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarTodos_debeRetornarLista() {
        Pedido p1 = new Pedido();
        Pedido p2 = new Pedido();
        when(pedidoRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Pedido> resultado = pedidoService.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(pedidoRepository, times(1)).findAll();
    }

    @Test
    void listarTodos_repositorioVacio_debeRetornarListaVacia() {
        when(pedidoRepository.findAll()).thenReturn(Arrays.asList());

        List<Pedido> resultado = pedidoService.listarTodos();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void guardar_debeRetornarPedidoGuardado() {
        Pedido p = new Pedido();
        when(pedidoRepository.save(p)).thenReturn(p);

        Pedido resultado = pedidoService.guardar(p);

        assertNotNull(resultado);
        verify(pedidoRepository, times(1)).save(p);
    }

    @Test
    void buscarPorId_debeRetornarPedido() {
        Pedido p = new Pedido();
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(p));

        Optional<Pedido> resultado = pedidoService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
    }
}

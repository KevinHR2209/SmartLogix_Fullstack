package com.smartlogix.ventas.service;

import com.smartlogix.ventas.entity.Pedido;
import com.smartlogix.ventas.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VentaServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private VentaService ventaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarTodos_retornaListaNoVacia() {
        Pedido p = new Pedido();
        when(pedidoRepository.findAll()).thenReturn(List.of(p));

        List<Pedido> result = ventaService.listarTodos();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void guardar_llamaRepositoryYRetornaPedido() {
        Pedido p = new Pedido();
        when(pedidoRepository.save(p)).thenReturn(p);

        Pedido result = ventaService.guardar(p);

        assertNotNull(result);
        verify(pedidoRepository, times(1)).save(p);
    }

    @Test
    void buscarPorId_cuandoExiste_retornaPedido() {
        Pedido p = new Pedido();
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(p));

        Pedido result = ventaService.buscarPorId(1L);

        assertNotNull(result);
    }

    @Test
    void buscarPorId_cuandoNoExiste_retornaNull() {
        when(pedidoRepository.findById(99L)).thenReturn(Optional.empty());

        Pedido result = ventaService.buscarPorId(99L);

        assertNull(result);
    }

    @Test
    void eliminar_llamaDeleteById() {
        ventaService.eliminar(1L);
        verify(pedidoRepository, times(1)).deleteById(1L);
    }
}

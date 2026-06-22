package com.smartlogix.logistica.service;

import com.smartlogix.logistica.entity.Despacho;
import com.smartlogix.logistica.repository.DespachoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LogisticaServiceTest {

    @Mock
    private DespachoRepository despachoRepository;

    @InjectMocks
    private LogisticaService logisticaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarTodos_retornaLista() {
        Despacho d = new Despacho();
        when(despachoRepository.findAll()).thenReturn(List.of(d));

        List<Despacho> result = logisticaService.listarTodos();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void guardar_llamaRepositoryYRetornaDespacho() {
        Despacho d = new Despacho();
        when(despachoRepository.save(d)).thenReturn(d);

        Despacho result = logisticaService.guardar(d);

        assertNotNull(result);
        verify(despachoRepository, times(1)).save(d);
    }

    @Test
    void buscarPorId_cuandoExiste_retornaDespacho() {
        Despacho d = new Despacho();
        when(despachoRepository.findById(1L)).thenReturn(Optional.of(d));

        Despacho result = logisticaService.buscarPorId(1L);

        assertNotNull(result);
    }

    @Test
    void buscarPorId_cuandoNoExiste_retornaNull() {
        when(despachoRepository.findById(99L)).thenReturn(Optional.empty());

        Despacho result = logisticaService.buscarPorId(99L);

        assertNull(result);
    }

    @Test
    void eliminar_llamaDeleteById() {
        logisticaService.eliminar(1L);
        verify(despachoRepository, times(1)).deleteById(1L);
    }
}

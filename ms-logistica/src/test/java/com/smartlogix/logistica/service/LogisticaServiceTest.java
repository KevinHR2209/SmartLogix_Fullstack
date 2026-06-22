package com.smartlogix.logistica.service;

import com.smartlogix.mslogistica.model.Despacho;
import com.smartlogix.mslogistica.repository.DespachoRepository;
import com.smartlogix.mslogistica.service.DespachoService;
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

public class LogisticaServiceTest {

    @Mock
    private DespachoRepository despachoRepository;

    @InjectMocks
    private DespachoService despachoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarTodos_debeRetornarLista() {
        Despacho d1 = new Despacho();
        Despacho d2 = new Despacho();
        when(despachoRepository.findAll()).thenReturn(Arrays.asList(d1, d2));

        List<Despacho> resultado = despachoService.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(despachoRepository, times(1)).findAll();
    }

    @Test
    void listarTodos_repositorioVacio_debeRetornarListaVacia() {
        when(despachoRepository.findAll()).thenReturn(Arrays.asList());

        List<Despacho> resultado = despachoService.listarTodos();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void guardar_debeRetornarDespachoGuardado() {
        Despacho d = new Despacho();
        when(despachoRepository.save(d)).thenReturn(d);

        Despacho resultado = despachoService.guardar(d);

        assertNotNull(resultado);
        verify(despachoRepository, times(1)).save(d);
    }

    @Test
    void buscarPorId_debeRetornarDespacho() {
        Despacho d = new Despacho();
        when(despachoRepository.findById(1L)).thenReturn(Optional.of(d));

        Optional<Despacho> resultado = despachoService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
    }
}

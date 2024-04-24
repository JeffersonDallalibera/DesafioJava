package com.jefferson.service;

import com.jefferson.model.Projeto;
import com.jefferson.repository.ProjetoRepository;
import com.jefferson.util.RecordNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ProjetoServiceTest {
    @Mock
    private ProjetoRepository repository;

    @InjectMocks
    private ProjetoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllProjetos() {
        List<Projeto> projetoList = new ArrayList<>();
        when(repository.findAll()).thenReturn(projetoList);

        List<Projeto> result = service.getAllProjetos();

        assertEquals(projetoList, result);
    }

    @Test
    void testGetProjetoById() throws RecordNotFoundException {
        Long id = 1L;
        Projeto projeto = new Projeto();
        projeto.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(projeto));

        Projeto result = service.getProjetoById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void testSalvarProjeto() {
        Projeto projeto = new Projeto();
        projeto.setNome("Test Projeto");

        when(repository.save(projeto)).thenReturn(projeto);

        Projeto result = service.salvarProjeto(projeto);

        assertNotNull(result);
        assertEquals(projeto, result);
    }

    @Test
    void testPesquisarProjetosPorTermo() {
        String termoPesquisa = "Test";

        service.pesquisarProjetosPorTermo(termoPesquisa);

        verify(repository, times(1)).findByNomeContainingIgnoreCase(termoPesquisa);
    }

    @Test
    void testDeletarProjeto() {
        Long id = 1L;

        service.deletarProjeto(id);

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    void testAtualizarProjeto() throws RecordNotFoundException {
        Long id = 1L;
        Projeto projetoExistente = new Projeto();
        projetoExistente.setId(id);

        Projeto projetoAtualizado = new Projeto();
        projetoAtualizado.setId(id);
        projetoAtualizado.setNome("Updated Projeto");

        when(repository.findById(id)).thenReturn(Optional.of(projetoExistente));
        when(repository.save(projetoExistente)).thenReturn(projetoExistente);

        Projeto result = service.atualizarProjeto(id, projetoAtualizado);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(projetoAtualizado.getNome(), result.getNome());
    }
}

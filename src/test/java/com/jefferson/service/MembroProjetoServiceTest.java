package com.jefferson.service;

import com.jefferson.model.MembroProjeto;
import com.jefferson.model.Pessoa;
import com.jefferson.model.Projeto;
import com.jefferson.repository.MembroProjetoRepository;
import com.jefferson.repository.PessoaRepository;
import com.jefferson.util.RecordNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class MembroProjetoServiceTest {

    @Autowired
    private MembroProjetoService membroProjetoService;

    @MockBean
    private MembroProjetoRepository membroProjetoRepository;

    @MockBean
    private PessoaRepository pessoaRepository;

    /*referencia: https://www.baeldung.com/mockito-behavior*/
    @Test
    void testAssociarMembroProjeto() throws RecordNotFoundException {

        Long idProjeto = 8L;
        Long idPessoa = 1L;
        Projeto projeto = new Projeto(idProjeto);
        Pessoa pessoa = new Pessoa(idPessoa);

        Mockito.when(pessoaRepository.findById(idPessoa)).thenReturn(Optional.of(pessoa));

        membroProjetoService.associarMembroProjeto(idProjeto, idPessoa);

        Mockito.verify(membroProjetoRepository).save(Mockito.any(MembroProjeto.class));

    }

    @Test
    void testAssociarMembroProjeto_ProjetoNaoEncontrado() {
        Long idProjeto = 999L;
        Long idPessoa = 2L;
        Mockito.when(pessoaRepository.findById(idPessoa)).thenReturn(Optional.of(new Pessoa(idPessoa)));


        assertThrows(RecordNotFoundException.class, () -> membroProjetoService.associarMembroProjeto(idProjeto, idPessoa));
    }
    @Test
    void testAssociarMembroProjeto_PessoaNaoEncontrada() {
        Long idProjeto = 1L;
        Long idPessoa = 999L;


        assertThrows(RecordNotFoundException.class, () -> membroProjetoService.associarMembroProjeto(idProjeto, idPessoa));
    }
    @Test
    void testAssociarMembroProjeto_ProjetoEPessoaNaoEncontrados() {
        Long idProjeto = 999L;
        Long idPessoa = 999L;
        assertThrows(RecordNotFoundException.class, () -> membroProjetoService.associarMembroProjeto(idProjeto, idPessoa));
    }

}

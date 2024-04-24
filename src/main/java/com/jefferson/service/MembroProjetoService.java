package com.jefferson.service;

import com.jefferson.model.MembroProjeto;
import com.jefferson.model.Pessoa;
import com.jefferson.model.Projeto;
import com.jefferson.repository.MembroProjetoRepository;
import com.jefferson.repository.PessoaRepository;
import com.jefferson.repository.ProjetoRepository;
import com.jefferson.util.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembroProjetoService {

    @Autowired
    private MembroProjetoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    public void associarMembroProjeto(Long idProjeto, Long idPessoa) throws RecordNotFoundException {
        MembroProjeto membroProjeto = new MembroProjeto();
        Pessoa pessoa = pessoaRepository.findById(idPessoa).orElseThrow(() -> new RecordNotFoundException("Pessoa não encontrada"));
        Projeto projeto = projetoRepository.findById(idProjeto).orElseThrow(() -> new RecordNotFoundException("Projeto encontrado"));

        membroProjeto.setPessoa(pessoa);
        membroProjeto.setProjeto(projeto);

        repository.save(membroProjeto);
    }
}
package com.jefferson.service;

import com.jefferson.model.Pessoa;
import com.jefferson.repository.PessoaRepository;
import com.jefferson.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository repository;

    @Autowired
    public PessoaService(ProjetoRepository projetoRepository, PessoaRepository repository) {
        this.repository = repository;
    }

    public List<Pessoa> listarPessoas() {
        return repository.findAll();
    }


    public Pessoa buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Pessoa salvarPessoa(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    public void deletarPessoa(Long id) {
        repository.deleteById(id);
    }
}

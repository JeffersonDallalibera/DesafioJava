package com.jefferson.service;

import com.jefferson.model.Projeto;
import com.jefferson.repository.ProjetoRepository;
import com.jefferson.util.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    private final ProjetoRepository repository;

    @Autowired
    public ProjetoService(ProjetoRepository repository) {
        this.repository = repository;
    }

    public List<Projeto> getAllProjetos() {
        List<Projeto> projetoList = repository.findAll();

        if (!projetoList.isEmpty()) {
            return projetoList;
        } else {
            return new ArrayList<Projeto>();
        }
    }


    public Projeto getProjetoById(Long id) throws RecordNotFoundException {
        Optional<Projeto> projeto = repository.findById(id);

        if (projeto.isPresent()) {
            return projeto.get();
        } else {
            throw new RecordNotFoundException("Projeto nao Encontrado");
        }
    }

    public Projeto salvarProjeto(Projeto projeto) {
        return repository.save(projeto);
    }

    public void deletarProjeto(Long id) {
        repository.deleteById(id);
    }
}

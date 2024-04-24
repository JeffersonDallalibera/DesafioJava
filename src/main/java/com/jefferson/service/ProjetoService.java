package com.jefferson.service;

import com.jefferson.model.Projeto;
import com.jefferson.repository.ProjetoRepository;
import com.jefferson.util.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    private final ProjetoRepository repository;
    private final DefaultErrorAttributes errorAttributes;

    @Autowired
    public ProjetoService(ProjetoRepository repository, DefaultErrorAttributes errorAttributes) {
        this.repository = repository;
        this.errorAttributes = errorAttributes;
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
    public List<Projeto> pesquisarProjetosPorTermo(String termoPesquisa) {
        return repository.findByNomeContainingIgnoreCase(termoPesquisa);
    }

    public void deletarProjeto(Long id) {
        repository.deleteById(id);
    }

    public Projeto atualizarProjeto(Long id, Projeto projetoAtualizado) throws RecordNotFoundException {
        Projeto projetoExistente = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Projeto nao Encontrado com o ID "+ id));

        projetoExistente.setNome(projetoAtualizado.getNome());
        projetoExistente.setDescricao(projetoAtualizado.getDescricao());
        projetoExistente.setDataInicio(projetoAtualizado.getDataInicio());
        projetoExistente.setDataFim(projetoAtualizado.getDataFim());
        projetoExistente.setOrcamento(projetoAtualizado.getOrcamento());
        projetoExistente.setRisco(projetoAtualizado.getRisco());
        projetoExistente.setStatus(projetoAtualizado.getStatus());
        return repository.save(projetoExistente);
    }
}

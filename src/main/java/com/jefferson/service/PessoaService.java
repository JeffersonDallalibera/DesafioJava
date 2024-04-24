package com.jefferson.service;

import com.jefferson.model.Pessoa;
import com.jefferson.model.Projeto;
import com.jefferson.repository.PessoaRepository;
import com.jefferson.repository.ProjetoRepository;
import com.jefferson.util.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository repository;
    private final ProjetoRepository projetoRepository;

    @Autowired
    public PessoaService(ProjetoRepository projetoRepository, PessoaRepository repository) {
        this.repository = repository;
        this.projetoRepository = projetoRepository;
    }

    public List<Pessoa> listarPessoas() {
        return repository.findAll();
    }

    public void cadastrarPessoa(Pessoa novoMembro) {
        repository.save(novoMembro);
    }

    public void associarMembroProjeto(Long idProjeto, Long idMembro) throws RecordNotFoundException {
        Optional<Pessoa> pessoaOptional = repository.findById(idMembro);
        Optional<Projeto> projetoOptional = projetoRepository.findById(idProjeto);

        if (pessoaOptional.isPresent() && projetoOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            Projeto projeto = projetoOptional.get();

            if (pessoa.isFuncionario()) {
                projeto.addMembro(pessoa);
                projetoRepository.save(projeto);
            } else {
                throw new RecordNotFoundException("A pessoa não é um funcionário e não pode ser associada ao projeto.");
            }
        } else {
            throw new RecordNotFoundException("Membro ou projeto não encontrado.");
        }
    }
}

package com.jefferson.controller;

import com.jefferson.model.Pessoa;
import com.jefferson.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/membros")
public class MembroController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Void> cadastrarMembro(@RequestBody Pessoa novoMembro) {
        if (!novoMembro.isFuncionario()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            pessoaService.cadastrarPessoa(novoMembro);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{idProjeto}/associar")
    public ResponseEntity<String> associarMembroProjeto(@PathVariable Long idProjeto, @RequestBody Pessoa membro) {
        if (!membro.isFuncionario()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Apenas membros com atribuição de funcionário podem ser associados a projetos.");
        }
        try {
            pessoaService.associarMembroProjeto(idProjeto, membro.getId());
            return ResponseEntity.status(HttpStatus.OK).body("Membro associado ao projeto com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao associar membro ao projeto: " + e.getMessage());
        }
    }
}
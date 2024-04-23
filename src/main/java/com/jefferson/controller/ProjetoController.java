package com.jefferson.controller;


import com.jefferson.model.Projeto;
import com.jefferson.service.ProjetoService;
import com.jefferson.util.RecordNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/projetos")
public class ProjetoController {

    private final ProjetoService service;

    @Autowired
    public ProjetoController(ProjetoService service) {
        this.service = service;
    }

    @GetMapping("/index")
    public String getAllProjetos(Model model) {
        List<Projeto> list = service.getAllProjetos();
        model.addAttribute("nome", "Jefferson");
        return "projeto";
    }

    @GetMapping("/")
    public String listarProjetos(Model model) {
        List<Projeto> projetos = service.getAllProjetos();
        model.addAttribute("projetos", projetos);
        return "listar-projetos"; // Nome da sua página JSP (listar-projetos.jsp)
    }

    @GetMapping("/pesquisar")
    public String pesquisarProjeto(Model model) {

        List<Projeto> projetos = service.getAllProjetos();
        model.addAttribute("projetos", projetos);
        return "pesquisar-projetos";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscarProjetoPorId(@PathVariable Long id) throws RecordNotFoundException {
        Projeto projeto = service.getProjetoById(id);
        if (projeto != null) {
            return ResponseEntity.ok(projeto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/novo-projeto")
    public String mostrarFormularioNovoProjeto() {
        return "novo-projeto";
    }


    @PostMapping("/novo-projeto")
    public String novoProjeto(@ModelAttribute("Projeto") Projeto projeto, Model model) {
        System.out.println(projeto);
        // Validar Nome do Projeto
        if (projeto.getNome() == null || projeto.getNome().isEmpty()) {
            model.addAttribute("nomeError", "O nome do projeto é obrigatório.");
        }

        // Validar Data de Início (e outras validações de data)
        if (projeto.getDataInicio() == null) {
            model.addAttribute("dataInicioError", "A data de início é obrigatória.");
        } else if (projeto.getDataPrevisaoFim() != null && projeto.getDataInicio().isAfter(projeto.getDataPrevisaoFim())) {
            model.addAttribute("dataInicioError", "A data de início deve ser anterior à data de previsão de término.");
        } else if (projeto.getDataFim() != null && projeto.getDataInicio().isAfter(projeto.getDataFim())) {
            model.addAttribute("dataInicioError", "A data de início deve ser anterior à data de término real.");
        }

        // Validar Orçamento Total
        if (projeto.getOrcamento() == null) {
            model.addAttribute("orcamentoTotalError", "O orçamento total é obrigatório.");
        }

        // Validar Classificação de Risco
        if (projeto.getRisco() == null || projeto.getRisco().isEmpty()) {
            model.addAttribute("classificacaoRiscoError", "A classificação de risco é obrigatória.");
        }

        // Validar Status
        if (projeto.getStatus() == null || projeto.getStatus().isEmpty()) {
            model.addAttribute("statusError", "O status é obrigatório.");
        }

        // Validar Gerente Responsável
        if (projeto.getGerenteResponsavel() == null) {
            model.addAttribute("gerenteError", "O gerente responsável é obrigatório.");
        }

        // Verificar se há erros
        if (model.containsAttribute("nomeError") || model.containsAttribute("dataInicioError") ||
                model.containsAttribute("orcamentoTotalError") || model.containsAttribute("classificacaoRiscoError") ||
                model.containsAttribute("statusError") || model.containsAttribute("gerenteError")) {

            model.addAttribute("projeto", projeto);
            return "novo-projeto"; // Substitua 'pagina_do_formulario' pelo nome da sua página JSP
        }

        // Se não houver erros, salvar o projeto e redirecionar para outra página
        service.salvarProjeto(projeto);
        return "redirect:projetos/";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProjeto(@PathVariable Long id) {
        service.deletarProjeto(id);
        return ResponseEntity.noContent().build();
    }

}
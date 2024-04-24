package com.jefferson.controller;


import com.jefferson.model.Projeto;
import com.jefferson.service.ProjetoService;
import com.jefferson.util.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        if (projeto.getNome() == null || projeto.getNome().isEmpty()) {
            model.addAttribute("nomeError", "O nome do projeto é obrigatório.");
        }
        if (projeto.getDataInicio() == null) {
            model.addAttribute("dataInicioError", "A data de início é obrigatória.");
        } else if (projeto.getDataPrevisaoFim() != null && projeto.getDataInicio().isAfter(projeto.getDataPrevisaoFim())) {
            model.addAttribute("dataInicioError", "A data de início deve ser anterior à data de previsão de término.");
        } else if (projeto.getDataFim() != null && projeto.getDataInicio().isAfter(projeto.getDataFim())) {
            model.addAttribute("dataInicioError", "A data de início deve ser anterior à data de término real.");
        }
        if (projeto.getOrcamento() == null) {
            model.addAttribute("orcamentoTotalError", "O orçamento total é obrigatório.");
        }
        if (projeto.getRisco() == null || projeto.getRisco().isEmpty()) {
            model.addAttribute("classificacaoRiscoError", "A classificação de risco é obrigatória.");
        }
        if (projeto.getStatus() == null || projeto.getStatus().isEmpty()) {
            model.addAttribute("statusError", "O status é obrigatório.");
        }
        if (projeto.getGerenteResponsavel() == null) {
            model.addAttribute("gerenteError", "O gerente responsável é obrigatório.");
        }

        if (model.containsAttribute("nomeError") || model.containsAttribute("dataInicioError") ||
                model.containsAttribute("orcamentoTotalError") || model.containsAttribute("classificacaoRiscoError") ||
                model.containsAttribute("statusError") || model.containsAttribute("gerenteError")) {

            model.addAttribute("projeto", projeto);
            return "novo-projeto";
        }
        service.salvarProjeto(projeto);
        return "redirect:/projetos/";
    }


    @GetMapping("/search")
    public String pesquisarProjetos(@RequestParam(name = "q") String termoPesquisa, Model model) {
        System.out.println("Termo pesquisado->" + termoPesquisa);
        List<Projeto> resultadosPesquisa = service.pesquisarProjetosPorTermo(termoPesquisa);
        model.addAttribute("resultadosPesquisa", resultadosPesquisa);
        return "resultados-pesquisa";
    }

    @GetMapping("/detalhes-projeto")
    public String detalhesProjeto(@RequestParam("id") Long id, Model model) throws RecordNotFoundException {
        System.out.println("id solicitado->" + id.toString());
        Projeto projeto = service.getProjetoById(id);
        model.addAttribute("projeto", projeto);
        return "detalhes-projeto";
    }
    @PostMapping("/excluir")
    public String excluirProjeto(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) throws RecordNotFoundException {
        System.out.println("Deletando projeto ");
        Projeto projeto = service.getProjetoById(id);

        if (projeto.getStatus().equals("iniciado") || projeto.getStatus().equals("em_andamento") || projeto.getStatus().equals("encerrado")) {
            redirectAttributes.addFlashAttribute("errorMessage", "O projeto não pode ser excluído.");
            return "redirect:/projetos/";
        }
        service.deletarProjeto(id);
        redirectAttributes.addFlashAttribute("successMessage", "O projeto foi excluído com sucesso.");
        return "redirect:/projetos/";
    }

    @PostMapping("/atualizar")
    public String atualizarProjeto(@RequestParam("id") Long id, Projeto projetoAtualizado, RedirectAttributes redirectAttributes) throws RecordNotFoundException {
        Projeto atualizarProjeto = service.atualizarProjeto(id, projetoAtualizado);
        redirectAttributes.addFlashAttribute("successMessage", "Projeto atualizado com sucesso.");
        return "redirect:/projetos/detalhes-projeto?id=" + id;
    }


}
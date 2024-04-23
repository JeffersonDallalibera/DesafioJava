package com.jefferson.controller;

import com.jefferson.service.PessoaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @GetMapping("/viewBooks")
    public String viewBooks(Model model) {
        model.addAttribute("books", service.listarPessoas());
        return "view";
    }
}
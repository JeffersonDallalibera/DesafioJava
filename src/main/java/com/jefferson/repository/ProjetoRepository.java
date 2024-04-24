package com.jefferson.repository;

import com.jefferson.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {


    List<Projeto> findByNomeContainingIgnoreCase(String termoPesquisa);

}

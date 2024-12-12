package com.remedios.guilherme.curso.produto;

import java.util.Collection;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
@EnableJpaRepositories
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	List<Produto> findByNome(String nome);
}


package com.remedios.guilherme.curso.produto;

import java.time.LocalDate;

public record DadosDetalhamentoProduto(
		Long id,
		String nome,
		double preco) {
	public DadosDetalhamentoProduto(Produto produto) {
		this(produto.getId(), produto.getNome(), produto.getPreco());
	}
}


package com.remedios.guilherme.curso.produto;

import java.time.LocalDate;

public record DadosListagemProduto(long id, String nome, double preco) {
	public DadosListagemProduto(Produto produto) {
		this(
			produto.getId(),
			produto.getNome(),
			produto.getPreco()
			);	
	}
}


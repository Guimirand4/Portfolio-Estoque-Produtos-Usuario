package com.remedios.guilherme.curso.produto;

import com.remedios.guilherme.curso.produto.DadosAtualizarProduto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarProduto(	
		@NotNull
		String nome,
		Double preco) {

}

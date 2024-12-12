package com.remedios.guilherme.curso.produto;

import java.time.LocalDate;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroProduto(
		
		Long id,
		
		@NotBlank
		String nome,
		
		@NotNull
		double preco) {

}


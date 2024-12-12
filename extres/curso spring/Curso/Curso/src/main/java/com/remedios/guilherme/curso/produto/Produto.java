package com.remedios.guilherme.curso.produto;

import jakarta.persistence.*;
import jakarta.validation.Valid;

@Entity(name = "produto")
@Table(name = "produto")
@SuppressWarnings("unused")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private double preco;

    // Construtor padrão (necessário para JPA)
    public Produto() {}

    // Construtor sem o campo 'id', útil para inicializações manuais
    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    // Getters e setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public Produto(DadosCadastroProduto dados) {
        this.nome = dados.nome();
        this.preco = dados.preco();
    }


 
    public void atualizarInformacoes(@Valid DadosAtualizarProduto dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.preco() != null) {
            this.preco = dados.preco();
        }
    }
}

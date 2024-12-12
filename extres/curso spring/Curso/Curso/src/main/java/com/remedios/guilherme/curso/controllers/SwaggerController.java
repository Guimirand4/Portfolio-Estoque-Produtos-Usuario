package com.remedios.guilherme.curso.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.remedios.guilherme.curso.produto.*;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/produtos", produces = {"application/json"})
@Tag(name = "Produtos", description = "API para gerenciamento de produtos")
public class SwaggerController {

    private final ProdutoRepository repository;

   
    public SwaggerController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Cadastra um novo produto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoProduto> cadastrar(@RequestBody @Valid DadosCadastroProduto dados,
                                                              UriComponentsBuilder uriBuilder) {
        var produto = new Produto(dados);
        repository.save(produto);
        var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoProduto(produto));
    }

    @Operation(summary = "Lista todos os produtos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
    })
    @GetMapping
    public ResponseEntity<List<DadosListagemProduto>> listar() {
        var lista = repository.findAll().stream().map(DadosListagemProduto::new).toList();
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Atualiza as informações de um produto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoProduto> atualizar(@PathVariable Long id,
                                                              @RequestBody @Valid DadosAtualizarProduto dados) {
        var produto = repository.getReferenceById(id);
        produto.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
    }

    @Operation(summary = "Exclui permanentemente um produto")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Produto excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

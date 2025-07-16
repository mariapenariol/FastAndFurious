/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.penariol.FastAndFuriousFood.api.controller;

import br.com.penariol.FastAndFuriousFood.domain.model.Produto;
import br.com.penariol.FastAndFuriousFood.domain.repository.ProdutoRepository;
import br.com.penariol.FastAndFuriousFood.domain.service.ProdutoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ppjatb
 */
@RestController
public class ProdutoController {
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private ProdutoService produtoService;
    
    @GetMapping("/produto")
    public List<Produto> listas(){
        return produtoRepository.findAll();
    }
    
    @GetMapping("/produto/{produtoID}")
    public Produto buscar (@PathVariable Long produtoID){
        Optional<Produto>produto = produtoRepository.findById(produtoID);
        return produto.orElse(null);
    }
    
    @GetMapping("/produto/cat/{categoria}")
    public List<Produto> buscarPorCategoria(@PathVariable String categoria) {
    return produtoRepository.findByCategoria(categoria);
    }
    
    @PostMapping("/produto")
     @ResponseStatus(HttpStatus.CREATED)
     public Produto adicionar (@Valid @RequestBody Produto produto){
         return produtoService.salvar(produto);
     }
    
     @PutMapping("/produto/{produtoID}")
     public ResponseEntity<Produto> atualizar (@Valid @PathVariable Long produtoID, @RequestBody Produto produto){
     
     
       if (!produtoRepository.existsById(produtoID)){
           return ResponseEntity.notFound().build();
       }
          produto.setId(produtoID);
          produto = produtoService.salvar(produto);
          return ResponseEntity.ok(produto);
       
     }
     
     @DeleteMapping("/produto/{produtoID}")
     public ResponseEntity<Void> excluir (@PathVariable Long produtoID){
     
     
       if (!produtoRepository.existsById(produtoID)){
           return ResponseEntity.notFound().build();
       }
          produtoService.excluir(produtoID);
          return ResponseEntity.noContent().build();
       
     }
}

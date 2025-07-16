/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.penariol.FastAndFuriousFood.domain.service;

import br.com.penariol.FastAndFuriousFood.domain.exception.DomainException;
import br.com.penariol.FastAndFuriousFood.domain.model.Produto;
import br.com.penariol.FastAndFuriousFood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ppjatb
 */
@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvar (Produto produto){
        Produto produtoExistente = produtoRepository.findByNome(produto.getNome());
        
        if (produtoExistente != null && !produtoExistente.equals(produto)){
            throw new DomainException ("Já existe um lanche com esse nome!");
        }
        return produtoRepository.save(produto);
    }
    
    public void excluir(Long produtoId){
        produtoRepository.deleteById(produtoId);
    }
    
}

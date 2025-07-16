/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.penariol.FastAndFuriousFood.domain.repository;

import br.com.penariol.FastAndFuriousFood.domain.model.Produto;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ppjatb
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    public List<Produto> findByCategoria(String categoria);
    public Produto findByNome(String nome);

    

}

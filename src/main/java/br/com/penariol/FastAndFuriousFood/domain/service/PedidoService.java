/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.penariol.FastAndFuriousFood.domain.service;

import br.com.penariol.FastAndFuriousFood.domain.exception.DomainException;
import br.com.penariol.FastAndFuriousFood.domain.model.Pedido;
import br.com.penariol.FastAndFuriousFood.domain.model.StatusPedido;
import br.com.penariol.FastAndFuriousFood.domain.repository.PedidoRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ppjatb
 */
@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    
    public Pedido criar (Pedido pedido){
        pedido.setStatus(StatusPedido.ABERTO);
        pedido.setDataCriacao(LocalDateTime.now());
        
        return pedidoRepository.save(pedido);
    }
    
    public Pedido salvar (Pedido pedido){
        Pedido pedidoExistente = pedidoRepository.findById(pedido.getId());
       
       if (pedidoExistente != null && !pedidoExistente.equals(pedido)){
           throw new DomainException("Já existe um pedido cadastrado com esse Id!");
       }
       return pedidoRepository.save(pedido);
   }
   
   public void excluir (Long id){
       pedidoRepository.deleteById(id);
   }

    
}

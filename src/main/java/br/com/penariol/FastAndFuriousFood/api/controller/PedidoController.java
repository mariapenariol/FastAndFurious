/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.penariol.FastAndFuriousFood.api.controller;


import br.com.penariol.FastAndFuriousFood.DTO.PedidoDTO;
import br.com.penariol.FastAndFuriousFood.domain.model.Pedido;
import br.com.penariol.FastAndFuriousFood.domain.model.StatusPedido;
import br.com.penariol.FastAndFuriousFood.domain.repository.PedidoRepository;
import br.com.penariol.FastAndFuriousFood.domain.service.PedidoService;
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
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @GetMapping ("/pedido")
    public List<Pedido> listas(){
              
        return pedidoRepository.findAll();
    }
    
     @GetMapping("/pedido/{id}")
    public Pedido buscarPorId (@PathVariable Long id){
        Optional<Pedido>pedido = pedidoRepository.findById(id);
        return pedido.orElse(null);
    }
    
    
    @PostMapping ("/pedido")
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido criar (@RequestBody Pedido pedido){
        return pedidoService.criar(pedido);
    }
    
     @PutMapping("/pedido/{id}")
     public ResponseEntity<Pedido> atualizar (@Valid @PathVariable Long id, @RequestBody Pedido pedido){
     
     
       if (!pedidoRepository.existsById(id)){
           return ResponseEntity.notFound().build();
       }
          pedido.setId(id);
          pedido = pedidoService.salvar(pedido);
          return ResponseEntity.ok(pedido);
       
     }
     
     @DeleteMapping("/pedido/{id}")
     public ResponseEntity<Void> excluir (@PathVariable Long id){
     
     
       if (!pedidoRepository.existsById(id)){
           return ResponseEntity.notFound().build();
       }
          pedidoService.excluir(id);
          return ResponseEntity.noContent().build();
       
     } 
     
    @GetMapping("/pedido/status/{status}")
    public List<Pedido> buscarPorStatus(@PathVariable StatusPedido status) {
    return pedidoRepository.findByStatus(status);
     }
    
    @PutMapping("/pedido/status/{id}")
    public ResponseEntity<Pedido> atualizarStatus(@PathVariable Long id, @RequestBody PedidoDTO dto) {
    Optional<Pedido> optionalPedido = pedidoRepository.findById(id);

    if (optionalPedido.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    Pedido pedido = optionalPedido.get();
    pedido.setStatus(dto.getStatus());
    pedido = pedidoRepository.save(pedido);

    return ResponseEntity.ok(pedido);
}

}

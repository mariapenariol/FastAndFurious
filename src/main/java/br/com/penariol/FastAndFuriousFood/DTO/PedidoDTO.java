/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.penariol.FastAndFuriousFood.DTO;

import br.com.penariol.FastAndFuriousFood.domain.model.StatusPedido;

/**
 *
 * @author ppjatb
 */
public class PedidoDTO {
     private StatusPedido status;

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }
}


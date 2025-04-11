package com.pruebassolid2.model.interfaces;

import com.pruebassolid2.model.Pedido;

public interface IOrderManager {
    boolean crearPedido(Pedido pedido);
    boolean actualizarPedido(Pedido pedido);
    boolean eliminarPedido(int id);
}

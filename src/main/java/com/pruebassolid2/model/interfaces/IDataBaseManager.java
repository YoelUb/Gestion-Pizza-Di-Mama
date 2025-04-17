package com.pruebassolid2.model.interfaces;

import com.pruebassolid2.model.Pedido;

public interface IDataBaseManager {
    void guardarPedido(Pedido pedido);
    void actualizarPedido(int id, Pedido pedido);
    void eliminarPedido(int id);
}

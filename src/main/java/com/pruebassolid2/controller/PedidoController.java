package com.pruebassolid2.controller;

import com.pruebassolid2.model.Pedido;
import com.pruebassolid2.model.OrderManager;
import com.pruebassolid2.model.PaymentProcessor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final OrderManager orderManager;

    public PedidoController() {
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        this.orderManager = new OrderManager(paymentProcessor);
    }

    @PostMapping
    public Map<String, Object> crearPedido(@RequestBody Pedido pedido) {
        Map<String, Object> response = new HashMap<>();
        boolean creado = orderManager.crearPedido(pedido);

        if (creado) {
            response.put("pedido", pedido);
        } else {
            response.put("error", "No se pudo crear el pedido.");
        }

        return response;
    }

    @PutMapping
    public Map<String, Object> actualizarPedido(@RequestBody Pedido pedido) {
        Map<String, Object> response = new HashMap<>();
        boolean actualizado = orderManager.actualizarPedido(pedido);

        if (actualizado) {
            response.put("mensaje", "Pedido actualizado correctamente.");
            response.put("pedido", pedido);
        } else {
            response.put("error", "No se pudo actualizar el pedido.");
        }

        return response;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> eliminarPedido(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        boolean eliminado = orderManager.eliminarPedido(id);

        if (eliminado) {
            response.put("mensaje", "Pedido eliminado correctamente.");
        } else {
            response.put("error", "No se pudo eliminar el pedido.");
        }

        return response;
    }

    @GetMapping("/buscar")
    public Pedido buscarPedido(@RequestParam String cliente, @RequestParam int id) {
        return orderManager.buscarPedidoPorClienteYId(cliente, id);
    }


    @GetMapping("/buscar-todos")
    public List<Pedido> obtenerTodos() {
        return orderManager.obtenerTodos();
    }

}

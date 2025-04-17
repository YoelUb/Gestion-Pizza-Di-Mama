package com.pruebassolid2.model;

import com.pruebassolid2.model.interfaces.IOrderManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderManager implements IOrderManager {

    private final PaymentProcessor paymentProcessor;

    public OrderManager(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public boolean crearPedido(Pedido pedido) {
        boolean pagoExitoso = paymentProcessor.procesarPago(pedido.getCliente(), pedido.getPrecio());
        if (!pagoExitoso) return false;

        try (Connection conn = DataBaseManager.getConnection()) {
            String sql = "INSERT INTO pedidos (cliente, pizza, precio, estado) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pedido.getCliente());
            ps.setString(2, pedido.getPizza());
            ps.setDouble(3, pedido.getPrecio());
            ps.setString(4, pedido.getEstado());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                pedido.setId(rs.getInt(1)); // asignar ID al objeto
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarPedido(Pedido pedido) {
        try (Connection conn = DataBaseManager.getConnection()) {
            String sql = "UPDATE pedidos SET estado = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pedido.getEstado());
            ps.setInt(2, pedido.getId());
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean eliminarPedido(int id) {
        try (Connection conn = DataBaseManager.getConnection()) {
            String sql = "DELETE FROM pedidos WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Pedido buscarPedidoPorClienteYId(String cliente, int id) {
        try (Connection conn = DataBaseManager.getConnection()) {
            String sql = "SELECT * FROM pedidos WHERE id = ? AND cliente = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, cliente);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Pedido p = new Pedido();
                p.setId(rs.getInt("id"));
                p.setCliente(rs.getString("cliente"));
                p.setPizza(rs.getString("pizza"));
                p.setPrecio(rs.getDouble("precio"));
                p.setEstado(rs.getString("estado"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Pedido> obtenerTodos() {
        List<Pedido> pedidos = new ArrayList<>();
        try (Connection conn = DataBaseManager.getConnection()) {
            String sql = "SELECT * FROM pedidos";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pedido p = new Pedido();
                p.setId(rs.getInt("id"));
                p.setCliente(rs.getString("cliente"));
                p.setPizza(rs.getString("pizza"));
                p.setPrecio(rs.getDouble("precio"));
                p.setEstado(rs.getString("estado"));
                pedidos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

}

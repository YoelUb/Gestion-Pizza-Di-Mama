package com.pruebassolid2.model;

public class Pedido {
    private int id;
    private String cliente;
    private String pizza;
    private double precio;
    private String estado;

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public String getPizza() { return pizza; }
    public void setPizza(String pizza) { this.pizza = pizza; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}

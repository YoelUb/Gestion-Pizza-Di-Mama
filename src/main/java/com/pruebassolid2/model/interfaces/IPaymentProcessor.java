package com.pruebassolid2.model.interfaces;

public interface IPaymentProcessor {
    boolean procesarPago(String cliente, double monto);
}
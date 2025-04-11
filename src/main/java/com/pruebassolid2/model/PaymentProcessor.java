package com.pruebassolid2.model;

import com.pruebassolid2.model.interfaces.IPaymentProcessor;

public class PaymentProcessor implements IPaymentProcessor {

    public boolean procesarPago(String cliente, double monto) {
        System.out.println("Procesando pago de " + monto + "€ para el cliente " + cliente);
        try {
            Thread.sleep(1500); // Simula espera de procesamiento
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        return true;
    }
}

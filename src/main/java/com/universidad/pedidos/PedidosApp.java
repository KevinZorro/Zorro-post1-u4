package com.universidad.pedidos;

import com.universidad.pedidos.command.*;
import com.universidad.pedidos.cor.*;
import com.universidad.pedidos.modelo.Pedido;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PedidosApp implements CommandLineRunner {
    private final HistorialComandos historial;

    public PedidosApp(HistorialComandos historial) {
        this.historial = historial;
    }

    public static void main(String[] args) {
        SpringApplication.run(PedidosApp.class, args);
    }

    @Override
    public void run(String... args) {
        // Construir la cadena de validación
        ValidadorPedido cadena = new ValidadorStock();
        cadena.setNext(new ValidadorMonto())
                .setNext(new ValidadorCredito());
        // Pedido válido
        Pedido p1 = new Pedido("P-001", "PROD-A", 3, 45000.0, true);
        System.out.println("\n--- Validando pedido P-001 ---");
        boolean ok = cadena.validar(p1);
        System.out.println("Resultado validación: " + ok);
        if (ok) {
            // Ejecutar comandos
            historial.ejecutar(new ComandoConfirmar(p1));
            historial.ejecutar(new ComandoAplicarDescuento(p1, 10));
            System.out.println("Estado actual: " + p1);
            // Deshacer el descuento
            System.out.println("\n--- Deshaciendo última acción ---");
            historial.deshacer();
            System.out.println("Estado después de undo: " + p1);
        }
        // Pedido inválido (sin crédito)
        System.out.println("\n--- Validando pedido P-002 (sin crédito) ---");
        Pedido p2 = new Pedido("P-002", "PROD-B", 2, 30000.0, false);
        cadena.validar(p2);
    }

}
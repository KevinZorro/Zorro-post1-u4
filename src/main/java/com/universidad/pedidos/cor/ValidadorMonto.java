package com.universidad.pedidos.cor;
import com.universidad.pedidos.modelo.Pedido;
public class ValidadorMonto extends ValidadorPedido {
 private static final double MINIMO = 5000.0;
 @Override
 public boolean validar(Pedido pedido) {
 if (pedido.getTotal() < MINIMO) {
 System.out.println("[MONTO] RECHAZADO: total $" +
pedido.getTotal()
 + " inferior al mínimo $" + MINIMO + ".");
 return false;
 }
 System.out.println("[MONTO] OK: total $" + pedido.getTotal() + "supera el mínimo.");
 return delegar(pedido);
 }
}

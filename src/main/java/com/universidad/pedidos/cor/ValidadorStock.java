package com.universidad.pedidos.cor;
import com.universidad.pedidos.modelo.Pedido;
public class ValidadorStock extends ValidadorPedido {
 private static final int STOCK_DISPONIBLE = 10; // stock simulado
 @Override
 public boolean validar(Pedido pedido) {
 if (pedido.getCantidad() > STOCK_DISPONIBLE) {
 System.out.println("[STOCK] RECHAZADO: cantidad " +
pedido.getCantidad()
 + " supera stock disponible (" +
STOCK_DISPONIBLE + ").");
 return false;
 }
 System.out.println("[STOCK] OK: " + pedido.getCantidad() + "unidades disponibles.");
 return delegar(pedido);
 }
}

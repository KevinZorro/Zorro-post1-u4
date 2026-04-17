package com.universidad.pedidos.command;
import com.universidad.pedidos.modelo.Pedido;
public class ComandoAplicarDescuento implements Comando {
 private final Pedido pedido;
 private final double porcentaje;
 private double totalAnterior;
 public ComandoAplicarDescuento(Pedido pedido, double porcentaje) {
 this.pedido = pedido;
 this.porcentaje = porcentaje;
 }
 @Override
 public void execute() {
 totalAnterior = pedido.getTotal();
 double nuevoTotal = totalAnterior * (1 - porcentaje / 100.0);
 pedido.setTotal(nuevoTotal);
 System.out.printf("[CMD] Descuento %.0f%% aplicado: $%.2f →$%.2f%n",
 porcentaje, totalAnterior, nuevoTotal);
 }
 @Override
 public void undo() {
 pedido.setTotal(totalAnterior);
 System.out.printf("[UNDO] Descuento revertido: $%.2f restaurado%n",
totalAnterior);
 }
 @Override
 public String getNombre() { return "AplicarDescuento(" + porcentaje +
"%)"; }
}

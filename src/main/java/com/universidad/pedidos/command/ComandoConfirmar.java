package com.universidad.pedidos.command;
import com.universidad.pedidos.modelo.Pedido;
public class ComandoConfirmar implements Comando {
 private final Pedido pedido;
 private String estadoAnterior;
 public ComandoConfirmar(Pedido pedido) { this.pedido = pedido; }
 @Override
 public void execute() {
 estadoAnterior = pedido.getEstado();
 pedido.setEstado("CONFIRMADO");
 System.out.println("[CMD] Pedido " + pedido.getId() + "confirmado.");
 }
 @Override
 public void undo() {
 pedido.setEstado(estadoAnterior);
 System.out.println("[UNDO] Pedido " + pedido.getId()
 + " revertido a: " + estadoAnterior);
 }
 @Override
 public String getNombre() { return "ConfirmarPedido"; }
}

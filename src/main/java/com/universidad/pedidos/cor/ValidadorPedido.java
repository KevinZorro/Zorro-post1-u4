package com.universidad.pedidos.cor;
import com.universidad.pedidos.modelo.Pedido;
public abstract class ValidadorPedido {
 protected ValidadorPedido siguiente;
 // Encadena el siguiente validador y lo retorna para fluent API
 public ValidadorPedido setNext(ValidadorPedido next) {
 this.siguiente = next;
 return next;
 }
 public abstract boolean validar(Pedido pedido);
 // Delega al siguiente si existe
 protected boolean delegar(Pedido pedido) {
 if (siguiente == null) return true;
 return siguiente.validar(pedido);
 }
}

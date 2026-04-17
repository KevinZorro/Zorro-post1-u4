package com.universidad.pedidos.cor;
import com.universidad.pedidos.modelo.Pedido;
public class ValidadorCredito extends ValidadorPedido {
 @Override
 public boolean validar(Pedido pedido) {
 if (!pedido.isCreditoOk()) {
 System.out.println("[CREDITO] RECHAZADO: el cliente no tiene crédito aprobado.");
 return false;
 }
 System.out.println("[CREDITO] OK: crédito del cliente aprobado.");
 return delegar(pedido);
 }
}
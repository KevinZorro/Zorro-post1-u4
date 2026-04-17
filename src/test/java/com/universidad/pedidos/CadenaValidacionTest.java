package com.universidad.pedidos;
import com.universidad.pedidos.command.*;
import com.universidad.pedidos.cor.*;
import com.universidad.pedidos.modelo.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class CadenaValidacionTest {
 private ValidadorPedido cadena;
 @BeforeEach
 void setUp() {
 cadena = new ValidadorStock();
 cadena.setNext(new ValidadorMonto()).setNext(new
ValidadorCredito());
 }
 @Test
 void testPedidoValidoPasaTodaLaCadena() {
 Pedido p = new Pedido("P-OK", "PROD", 2, 20000.0, true);
 assertTrue(cadena.validar(p));
 }
 @Test
 void testPedidoRechazadoPorStock() {
 Pedido p = new Pedido("P-STOCK", "PROD", 99, 50000.0, true);
 assertFalse(cadena.validar(p));
 }
 @Test
 void testPedidoRechazadoPorMontoMinimo() {
 Pedido p = new Pedido("P-MONTO", "PROD", 1, 100.0, true);
 assertFalse(cadena.validar(p));
 }
 @Test
 void testCommandConfirmarConUndo() {
 Pedido p = new Pedido("P-CMD", "PROD", 1, 10000.0, true);
 HistorialComandos h = new HistorialComandos();
 assertEquals("PENDIENTE", p.getEstado());
 h.ejecutar(new ComandoConfirmar(p));
 assertEquals("CONFIRMADO", p.getEstado());
 h.deshacer();
 assertEquals("PENDIENTE", p.getEstado());
 }
 @Test
 void testCommandDescuentoConUndo() {
 Pedido p = new Pedido("P-DESC", "PROD", 1, 100000.0, true);
 HistorialComandos h = new HistorialComandos();
 h.ejecutar(new ComandoAplicarDescuento(p, 20));
 assertEquals(80000.0, p.getTotal(), 0.01);
 h.deshacer();
 assertEquals(100000.0, p.getTotal(), 0.01);
 }
}

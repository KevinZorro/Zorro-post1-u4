package com.universidad.pedidos.command;
import org.springframework.stereotype.Component;
import java.util.ArrayDeque;
import java.util.Deque;
@Component
public class HistorialComandos {
 private final Deque<Comando> historial = new ArrayDeque<>();
 public void ejecutar(Comando cmd) {
 cmd.execute();
 historial.push(cmd);
 }
 public void deshacer() {
 if (historial.isEmpty()) {
 System.out.println("[UNDO] No hay acciones para deshacer.");
 return;
 }
 historial.pop().undo();
 }
 public int tamano() { return historial.size(); }
}
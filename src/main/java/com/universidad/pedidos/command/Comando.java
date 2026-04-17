package com.universidad.pedidos.command;
public interface Comando {
 void execute();
 void undo();
 String getNombre();
}

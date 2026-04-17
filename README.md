# pedidos-comportamiento

Proyecto Spring Boot que implementa los patrones de comportamiento
**Chain of Responsibility** y **Command con Undo** en el contexto de
un sistema de procesamiento de pedidos de comercio electrónico.

## Patrones Aplicados

### Chain of Responsibility — Cadena de Validación

Cada validación está encapsulada en un manejador independiente.
La cadena se construye con fluent API (`setNext()`):
ValidadorStock → ValidadorMonto → ValidadorCredito

| Validador          | Condición de rechazo                          |
|--------------------|-----------------------------------------------|
| `ValidadorStock`   | `cantidad > 10` (stock simulado)              |
| `ValidadorMonto`   | `total < $5,000`                              |
| `ValidadorCredito` | `creditoOk == false`                          |

Si un manejador rechaza el pedido, la cadena se detiene y no delega.

### Command con Undo — Operaciones Reversibles

Cada acción sobre el pedido se encapsula como un objeto `Comando`
que almacena el estado anterior para soportar `undo()`:

| Comando                   | execute()                  | undo()                        |
|---------------------------|----------------------------|-------------------------------|
| `ComandoConfirmar`        | estado → CONFIRMADO        | restaura estado anterior      |
| `ComandoAplicarDescuento` | total × (1 − porcentaje)   | restaura total anterior       |

`HistorialComandos` actúa como **Invoker** usando una pila `ArrayDeque`
(LIFO): `push` al ejecutar, `pop` al deshacer.

## Requisitos

- Java 17+
- Maven 3.8+

## Ejecución

```bash
git clone https://github.com/<usuario>/Zorro-post1-u4.git
cd Zorro-post1-u4
mvn clean package
mvn spring-boot:run
```

## Pruebas

```bash
mvn test
```

Resultado esperado: `BUILD SUCCESS` con 5 tests pasando.

## Salida de Consola Esperada

PEDIDO P-001 (válido)
[STOCK] OK: 3 unidades disponibles.
[MONTO] OK: total $45000.00 supera el mínimo.
[CREDITO] OK: crédito del cliente aprobado.
Resultado validación: true
[CMD] Pedido P-001 confirmado.
[CMD] Descuento 10% aplicado: $45000.00 → $40500.00
Estado actual: Pedido{id='P-001', estado='CONFIRMADO', total=40500.00}

--- Deshaciendo última acción (descuento) ---
[UNDO] Descuento revertido: $45000.00 restaurado
Estado después de undo: Pedido{id='P-001', estado='CONFIRMADO', total=45000.00}
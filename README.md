# ResicApp

La empresa Read And Music ha contratado a usted y su equipo para desarrollar una aplicación de compra de libros y discos musicales, llamada "ResicApp". A continuación, se detallan los requisitos que la empresa menciona:

## Requisitos

- Implementar un sistema de inicio de sesión mediante nickname y contraseña.
- Permitir al usuario comprar libros o discos musicales, calculando correctamente el monto a pagar.
- Mostrar al usuario un listado de los libros y discos musicales disponibles para la compra.
- Mostrar al usuario su historial de compras, así como el detalle de cada libro o disco elegido.

## Cálculo del monto total a pagar

La empresa proporciona las siguientes reglas para calcular el monto total a pagar, basadas en la clasificación del producto elegido:

| Tipo de clasificación | Comisión                                                                                                               | Ejemplo                                                                                                                                                                     |
|------------------------|------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Gold                   | Aplica una comisión del 2%                                                                                            | Si compro un producto Gold de $100, el monto total a pagar es de $102                                                                                                      |
| Silver                 | Si la compra se realiza entre las 15:00 hs y las 22:30 hs aplica una comisión del 1%, sino aplica una comisión del 3% | Si compro un producto Silver de $100 entre las 15:00 hs y las 22:30 hs, el monto total a pagar es de $101.                                                               |
|                        |                                                                                                                        | Si compro un producto Silver de $100 en un horario por fuera de las 15:00 hs y las 22:30 hs, el monto total a pagar es de $103.                                           |
| Platinum               | Si la compra se realiza un sábado o domingo se aplica una comisión del 3%, sino aplica una comisión del 0.75%         | Si compro un producto Platinum de $100 un sábado o domingo, el monto total a pagar es de $103                                                                               |
|                        |                                                                                                                        | Si compro un producto Platinum de $100 un día de semana, el monto total a pagar es de $100,75                                                                              |
| Bronze                 | No aplica comisión                                                                                                    | -                                                                                                                                                                           |


### Notas
- No necesita implementar una interfaz grafica
- No necesita hacer uso de una base de datos
- Todas las reglas que la empresa usa para calcular el costo total fueron dadas anteriormente. No necesita aplicar ninguna otra regla

### Requisitos técnicos
- Hacer uso de herencia y polimorfismo.
- Hacer uso de funciones lambda y/o acotadas.
- Crear y hacer uso de al menos una excepción propia.

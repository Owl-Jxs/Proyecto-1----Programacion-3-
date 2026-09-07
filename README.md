# Proyecto 1 — Programación III

Sistema de gestión para restaurante: catálogo, pedidos y estadísticas.
Desarrollado para el curso EIF206 - Programación III (UNA).

## Integrantes
- Jose Alvarado
- Andres Segura
- Erving Sequeira
- Arleth Varela

## Cómo ejecutar
1. Abrir el proyecto en IntelliJ.
2. Ejecutar la clase `Main` (`AplicacionGestora.Main`).
3. Los datos (catálogo y pedidos) se guardan automáticamente en la carpeta `datos/` (CSV).

## Funcionalidades
- Gestión del catálogo (crear, editar y eliminar productos) con deshacer/rehacer.
- Realización de pedidos (carrito) con deshacer/rehacer.
- Procesado y cancelación de pedidos, con guardado de recibos en CSV.
- Estadísticas de ventas (ingresos, pedidos por estado y producto más vendido).

## Arquitectura
- **MVC**: separa interfaz (Swing), controladores y modelo.
- **DAO**: acceso a datos; implementaciones en memoria (`LinkedHashMap`) con persistencia en CSV.
- **Command**: acciones deshacer/rehacer (`IComando` + `GestorAcciones`).
- **Persistencia**: archivos CSV bajo `datos/` (catálogo y pedidos).

## Estructura del proyecto

src/AplicacionGestora/
├── Main.java                    # punto de entrada (composition root)
├── Logica/
│   ├── Models/                  # entidades y colecciones (Producto, Catalogo, interfaces)
│   ├── Structures/              # Pedido y LineaPedido
│   ├── Controllers/             # lógica de aplicación
│   ├── Comandos/                # patrón Command (deshacer/rehacer)
│   └── DAO/                     # acceso a datos en memoria + persistencia
├── Persistencia/                # lectura/escritura de CSV
└── Vista/                       # interfaz gráfica (Swing)

## Documentación
- `Javadoc`

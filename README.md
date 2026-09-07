# Proyecto 1 - Programación III

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

```text
src/
└── AplicacionGestora/
    ├── Main.java                    # Punto de entrada (Composition Root)
    │
    ├── Logica/
    │   ├── Models/                  # Entidades y colecciones
    │   │                           # Producto, Catalogo e interfaces
    │   │
    │   ├── Structures/              # Pedido y LineaPedido
    │   │
    │   ├── Controllers/             # Lógica de aplicación
    │   │
    │   ├── Comandos/                # Patrón Command (deshacer/rehacer)
    │   │
    │   └── DAO/                     # Acceso a datos en memoria
    │                               # y persistencia
    │
    ├── Persistencia/                # Lectura/escritura de CSV
    │
    └── Vista/                       # Interfaz gráfica (Swing)

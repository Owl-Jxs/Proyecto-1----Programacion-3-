package AplicacionGestora.Vista.Panels.Catalogo;

import AplicacionGestora.Logica.Controllers.ControladorCatalogo;
import AplicacionGestora.Logica.Controllers.ControladorPedido;
import AplicacionGestora.Logica.Models.CategoriaProducto;
import AplicacionGestora.Logica.Models.Producto;
import AplicacionGestora.Vista.Panels.MenuPrincipal.mainPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CatalogoPanel extends JPanel {

    private static final int COLUMNAS_CATALOGO = 3;

    private JPanel panelRaiz;
    private JPanel panelCabecera;
    private JLabel lblTitulo;
    private JPanel panelAcciones;
    private JScrollPane scrollProductos;
    private JPanel panelProductos;

    private ModoCatalogo modo;
    private mainPanel main;
    private ControladorCatalogo controladorCatalogo;
    private ControladorPedido controladorPedido;
    private JButton btnCarrito;

    public CatalogoPanel(
            ModoCatalogo modo,
            mainPanel main,
            ControladorCatalogo controladorCatalogo,
            ControladorPedido controladorPedido
    ) {
        this.modo = modo;
        this.main = main;
        this.controladorCatalogo = controladorCatalogo;
        this.controladorPedido = controladorPedido;

        inicializarFormularioSiHaceFalta();
        prepararEstructuraVisual();
        configurarModo();
        actualizarCatalogo();

        setLayout(new BorderLayout());
        add(panelRaiz, BorderLayout.CENTER);
    }

    private void inicializarFormularioSiHaceFalta() {
        if (panelRaiz != null) {
            return;
        }

        panelRaiz = new JPanel(new BorderLayout());
        panelCabecera = new JPanel();
        lblTitulo = new JLabel();
        panelAcciones = new JPanel();
        panelProductos = new JPanel();
        scrollProductos = new JScrollPane(panelProductos);
    }

    private void prepararEstructuraVisual() {
        panelRaiz.removeAll();
        panelRaiz.setLayout(new BorderLayout());
        panelRaiz.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));

        panelCabecera.setLayout(new BorderLayout(12, 0));
        panelCabecera.setBorder(BorderFactory.createEmptyBorder(0, 0, 14, 0));
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 24f));
        panelAcciones.setLayout(new FlowLayout(FlowLayout.RIGHT, 8, 0));

        scrollProductos.setViewportView(panelProductos);
        panelCabecera.add(lblTitulo, BorderLayout.WEST);
        panelCabecera.add(panelAcciones, BorderLayout.EAST);
        panelRaiz.add(panelCabecera, BorderLayout.NORTH);
        panelRaiz.add(scrollProductos, BorderLayout.CENTER);
    }

    private void configurarModo() {
        panelAcciones.removeAll();
        JButton btnVolver = new JButton("Volver");

        if (modo == ModoCatalogo.PEDIDO) {
            lblTitulo.setText("Catalogo");
            btnVolver.addActionListener(e -> main.mostrarMenuCliente());

            btnCarrito = new JButton();
            btnCarrito.addActionListener(e -> main.mostrarCarrito());
            actualizarTextoCarrito();

            panelAcciones.add(btnVolver);
            panelAcciones.add(btnCarrito);
        } else {
            lblTitulo.setText("Gestion de catalogo");
            btnVolver.addActionListener(e -> main.mostrarMenuAdmin());
            panelAcciones.add(btnVolver);
        }
    }

    public void actualizarCatalogo() {
        List<Producto> productos = controladorCatalogo.obtenerCatalogo();

        panelProductos.removeAll();
        panelProductos.setLayout(new GridLayout(0, COLUMNAS_CATALOGO, 16, 16));
        panelProductos.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        for (Producto producto : productos) {
            panelProductos.add(crearTarjetaProducto(producto));
        }

        if (modo == ModoCatalogo.ADMIN) {
            panelProductos.add(crearTarjetaAgregarProducto());
        } else if (productos.isEmpty()) {
            panelProductos.add(new JLabel("No hay productos en el catalogo."));
        }

        actualizarTextoCarrito();
        panelProductos.revalidate();
        panelProductos.repaint();
    }

    private JPanel crearTarjetaProducto(Producto producto) {
        JPanel tarjeta = crearBaseTarjeta();

        JLabel lblNombre = new JLabel(producto.getNombre());
        lblNombre.setFont(lblNombre.getFont().deriveFont(Font.BOLD, 16f));

        JLabel lblCategoria = new JLabel(producto.getCategoria().toString());
        JLabel lblPrecio = new JLabel(formatearPrecio(producto.getPrecio()));

        JPanel panelDatos = new JPanel(new GridLayout(3, 1, 0, 4));
        panelDatos.setOpaque(false);
        panelDatos.add(lblNombre);
        panelDatos.add(lblCategoria);
        panelDatos.add(lblPrecio);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        panelBotones.setOpaque(false);

        if (modo == ModoCatalogo.PEDIDO) {
            JButton btnAgregar = new JButton("Agregar");
            btnAgregar.addActionListener(e -> agregarProductoAlPedido(producto));
            panelBotones.add(btnAgregar);
        } else {
            JButton btnEditar = new JButton("Editar");
            JButton btnEliminar = new JButton("Eliminar");

            btnEditar.addActionListener(e -> editarProducto(producto));
            btnEliminar.addActionListener(e -> eliminarProducto(producto));

            panelBotones.add(btnEditar);
            panelBotones.add(btnEliminar);
        }

        tarjeta.add(panelDatos, BorderLayout.CENTER);
        tarjeta.add(panelBotones, BorderLayout.SOUTH);
        return tarjeta;
    }

    private JPanel crearTarjetaAgregarProducto() {
        JPanel tarjeta = crearBaseTarjeta();
        tarjeta.setLayout(new GridBagLayout());

        JButton btnAgregarProducto = new JButton("Agregar producto");
        btnAgregarProducto.addActionListener(e -> crearProducto());
        tarjeta.add(btnAgregarProducto);

        return tarjeta;
    }

    private JPanel crearBaseTarjeta() {
        JPanel tarjeta = new JPanel(new BorderLayout(8, 8));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                BorderFactory.createEmptyBorder(14, 14, 14, 14)
        ));
        tarjeta.setPreferredSize(new Dimension(180, 150));
        return tarjeta;
    }

    private void crearProducto() {
        Producto producto = solicitarProducto(null);

        if (producto == null) {
            return;
        }

        try {
            controladorCatalogo.agregarProducto(producto);
            actualizarCatalogo();
        } catch (RuntimeException excepcion) {
            mostrarError(excepcion.getMessage());
        }
    }

    private void editarProducto(Producto productoAnterior) {
        Producto producto = solicitarProducto(productoAnterior);

        if (producto == null) {
            return;
        }

        try {
            controladorCatalogo.modificarProducto(producto);
            actualizarCatalogo();
        } catch (RuntimeException excepcion) {
            mostrarError(excepcion.getMessage());
        }
    }

    private void eliminarProducto(Producto producto) {
        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "Eliminar " + producto.getNombre() + "?",
                "Eliminar producto",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            controladorCatalogo.eliminarProducto(producto.getId());
            actualizarCatalogo();
        } catch (RuntimeException excepcion) {
            mostrarError(excepcion.getMessage());
        }
    }

    private Producto solicitarProducto(Producto productoAnterior) {
        JTextField txtNombre = new JTextField();
        JTextField txtPrecio = new JTextField();
        JComboBox<CategoriaProducto> cmbCategoria = new JComboBox<>(
                CategoriaProducto.values()
        );

        int id = controladorCatalogo.siguienteId();
        String titulo = "Agregar producto";

        if (productoAnterior != null) {
            id = productoAnterior.getId();
            titulo = "Editar producto";
            txtNombre.setText(productoAnterior.getNombre());
            txtPrecio.setText(String.valueOf(productoAnterior.getPrecio()));
            cmbCategoria.setSelectedItem(productoAnterior.getCategoria());
        }

        JPanel formulario = new JPanel(new GridLayout(0, 2, 8, 8));
        formulario.add(new JLabel("Nombre:"));
        formulario.add(txtNombre);
        formulario.add(new JLabel("Precio:"));
        formulario.add(txtPrecio);
        formulario.add(new JLabel("Categoria:"));
        formulario.add(cmbCategoria);

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                formulario,
                titulo,
                JOptionPane.OK_CANCEL_OPTION
        );

        if (respuesta != JOptionPane.OK_OPTION) {
            return null;
        }

        try {
            double precio = Double.parseDouble(txtPrecio.getText());
            CategoriaProducto categoria = (CategoriaProducto) cmbCategoria.getSelectedItem();
            return new Producto(id, txtNombre.getText(), precio, categoria);
        } catch (NumberFormatException excepcion) {
            mostrarError("El precio debe ser un numero.");
            return null;
        }
    }

    private void agregarProductoAlPedido(Producto producto) {
        try {
            controladorPedido.agregarLineaAlPedido(producto, 1);
            actualizarTextoCarrito();
        } catch (RuntimeException excepcion) {
            mostrarError(excepcion.getMessage());
        }
    }

    private void actualizarTextoCarrito() {
        if (btnCarrito != null) {
            int cantidad = controladorPedido.getPedidoActual().getLineas().size();
            btnCarrito.setText("Carrito (" + cantidad + ")");
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Catalogo",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private String formatearPrecio(double precio) {
        return String.format("CRC %.2f", precio);
    }
}

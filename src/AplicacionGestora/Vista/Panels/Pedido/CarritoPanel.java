package AplicacionGestora.Vista.Panels.Pedido;

import AplicacionGestora.Logica.Controllers.ControladorPedido;
import AplicacionGestora.Logica.Structures.LineaPedido;
import AplicacionGestora.Vista.Panels.MenuPrincipal.mainPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CarritoPanel extends JPanel {

    private JPanel panelRaiz;
    private JTable tablaPedido;
    private JLabel lblTotal;
    private JButton btnProcesar;
    private JButton btnCancelar;
    private JButton btnVolver;

    private mainPanel main;
    private ControladorPedido controladorPedido;
    private DefaultTableModel modeloTabla;

    public CarritoPanel(mainPanel main, ControladorPedido controladorPedido) {
        this.main = main;
        this.controladorPedido = controladorPedido;

        inicializarComponentes();
        crearVista();

        btnProcesar.addActionListener(e -> procesarPedido());
        btnCancelar.addActionListener(e -> cancelarPedido());
        btnVolver.addActionListener(e -> main.mostrarCatalogoPedido());

        actualizarCarrito();

        setLayout(new BorderLayout());
        add(panelRaiz, BorderLayout.CENTER);
    }

    private void inicializarComponentes() {
        panelRaiz = new JPanel(new BorderLayout(0, 18));
        panelRaiz.setBorder(BorderFactory.createEmptyBorder(28, 36, 28, 36));

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Producto");
        modeloTabla.addColumn("Categoria");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Subtotal");
        tablaPedido = new JTable(modeloTabla);

        lblTotal = new JLabel("Total: CRC 0.00");
        btnProcesar = new JButton("Procesar pedido");
        btnCancelar = new JButton("Cancelar pedido");
        btnVolver = new JButton("Volver");
    }

    private void crearVista() {
        JLabel lblTitulo = new JLabel("CARRITO", SwingConstants.CENTER);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 24f));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnProcesar);
        panelBotones.add(btnCancelar);
        panelBotones.add(btnVolver);

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(lblTotal, BorderLayout.WEST);
        panelInferior.add(panelBotones, BorderLayout.EAST);

        panelRaiz.add(lblTitulo, BorderLayout.NORTH);
        panelRaiz.add(new JScrollPane(tablaPedido), BorderLayout.CENTER);
        panelRaiz.add(panelInferior, BorderLayout.SOUTH);
    }

    public void actualizarCarrito() {
        modeloTabla.setRowCount(0);

        for (LineaPedido linea : controladorPedido.getPedidoActual().getLineas()) {
            modeloTabla.addRow(new Object[]{
                    linea.getProducto().getNombre(),
                    linea.getProducto().getCategoria(),
                    linea.getCantidad(),
                    String.format("CRC %.2f", linea.calcularSubtotal())
            });
        }

        lblTotal.setText(String.format(
                "Total: CRC %.2f",
                controladorPedido.getPedidoActual().calcularTotal()
        ));
    }

    private void procesarPedido() {
        try {
            controladorPedido.procesarPedidoTotal();
            actualizarCarrito();
            JOptionPane.showMessageDialog(this, "Pedido procesado correctamente.");
        } catch (RuntimeException excepcion) {
            mostrarError(excepcion.getMessage());
        }
    }

    private void cancelarPedido() {
        try {
            controladorPedido.cancelarPedido();
            actualizarCarrito();
            JOptionPane.showMessageDialog(this, "Pedido cancelado.");
        } catch (RuntimeException excepcion) {
            mostrarError(excepcion.getMessage());
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Carrito",
                JOptionPane.ERROR_MESSAGE
        );
    }
}

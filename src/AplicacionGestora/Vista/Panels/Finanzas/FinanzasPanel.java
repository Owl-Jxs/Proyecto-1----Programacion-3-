package AplicacionGestora.Vista.Panels.Finanzas;

import AplicacionGestora.Logica.Controllers.ControladorFinanzas;
import AplicacionGestora.Logica.Models.CategoriaProducto;
import AplicacionGestora.Vista.Panels.MenuPrincipal.mainPanel;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FinanzasPanel extends JPanel {

    private JPanel panelRaiz;
    private JComboBox<String> cmbPeriodo;
    private JTextField txtFecha;
    private JButton btnConsultar;
    private JTable tablaIngresos;
    private JLabel lblTotal;
    private JButton btnVolver;

    private ControladorFinanzas controladorFinanzas;
    private DefaultTableModel modeloTabla;

    public FinanzasPanel(mainPanel main) {
        inicializarComponentes();
        crearVista();

        controladorFinanzas = new ControladorFinanzas();
        btnConsultar.addActionListener(e -> consultarIngresos());
        cmbPeriodo.addActionListener(e -> cambiarEstadoFecha());
        btnVolver.addActionListener(e -> main.mostrarMenuAdmin());

        cambiarEstadoFecha();
        consultarIngresos();

        setLayout(new BorderLayout());
        add(panelRaiz, BorderLayout.CENTER);
    }

    private void inicializarComponentes() {
        panelRaiz = new JPanel(new BorderLayout(0, 18));
        panelRaiz.setBorder(BorderFactory.createEmptyBorder(28, 36, 28, 36));

        cmbPeriodo = new JComboBox<>(new String[]{"Ultimo dia", "Ultimo mes", "Fecha especifica"});
        txtFecha = new JTextField(10);
        txtFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        btnConsultar = new JButton("Consultar");
        btnVolver = new JButton("Volver");
        lblTotal = new JLabel("Ingreso bruto total: CRC 0.00");

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Categoria");
        modeloTabla.addColumn("Ingresos brutos");
        tablaIngresos = new JTable(modeloTabla);
    }

    private void crearVista() {
        JLabel lblTitulo = new JLabel("FINANZAS", SwingConstants.CENTER);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 24f));

        JPanel panelSuperior = new JPanel(new BorderLayout(0, 12));
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltros.add(new JLabel("Periodo:"));
        panelFiltros.add(cmbPeriodo);
        panelFiltros.add(new JLabel("Fecha:"));
        panelFiltros.add(txtFecha);
        panelFiltros.add(btnConsultar);
        panelSuperior.add(panelFiltros, BorderLayout.SOUTH);

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(lblTotal, BorderLayout.WEST);
        panelInferior.add(btnVolver, BorderLayout.EAST);

        panelRaiz.add(panelSuperior, BorderLayout.NORTH);
        panelRaiz.add(new JScrollPane(tablaIngresos), BorderLayout.CENTER);
        panelRaiz.add(panelInferior, BorderLayout.SOUTH);
    }

    private void cambiarEstadoFecha() {
        boolean fechaEspecifica = "Fecha especifica".equals(
                cmbPeriodo.getSelectedItem()
        );
        txtFecha.setEnabled(fechaEspecifica);
    }

    public void consultarIngresos() {
        try {
            Date fecha = obtenerFecha();
            String periodo = (String) cmbPeriodo.getSelectedItem();
            double[] ingresos = controladorFinanzas.calcularIngresos(periodo, fecha);

            llenarTabla(ingresos);
        } catch (ParseException excepcion) {
            JOptionPane.showMessageDialog(
                    this,
                    "La fecha debe tener el formato dd/MM/yyyy.",
                    "Fecha invalida",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (IllegalStateException excepcion) {
            JOptionPane.showMessageDialog(
                    this,
                    excepcion.getMessage(),
                    "Error al leer pedidos",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private Date obtenerFecha() throws ParseException {
        if (!"Fecha especifica".equals(cmbPeriodo.getSelectedItem())) {
            return new Date();
        }

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);
        return formato.parse(txtFecha.getText());
    }

    private void llenarTabla(double[] ingresos) {
        modeloTabla.setRowCount(0);
        double total = 0;
        CategoriaProducto[] categorias = CategoriaProducto.values();

        for (int indice = 0; indice < categorias.length; indice++) {
            modeloTabla.addRow(new Object[]{
                    categorias[indice],
                    String.format("CRC %.2f", ingresos[indice])
            });
            total += ingresos[indice];
        }

        lblTotal.setText(String.format("Ingreso bruto total: CRC %.2f", total));
    }
}

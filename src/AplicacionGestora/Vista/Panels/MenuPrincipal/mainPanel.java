package AplicacionGestora.Vista.Panels.MenuPrincipal;

import javax.swing.*;
import java.awt.*;

public class mainPanel extends JFrame {

    private CardLayout gestorViews;
    private JPanel contentPanel;
    private SelectionPanel panelInicial;

    /*
     * Como contentPanel está marcado como custom-create="true"
     * en el .form, IntelliJ utiliza este método para crearlo.
     */
    private void createUIComponents() {
        gestorViews = new CardLayout();
        contentPanel = new JPanel(gestorViews);
    }

    public mainPanel() {

        // Propiedades de la ventana
        setTitle("Panel principal de gestion de restaurante");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        /*
         * El .form se encarga de crear contentPanel mediante
         * createUIComponents().
         *
         * IMPORTANTE:
         * No debemos volver a hacer:
         * contentPanel = new JPanel(...)
         * porque estaríamos reemplazando el panel creado por el .form.
         */
        $$$setupUI$$$();

        // Creamos la pantalla inicial
        panelInicial = new SelectionPanel(this);

        // Agregamos la pantalla inicial al CardLayout
        contentPanel.add(panelInicial, "Pantalla de inicio");

        // Mostramos la pantalla inicial
        gestorViews.show(contentPanel, "Pantalla de inicio");

        // Colocamos el panel en la ventana
        setContentPane(contentPanel);

        // Centrar la ventana
        setLocationRelativeTo(null);

        // Mostrar la ventana
        setVisible(true);
    }

    public void mostrarMenuAdmin() {
        // Aquí posteriormente agregaremos el menú del administrador.

        // Ejemplo:
        // MenuAdmin menuAdmin = new MenuAdmin();
        // contentPanel.add(menuAdmin, "VistaAdmin");
        // gestorViews.show(contentPanel, "VistaAdmin");
    }

    public void mostrarMenuCliente() {
        // Aquí posteriormente agregaremos el menú del cliente.

        // Ejemplo:
        // MenuCliente menuCliente = new MenuCliente();
        // contentPanel.add(menuCliente, "VistaCliente");
        // gestorViews.show(contentPanel, "VistaCliente");
    }

    /**
     * Método generado por IntelliJ GUI Designer.
     * Se encarga de inicializar los componentes definidos
     * en mainPanel.form.
     */
    private void $$$setupUI$$$() {
        createUIComponents();
    }
}

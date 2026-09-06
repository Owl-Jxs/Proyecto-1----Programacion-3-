package AplicacionGestora.Vista.Panels.MenuPrincipal;
import AplicacionGestora.Vista.Panels.MenuPrincipal.mainPanel;

import javax.swing.*;
import java.awt.*;

public class MenuRegular extends JPanel {
    private JPanel panelRaiz;
    private JLabel lblTitulo;
    private JButton btnRealizarPedido;
    private JButton btnVolver;


    public MenuRegular (mainPanel main) {
        inicializarPanelSiHaceFalta();
        setLayout(new BorderLayout());
        add (panelRaiz, BorderLayout.CENTER);

        //btnRealizarPedido.addActionListener(e-> main.mostrarCatalogoPedido);
        btnVolver.addActionListener(e->main.mostrarSeleccion());
    }

    private void inicializarPanelSiHaceFalta () {
        if (panelRaiz != null){
            return;
        }
        panelRaiz = new JPanel(new GridLayout());
        panelRaiz.setBorder (BorderFactory.createEmptyBorder(60,80,60,80) );

        lblTitulo = new JLabel ("MENU REGULAR", SwingConstants.CENTER);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 24f));
        btnRealizarPedido = new JButton ("Realizar pedido");
        btnVolver = new JButton("Volver");

        GridBagConstraints gbc = crearRestricciones();

        gbc.gridy = 0;
        panelRaiz.add(lblTitulo, gbc);

        gbc.gridy = 1;
        panelRaiz.add(btnRealizarPedido, gbc);

        gbc.gridy = 2;
        panelRaiz.add(btnVolver, gbc);
    }

    private GridBagConstraints crearRestricciones() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 10, 10, 10);
        return gbc;
    }
}

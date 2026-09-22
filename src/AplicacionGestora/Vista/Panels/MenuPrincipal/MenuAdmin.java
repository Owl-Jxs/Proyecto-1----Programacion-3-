package AplicacionGestora.Vista.Panels.MenuPrincipal;

import AplicacionGestora.Vista.Panels.MenuPrincipal.mainPanel;
import javax.swing.*;
import java.awt.*;

public class MenuAdmin extends JPanel {
    private JPanel panelRaiz;
    private JLabel lbTitulo;
    private JButton btnCatalogo;
    private JButton btnFinanzas;
    private JButton btnVolver;

    public MenuAdmin (mainPanel main) {
        inicializarPanel();
        setLayout( new BorderLayout());
        add (panelRaiz, BorderLayout.CENTER);

        btnCatalogo.addActionListener(e -> main.mostrarCatalogoAdmin());
        btnFinanzas.addActionListener(e -> main.mostrarFinanzas());
        btnVolver.addActionListener(e->main.mostrarSeleccion());
    }

    private void inicializarPanel (){
        if (panelRaiz != null){
            return;
        }
        panelRaiz = new JPanel(new GridBagLayout());
        panelRaiz.setBorder (BorderFactory.createEmptyBorder(60,80,60,80) );

        lbTitulo = new JLabel("MENU ADMINISTRADOR");
        lbTitulo.setFont(lbTitulo.getFont().deriveFont(Font.BOLD, 24f));

        btnCatalogo = new JButton ("Gestionar Catalogo");
        btnFinanzas = new JButton("Gestionar Finanzas");
        btnVolver = new JButton("Volver");

        GridBagConstraints gbc = crearRestricciones();

        gbc.gridy = 0;
        panelRaiz.add(lbTitulo, gbc);

        gbc.gridy = 1;
        panelRaiz.add(btnCatalogo,gbc);

        gbc.gridy = 2;
        panelRaiz.add(btnFinanzas, gbc);

        gbc.gridy = 3;
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

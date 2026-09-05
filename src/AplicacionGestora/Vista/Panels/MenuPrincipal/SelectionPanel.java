package AplicacionGestora.Vista.Panels.MenuPrincipal;
import javax.swing.*;
import java.awt.*;

public class SelectionPanel extends JPanel {
    private JPanel panelRaiz;
    private JButton btnAdmin;
    private JButton btnRegular;

    public SelectionPanel(mainPanel main) {

        setLayout(new BorderLayout());
        if (panelRaiz != null) {
            add(panelRaiz, BorderLayout.CENTER);
        }

        // Acción del botón administrador
        btnAdmin.addActionListener(e -> {
            main.mostrarMenuAdmin();
        });

        // Acción del botón usuario regular
        btnRegular.addActionListener(e -> {
            main.mostrarMenuCliente();
        });
    }
}
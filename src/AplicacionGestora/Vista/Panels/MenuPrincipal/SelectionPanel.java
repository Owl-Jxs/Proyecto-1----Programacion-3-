package AplicacionGestora.Vista.Panels.MenuPrincipal;
import javax.swing.*;
import java.awt.*;

public class SelectionPanel extends JPanel {
    private JPanel panelRaiz;
    private JButton btnAdmin;
    private JButton btnRegular;

    public SelectionPanel(mainPanel main) {
        inicializarFormularioTipoUsuario();
        setLayout( new BorderLayout());
        add(panelRaiz, BorderLayout.CENTER);


        // Acción del botón administrador
        btnAdmin.addActionListener(e -> {
            main.mostrarMenuAdmin();
        });

        // Acción del botón usuario regular
        btnRegular.addActionListener(e -> {
            main.mostrarMenuCliente();
        });
    }

    private void inicializarFormularioTipoUsuario () {
        if (panelRaiz != null) { return; }

        panelRaiz = new JPanel(new GridBagLayout());
        panelRaiz.setBorder(BorderFactory.createEmptyBorder(60, 80, 60, 80));

        JLabel lblTitulo = new JLabel("Seleccione tipo de usuario", SwingConstants.CENTER);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 24f));

        btnRegular = new JButton("Entrar como usuario regular");
        btnAdmin = new JButton("Entrar como usuario admin");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridy = 0;
        panelRaiz.add(lblTitulo, gbc);

        gbc.gridy = 1;
        panelRaiz.add(btnRegular, gbc);

        gbc.gridy = 2;
        panelRaiz.add(btnAdmin, gbc);

    }
}
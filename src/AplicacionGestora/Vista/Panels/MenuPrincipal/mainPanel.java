package AplicacionGestora.Vista.Panels.MenuPrincipal;

import AplicacionGestora.Vista.Panels.Catalogo.CatalogoPanel;
import AplicacionGestora.Vista.Panels.Catalogo.ModoCatalogo;
import AplicacionGestora.Vista.Panels.Finanzas.FinanzasPanel;
import AplicacionGestora.Vista.Panels.Pedido.CarritoPanel;
import AplicacionGestora.Logica.Controllers.ControladorCatalogo;
import AplicacionGestora.Logica.Controllers.ControladorPedido;
import AplicacionGestora.Logica.DAO.CatalogoDAOImpl;
import AplicacionGestora.Logica.DAO.PedidoDAOImpl;

import javax.swing.*;
import java.awt.*;

public class mainPanel extends JFrame {
    //los nombres de cada panel en el gestor
    private static final String VISTA_SELECCION = "Seleccion";
    private static final String VISTA_MENU_ADMIN = "menuAdmin";
    private static final String VISTA_MENU_REGULAR = "menuRegular";
    private static final String VISTA_CATALOGO_ADMIN = "catalogoAdmin";
    private static final String VISTA_CATALOGO_PEDIDO = "catalogoPedido";
    private static final String VISTA_CARRITO = "carrito";
    private static final String VISTA_FINANZAS = "finanzas";


    private CardLayout gestorViews;
    private JPanel contentPanel;
    private ControladorCatalogo controladorCatalogo;
    private ControladorPedido controladorPedido;
    private SelectionPanel selectionPanel;
    private MenuRegular menuRegularPanel;
    private MenuAdmin menuAdminPanel;
    private CatalogoPanel catalogoPedidoPanel;
    private CatalogoPanel catalogoAdminPanel;
    private CarritoPanel carritoPanel;
    private FinanzasPanel finanzasPanel;

    public mainPanel() {
        configurarVentana();
        $$$setupUI$$$();

        //inicializamos y registramos las pantallas que usaremos
        inicializarPantallas();
        registrarPantallas();
        setContentPane(contentPanel);
        mostrarSeleccion ();
    }

    private void createUIComponents() {
        gestorViews = new CardLayout();
        contentPanel = new JPanel(gestorViews);
    }

    private void configurarVentana () { // Propiedades de la ventana
        setTitle("Panel principal de gestion de restaurante");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarPantallas () {
        controladorCatalogo = new ControladorCatalogo(new CatalogoDAOImpl());
        controladorPedido = new ControladorPedido(new PedidoDAOImpl());
        selectionPanel = new SelectionPanel(this);
        menuRegularPanel = new MenuRegular (this);
        menuAdminPanel = new MenuAdmin (this);
        catalogoPedidoPanel = new CatalogoPanel(
                ModoCatalogo.PEDIDO, this, controladorCatalogo, controladorPedido
        );
        catalogoAdminPanel = new CatalogoPanel(
                ModoCatalogo.ADMIN, this, controladorCatalogo, controladorPedido
        );
        carritoPanel = new CarritoPanel(this, controladorPedido);
        finanzasPanel = new FinanzasPanel(this);
    }

    private void registrarPantallas () {
        contentPanel.add (selectionPanel, VISTA_SELECCION);
        contentPanel.add(menuRegularPanel, VISTA_MENU_REGULAR);
        contentPanel.add(menuAdminPanel, VISTA_MENU_ADMIN);
        contentPanel.add(catalogoPedidoPanel, VISTA_CATALOGO_PEDIDO);
        contentPanel.add(catalogoAdminPanel, VISTA_CATALOGO_ADMIN);
        contentPanel.add(carritoPanel, VISTA_CARRITO);
        contentPanel.add(finanzasPanel, VISTA_FINANZAS);
    }

    public void mostrarSeleccion () { gestorViews.show(contentPanel, VISTA_SELECCION);}

    public void mostrarMenuAdmin() { gestorViews.show(contentPanel, VISTA_MENU_ADMIN);}

    public void mostrarMenuCliente() { gestorViews.show(contentPanel, VISTA_MENU_REGULAR);}
    public void mostrarCatalogoPedido() {
        catalogoPedidoPanel.actualizarCatalogo();
        gestorViews.show(contentPanel, VISTA_CATALOGO_PEDIDO);
    }

    public void mostrarCatalogoAdmin() {
        catalogoAdminPanel.actualizarCatalogo();
        gestorViews.show(contentPanel, VISTA_CATALOGO_ADMIN);
    }

    public void mostrarCarrito() {
        carritoPanel.actualizarCarrito();
        gestorViews.show(contentPanel, VISTA_CARRITO);
    }

    public void mostrarFinanzas() {
        finanzasPanel.consultarIngresos();
        gestorViews.show(contentPanel, VISTA_FINANZAS);
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

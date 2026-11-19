package practica2;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.MenuSelectionManager;

/**
 * Práctica 2 - Ventana con JMenuBar, JMenu (con submenús),
 * imagen de fondo y JOptionPane al salir.
 */
public class Ventana extends JFrame {

    // Fondo
    private PanelFondo panelFondo;

    // Barra de menú
    private JMenuBar barraMenu;

    // Menús principales
    private JMenu menuArchivo;
    private JMenu menuEditar;
    private JMenu menuReporte;
    private JMenu menuSalir;

    // Submenús
    private JMenu menuNuevo;
    private JMenu menuConsultar;
    private JMenu menuEliminar;
    private JMenu menuModificar;
    private JMenu menuArchivoPdf;
    private JMenu menuArchivoTxt;

    public Ventana() {
        setTitle("Práctica 2");
        setSize(420, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        colocarFondo();
        colocarBarraMenu();
        colocarMenuArchivo();
        colocarMenuEditar();
        colocarMenuReporte();
        colocarMenuSalir();
    }

    private void colocarFondo() {
        panelFondo = new PanelFondo("/practica2/imagenes/fondo.png");
        panelFondo.setLayout(null);
        setContentPane(panelFondo);
    }

    private void colocarBarraMenu() {
        barraMenu = new JMenuBar();
        setJMenuBar(barraMenu);
    }

    private void colocarMenuArchivo() {
        menuArchivo = new JMenu("Archivo");
        barraMenu.add(menuArchivo);

        menuNuevo = crearSubmenu("Nuevo");
        menuArchivo.add(menuNuevo);

        menuConsultar = crearSubmenu("Consultar");
        menuArchivo.add(menuConsultar);

        menuEliminar = crearSubmenu("Eliminar");
        menuArchivo.add(menuEliminar);
    }

    private void colocarMenuEditar() {
        menuEditar = new JMenu("Editar");
        barraMenu.add(menuEditar);

        menuModificar = crearSubmenu("Modificar");
        menuEditar.add(menuModificar);
    }

    private void colocarMenuReporte() {
        menuReporte = new JMenu("Reporte");
        barraMenu.add(menuReporte);

        menuArchivoPdf = crearSubmenu("Archivo PDF");
        menuReporte.add(menuArchivoPdf);

        menuArchivoTxt = crearSubmenu("Archivo txt");
        menuReporte.add(menuArchivoTxt);
    }

    private void colocarMenuSalir() {
        menuSalir = new JMenu("Salir");
        menuSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                salir();
            }
        });
        barraMenu.add(menuSalir);
    }

    /** Crea un submenú con las opciones Alumno y Profesor. */
    private JMenu crearSubmenu(String titulo) {
        JMenu submenu = new JMenu(titulo);
        submenu.add(crearOpcion(titulo, "Alumno"));
        submenu.add(crearOpcion(titulo, "Profesor"));
        return submenu;
    }

    private JMenuItem crearOpcion(String accion, String nombre) {
        JMenuItem opcion = new JMenuItem(nombre);
        opcion.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Opción seleccionada: " + accion + " - " + nombre,
                "Práctica 2", JOptionPane.INFORMATION_MESSAGE));
        return opcion;
    }

    /** Menú Salir: pide confirmación con un JOptionPane. */
    private void salir() {
        MenuSelectionManager.defaultManager().clearSelectedPath();
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Desea salir del programa?", "Salir",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}

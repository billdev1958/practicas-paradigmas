package practica2;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Práctica 2 - Clase principal (ejecuta el programa).
 */
public class Practica2 {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("No se pudo cargar Nimbus: " + e.getMessage());
        }
        SwingUtilities.invokeLater(() -> new Ventana().setVisible(true));
    }
}

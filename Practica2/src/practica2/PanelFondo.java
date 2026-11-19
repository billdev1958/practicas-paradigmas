package practica2;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Panel que dibuja una imagen de fondo ajustada a su tamaño.
 */
public class PanelFondo extends JPanel {

    private Image imagen;

    public PanelFondo(String ruta) {
        URL url = getClass().getResource(ruta);
        if (url != null) {
            imagen = new ImageIcon(url).getImage();
        } else {
            System.out.println("No se encontró la imagen de fondo: " + ruta);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

package practica5;

import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Práctica 5 - Inicio de sesión con JLabel, JTextField,
 * JPasswordField y JButton.
 */
public class Ventana extends JFrame {

    // Credenciales de ejemplo para probar el acceso
    private static final String USUARIO = "admin";
    private static final char[] CONTRASENA = {'1', '2', '3', '4'};

    // Contenedor
    private JPanel panel;

    // Etiquetas
    private JLabel lblUsuario;
    private JLabel lblContrasena;

    // Cajas de texto
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;

    // Botones
    private JButton btnNuevo;
    private JButton btnAcceder;

    public Ventana() {
        setTitle("Practica 5");
        setSize(420, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        colocarPanel();
        colocarEtiquetas();
        colocarCajasDeTexto();
        colocarBotones();
    }

    private void colocarPanel() {
        panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel);
    }

    private void colocarEtiquetas() {
        lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(50, 50, 100, 30);
        panel.add(lblUsuario);

        lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(50, 120, 100, 30);
        panel.add(lblContrasena);
    }

    private void colocarCajasDeTexto() {
        txtUsuario = new JTextField();
        txtUsuario.setBounds(160, 50, 200, 30);
        panel.add(txtUsuario);

        txtContrasena = new JPasswordField();
        txtContrasena.setEchoChar('*');
        txtContrasena.setBounds(160, 120, 200, 30);
        // Enter en la contraseña equivale a presionar Acceder
        txtContrasena.addActionListener(e -> acceder());
        panel.add(txtContrasena);
    }

    private void colocarBotones() {
        btnNuevo = new JButton("Nuevo");
        btnNuevo.setBounds(85, 200, 90, 30);
        btnNuevo.addActionListener(e -> limpiarCampos());
        panel.add(btnNuevo);

        btnAcceder = new JButton("Acceder");
        btnAcceder.setBounds(225, 200, 90, 30);
        btnAcceder.addActionListener(e -> acceder());
        panel.add(btnAcceder);
    }

    /** Botón Nuevo: limpia el formulario. */
    private void limpiarCampos() {
        txtUsuario.setText("");
        txtContrasena.setText("");
        txtUsuario.requestFocus();
    }

    /** Botón Acceder: valida el usuario y la contraseña. */
    private void acceder() {
        String usuario = txtUsuario.getText().trim();
        char[] contrasena = txtContrasena.getPassword();

        if (usuario.isEmpty() || contrasena.length == 0) {
            JOptionPane.showMessageDialog(this, "Escriba el usuario y la contraseña.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
        } else if (usuario.equals(USUARIO) && Arrays.equals(contrasena, CONTRASENA)) {
            JOptionPane.showMessageDialog(this, "Bienvenido, " + usuario + ".",
                    "Acceso correcto", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.",
                    "Acceso denegado", JOptionPane.ERROR_MESSAGE);
            txtContrasena.setText("");
            txtContrasena.requestFocus();
        }

        // Se borra la copia de la contraseña de la memoria
        Arrays.fill(contrasena, '\0');
    }
}

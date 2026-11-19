package practica1;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Práctica 1 - Formulario con JLabel, JTextField, JPasswordField,
 * JTextArea (con barras de desplazamiento) y JButton.
 */
public class Ventana extends JFrame {

    // Contenedor
    private JPanel panel;

    // Etiquetas
    private JLabel lblNombre;
    private JLabel lblEdad;
    private JLabel lblCorreo;
    private JLabel lblUsuario;
    private JLabel lblContrasena;
    private JLabel lblInformacion;

    // Cajas de texto
    private JTextField txtNombre;
    private JTextField txtEdad;
    private JTextField txtCorreo;
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;

    // Área de texto
    private JTextArea areaInformacion;
    private JScrollPane scrollInformacion;

    // Botones
    private JButton btnNuevo;
    private JButton btnAgregar;

    public Ventana() {
        setTitle("Práctica 1");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        colocarPanel();
        colocarEtiquetas();
        colocarCajasDeTexto();
        colocarAreaDeTexto();
        colocarBotones();
    }

    private void colocarPanel() {
        panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel);
    }

    private void colocarEtiquetas() {
        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 30, 100, 28);
        panel.add(lblNombre);

        lblEdad = new JLabel("Edad:");
        lblEdad.setBounds(20, 80, 100, 28);
        panel.add(lblEdad);

        lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(20, 130, 100, 28);
        panel.add(lblCorreo);

        lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(20, 180, 100, 28);
        panel.add(lblUsuario);

        lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(20, 230, 100, 28);
        panel.add(lblContrasena);

        lblInformacion = new JLabel("Información");
        lblInformacion.setBounds(385, 25, 120, 28);
        panel.add(lblInformacion);
    }

    private void colocarCajasDeTexto() {
        txtNombre = new JTextField();
        txtNombre.setBounds(135, 30, 210, 28);
        panel.add(txtNombre);

        txtEdad = new JTextField();
        txtEdad.setBounds(135, 80, 210, 28);
        panel.add(txtEdad);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(135, 130, 210, 28);
        panel.add(txtCorreo);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(135, 180, 210, 28);
        panel.add(txtUsuario);

        txtContrasena = new JPasswordField();
        txtContrasena.setEchoChar('*');
        txtContrasena.setBounds(135, 230, 210, 28);
        panel.add(txtContrasena);
    }

    private void colocarAreaDeTexto() {
        areaInformacion = new JTextArea();
        areaInformacion.setEditable(false);

        scrollInformacion = new JScrollPane(areaInformacion);
        scrollInformacion.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollInformacion.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollInformacion.setBounds(385, 70, 290, 190);
        panel.add(scrollInformacion);
    }

    private void colocarBotones() {
        btnNuevo = new JButton("Nuevo");
        btnNuevo.setBounds(240, 300, 90, 30);
        btnNuevo.addActionListener(e -> limpiarCampos());
        panel.add(btnNuevo);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(390, 300, 90, 30);
        btnAgregar.addActionListener(e -> agregarInformacion());
        panel.add(btnAgregar);
    }

    /** Botón Nuevo: deja el formulario listo para otra captura. */
    private void limpiarCampos() {
        txtNombre.setText("");
        txtEdad.setText("");
        txtCorreo.setText("");
        txtUsuario.setText("");
        txtContrasena.setText("");
        txtNombre.requestFocus();
    }

    /** Botón Agregar: valida los datos y los muestra en el área de texto. */
    private void agregarInformacion() {
        String nombre = txtNombre.getText().trim();
        String edad = txtEdad.getText().trim();
        String correo = txtCorreo.getText().trim();
        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword());

        if (nombre.isEmpty() || edad.isEmpty() || correo.isEmpty()
                || usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            if (Integer.parseInt(edad) <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La edad debe ser un número entero positivo.",
                    "Edad no válida", JOptionPane.ERROR_MESSAGE);
            txtEdad.requestFocus();
            return;
        }

        areaInformacion.append("Nombre: " + nombre + "\n");
        areaInformacion.append("Edad: " + edad + "\n");
        areaInformacion.append("Correo: " + correo + "\n");
        areaInformacion.append("Usuario: " + usuario + "\n");
        areaInformacion.append("Contraseña: " + "*".repeat(contrasena.length()) + "\n");
        areaInformacion.append("------------------------------\n");

        limpiarCampos();
    }
}

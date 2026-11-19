package practica3;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Práctica 3 - Formulario con JLabel, JTextField, JComboBox, JCheckBox,
 * JFileChooser (incrustado en la ventana) y JButton.
 */
public class Ventana extends JFrame {

    // Contenedor
    private JPanel panel;

    // Etiquetas
    private JLabel lblNombre;
    private JLabel lblDireccion;
    private JLabel lblSexo;
    private JLabel lblTelefono;
    private JLabel lblNumero;
    private JLabel lblFotografia;
    private JLabel lblFoto;

    // Cajas de texto
    private JTextField txtNombre;
    private JTextField txtDireccion;
    private JTextField txtNumero;

    // Lista desplegable
    private JComboBox<String> cmbSexo;

    // Casillas de verificación
    private JCheckBox chkMovil;
    private JCheckBox chkCasa;

    // Selector de archivos
    private JFileChooser selectorArchivo;
    private File archivoFoto;

    // Botones
    private JButton btnNuevo;
    private JButton btnGuardar;

    public Ventana() {
        setTitle("Práctica 3");
        setSize(640, 680);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        colocarPanel();
        colocarEtiquetas();
        colocarCajasDeTexto();
        colocarListaDesplegable();
        colocarCasillas();
        colocarSelectorArchivo();
        colocarBotones();
    }

    private void colocarPanel() {
        panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel);
    }

    private void colocarEtiquetas() {
        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 30, 90, 28);
        panel.add(lblNombre);

        lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(20, 75, 90, 28);
        panel.add(lblDireccion);

        lblSexo = new JLabel("Sexo:");
        lblSexo.setBounds(20, 120, 90, 28);
        panel.add(lblSexo);

        lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(20, 165, 90, 28);
        panel.add(lblTelefono);

        lblNumero = new JLabel("Número:");
        lblNumero.setBounds(20, 210, 90, 28);
        panel.add(lblNumero);

        lblFotografia = new JLabel("Fotografía:");
        lblFotografia.setBounds(20, 400, 90, 28);
        panel.add(lblFotografia);

        // Etiqueta donde se muestra la fotografía seleccionada
        lblFoto = new JLabel("Foto", SwingConstants.CENTER);
        lblFoto.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblFoto.setBounds(320, 25, 290, 215);
        panel.add(lblFoto);
    }

    private void colocarCajasDeTexto() {
        txtNombre = new JTextField();
        txtNombre.setBounds(120, 30, 170, 28);
        panel.add(txtNombre);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(120, 75, 170, 28);
        panel.add(txtDireccion);

        txtNumero = new JTextField();
        txtNumero.setBounds(120, 210, 170, 28);
        panel.add(txtNumero);
    }

    private void colocarListaDesplegable() {
        cmbSexo = new JComboBox<>();
        cmbSexo.addItem("Femenino");
        cmbSexo.addItem("Masculino");
        cmbSexo.setBounds(120, 120, 170, 28);
        panel.add(cmbSexo);
    }

    private void colocarCasillas() {
        chkMovil = new JCheckBox("Movil");
        chkMovil.setBounds(120, 165, 80, 28);
        panel.add(chkMovil);

        chkCasa = new JCheckBox("Casa");
        chkCasa.setBounds(210, 165, 80, 28);
        panel.add(chkCasa);
    }

    private void colocarSelectorArchivo() {
        selectorArchivo = new JFileChooser();
        selectorArchivo.setFileFilter(new FileNameExtensionFilter(
                "Imágenes (jpg, jpeg, png, gif)", "jpg", "jpeg", "png", "gif"));
        selectorArchivo.setBounds(120, 265, 490, 300);
        selectorArchivo.addActionListener(e -> {
            if (JFileChooser.APPROVE_SELECTION.equals(e.getActionCommand())) {
                cargarFoto(selectorArchivo.getSelectedFile());
            } else if (JFileChooser.CANCEL_SELECTION.equals(e.getActionCommand())) {
                quitarFoto();
            }
        });
        panel.add(selectorArchivo);
    }

    private void colocarBotones() {
        btnNuevo = new JButton("Nuevo");
        btnNuevo.setBounds(210, 590, 90, 30);
        btnNuevo.addActionListener(e -> limpiarCampos());
        panel.add(btnNuevo);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(340, 590, 90, 30);
        btnGuardar.addActionListener(e -> guardar());
        panel.add(btnGuardar);
    }

    /** Botón Abrir del selector: muestra la imagen elegida en la etiqueta Foto. */
    private void cargarFoto(File archivo) {
        if (archivo == null || !archivo.isFile()) {
            return;
        }
        ImageIcon original = new ImageIcon(archivo.getAbsolutePath());
        if (original.getIconWidth() <= 0) {
            JOptionPane.showMessageDialog(this, "El archivo seleccionado no es una imagen válida.",
                    "Fotografía", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Se ajusta la imagen al tamaño de la etiqueta conservando su proporción
        double escala = Math.min(
                (double) lblFoto.getWidth() / original.getIconWidth(),
                (double) lblFoto.getHeight() / original.getIconHeight());
        int ancho = Math.max(1, (int) (original.getIconWidth() * escala));
        int alto = Math.max(1, (int) (original.getIconHeight() * escala));
        Image ajustada = original.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

        archivoFoto = archivo;
        lblFoto.setText("");
        lblFoto.setIcon(new ImageIcon(ajustada));
    }

    /** Botón Cancelar del selector: quita la fotografía. */
    private void quitarFoto() {
        archivoFoto = null;
        selectorArchivo.setSelectedFile(null);
        lblFoto.setIcon(null);
        lblFoto.setText("Foto");
    }

    /** Botón Nuevo: deja el formulario listo para otra captura. */
    private void limpiarCampos() {
        txtNombre.setText("");
        txtDireccion.setText("");
        txtNumero.setText("");
        cmbSexo.setSelectedIndex(0);
        chkMovil.setSelected(false);
        chkCasa.setSelected(false);
        quitarFoto();
        txtNombre.requestFocus();
    }

    /** Botón Guardar: valida los datos y muestra el resumen capturado. */
    private void guardar() {
        String nombre = txtNombre.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String numero = txtNumero.getText().trim();

        if (nombre.isEmpty() || direccion.isEmpty() || numero.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre, dirección y número son obligatorios.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!numero.matches("\\d{7,10}")) {
            JOptionPane.showMessageDialog(this, "El número debe tener de 7 a 10 dígitos.",
                    "Número no válido", JOptionPane.ERROR_MESSAGE);
            txtNumero.requestFocus();
            return;
        }
        if (!chkMovil.isSelected() && !chkCasa.isSelected()) {
            JOptionPane.showMessageDialog(this, "Seleccione el tipo de teléfono (Movil o Casa).",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String tipoTelefono;
        if (chkMovil.isSelected() && chkCasa.isSelected()) {
            tipoTelefono = "Movil y Casa";
        } else if (chkMovil.isSelected()) {
            tipoTelefono = "Movil";
        } else {
            tipoTelefono = "Casa";
        }

        String foto = (archivoFoto == null) ? "Sin fotografía" : archivoFoto.getName();

        JOptionPane.showMessageDialog(this,
                "Nombre: " + nombre + "\n"
                + "Dirección: " + direccion + "\n"
                + "Sexo: " + cmbSexo.getSelectedItem() + "\n"
                + "Teléfono: " + tipoTelefono + "\n"
                + "Número: " + numero + "\n"
                + "Fotografía: " + foto,
                "Datos guardados", JOptionPane.INFORMATION_MESSAGE);
    }
}

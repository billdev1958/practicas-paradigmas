package practica4;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Práctica 4 - Búsqueda por matrícula con JLabel, JTextField,
 * JButton y JTable.
 */
public class Ventana extends JFrame {

    // Alumnos de ejemplo: matrícula, nombre, edad, teléfono, correo
    private final String[][] alumnos = {
        {"2026001", "Ana López", "19", "7221234567", "ana.lopez@correo.com"},
        {"2026002", "Luis Hernández", "20", "7222345678", "luis.hdz@correo.com"},
        {"2026003", "María García", "21", "7223456789", "maria.garcia@correo.com"},
        {"2026004", "Carlos Sánchez", "19", "7224567890", "carlos.sanchez@correo.com"},
        {"2026005", "Sofía Martínez", "22", "7225678901", "sofia.mtz@correo.com"}
    };

    // Contenedor
    private JPanel panel;

    // Etiqueta
    private JLabel lblMatricula;

    // Caja de texto
    private JTextField txtMatricula;

    // Botones
    private JButton btnBuscar;
    private JButton btnNuevo;

    // Tabla
    private DefaultTableModel modeloTabla;
    private JTable tablaAlumnos;
    private JScrollPane scrollTabla;

    public Ventana() {
        setTitle("Práctica4");
        setSize(760, 440);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        colocarPanel();
        colocarEtiqueta();
        colocarCajaDeTexto();
        colocarBotones();
        colocarTabla();
    }

    private void colocarPanel() {
        panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel);
    }

    private void colocarEtiqueta() {
        lblMatricula = new JLabel("Matricula:");
        lblMatricula.setBounds(60, 50, 80, 30);
        panel.add(lblMatricula);
    }

    private void colocarCajaDeTexto() {
        txtMatricula = new JTextField();
        txtMatricula.setBounds(145, 50, 150, 30);
        // Enter en la caja de texto equivale a presionar Buscar
        txtMatricula.addActionListener(e -> buscar());
        panel.add(txtMatricula);
    }

    private void colocarBotones() {
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(440, 50, 85, 30);
        btnBuscar.addActionListener(e -> buscar());
        panel.add(btnBuscar);

        btnNuevo = new JButton("Nuevo");
        btnNuevo.setBounds(545, 50, 85, 30);
        btnNuevo.addActionListener(e -> nuevo());
        panel.add(btnNuevo);
    }

    private void colocarTabla() {
        String[] columnas = {"Matricula", "Nombre", "Edad", "Teléfono", "Correo"};

        // Modelo sin filas y con celdas no editables
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaAlumnos = new JTable(modeloTabla);
        tablaAlumnos.setFillsViewportHeight(true);
        tablaAlumnos.getTableHeader().setReorderingAllowed(false);

        scrollTabla = new JScrollPane(tablaAlumnos);
        scrollTabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollTabla.setBounds(60, 120, 630, 240);
        panel.add(scrollTabla);
    }

    /** Botón Buscar: muestra en la tabla al alumno con la matrícula indicada. */
    private void buscar() {
        String matricula = txtMatricula.getText().trim();

        if (matricula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escriba una matrícula.",
                    "Buscar", JOptionPane.WARNING_MESSAGE);
            txtMatricula.requestFocus();
            return;
        }

        modeloTabla.setRowCount(0);
        for (String[] alumno : alumnos) {
            if (alumno[0].equals(matricula)) {
                modeloTabla.addRow(alumno);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "No se encontró la matrícula " + matricula + ".",
                "Buscar", JOptionPane.INFORMATION_MESSAGE);
    }

    /** Botón Nuevo: limpia la caja de texto y la tabla. */
    private void nuevo() {
        txtMatricula.setText("");
        modeloTabla.setRowCount(0);
        txtMatricula.requestFocus();
    }
}

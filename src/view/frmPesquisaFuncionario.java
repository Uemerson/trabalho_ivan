package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class frmPesquisaFuncionario extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JButton btnConfirma;

	
	public frmPesquisaFuncionario() {
		setClosable(true);
		setTitle("Pesquisar Funcion\u00E1rio");
		setBounds(100, 100, 808, 515);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 772, 103);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Registro");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(10, 23, 74, 28);
		panel.add(label);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setColumns(10);
		textField.setBounds(73, 23, 68, 28);
		panel.add(textField);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(151, 23, 43, 28);
		panel.add(lblNome);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(204, 23, 236, 28);
		panel.add(textField_1);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setHorizontalAlignment(SwingConstants.LEFT);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpf.setBounds(450, 23, 35, 28);
		panel.add(lblCpf);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(486, 23, 98, 28);
		panel.add(formattedTextField);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(640, 23, 122, 28);
		panel.add(comboBox);
		
		JLabel label_1 = new JLabel("Cargo");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(594, 23, 48, 28);
		panel.add(label_1);
		
		JButton btnNewButton = new JButton("Limpar Filtros");
		btnNewButton.setBounds(640, 62, 122, 28);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 125, 772, 316);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setColumnHeaderView(table);
		
		btnConfirma = new JButton("Confirma");
		btnConfirma.setBounds(660, 446, 122, 28);
		btnConfirma.setVisible(false);
		getContentPane().add(btnConfirma);

	}
}

package view;

import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import dao.DAOCargo;
import dao.DAOConexaoMySQL;
import dao.DAOFuncionario;
import model.Funcionario;
import tableModel.FuncionarioTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmPesquisaFuncionario extends JInternalFrame implements FocusListener, DocumentListener, ActionListener {
	private JTextField txtNome;
	private JTable tbTabelaFuncionario;
	private JButton btnConfirma;
	private JFormattedTextField txtCPF;
	private JComboBox cbCargo;
	private JButton btnLimparFiltros;
	private JLabel lblRegistro;
	private JLabel lblNome;
	private JLabel lblCpf;
	private JLabel lblCargo;

	private static frmPesquisaFuncionario singleton = null;
	private JComboBox cbRegistro;
	private JPanel pnlFiltros;
	private JScrollPane srpPainelTabela;

	public static frmPesquisaFuncionario getFrmPesquisaFuncionario() throws ParseException, SQLException {
		if (singleton == null) {
			singleton = new frmPesquisaFuncionario();
		}

		return singleton;
	}

	public frmPesquisaFuncionario() throws SQLException {
		setClosable(true);
		setTitle("Pesquisar Funcion\u00E1rio");
		setBounds(100, 100, 808, 515);
		getContentPane().setLayout(null);

		DAOFuncionario daoFuncionario = new DAOFuncionario();
		DAOCargo daoCargo = new DAOCargo();

		pnlFiltros = new JPanel();
		pnlFiltros.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlFiltros.setBounds(10, 11, 772, 103);
		getContentPane().add(pnlFiltros);
		pnlFiltros.setLayout(null);

		lblRegistro = new JLabel("Registro");
		lblRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegistro.setBounds(10, 23, 74, 28);
		pnlFiltros.add(lblRegistro);

		lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(151, 23, 43, 28);
		pnlFiltros.add(lblNome);

		txtNome = new JTextField();
		txtNome.addFocusListener(this);
		txtNome.setColumns(10);
		txtNome.setBounds(204, 23, 236, 28);
		txtNome.getDocument().addDocumentListener(this);
		pnlFiltros.add(txtNome);

		lblCpf = new JLabel("CPF");
		lblCpf.setHorizontalAlignment(SwingConstants.LEFT);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpf.setBounds(450, 23, 35, 28);
		pnlFiltros.add(lblCpf);

		txtCPF = new JFormattedTextField();
		txtCPF.addFocusListener(this);
		txtCPF.setBounds(486, 23, 98, 28);
		pnlFiltros.add(txtCPF);

		cbCargo = new JComboBox();
		cbCargo.setBounds(640, 23, 122, 28);

		for (int i = 0; i < daoCargo.listaCargo().size(); i++) {
			cbCargo.addItem(daoCargo.listaCargo().get(i).getNome());
		}

		AutoCompleteDecorator.decorate(cbCargo);
		cbCargo.setSelectedItem(null);
		pnlFiltros.add(cbCargo);

		lblCargo = new JLabel("Cargo");
		lblCargo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCargo.setBounds(594, 23, 48, 28);
		pnlFiltros.add(lblCargo);

		btnLimparFiltros = new JButton("Limpar Filtros");
		btnLimparFiltros.setBounds(640, 62, 122, 28);
		pnlFiltros.add(btnLimparFiltros);

		cbRegistro = new JComboBox();
		cbRegistro.setBounds(69, 23, 72, 28);

		// Preenche a comboBox com os registros
		for (int i = 0; i < daoFuncionario.listaFuncionario().size(); i++) {
			cbRegistro.addItem(daoFuncionario.listaFuncionario().get(i).getRegistro());
		}

		AutoCompleteDecorator.decorate(cbRegistro);
		cbRegistro.setSelectedItem(null);
		cbRegistro.addActionListener(this);

		pnlFiltros.add(cbRegistro);

		srpPainelTabela = new JScrollPane();
		srpPainelTabela.setBounds(10, 125, 772, 316);
		getContentPane().add(srpPainelTabela);

		tbTabelaFuncionario = new JTable();
		FuncionarioTableModel model = new FuncionarioTableModel();
		tbTabelaFuncionario.setModel(model);

		// Preenche a Table com a lista de usuarios
		model.addListaDeFuncionario(daoFuncionario.listaFuncionario());

		tbTabelaFuncionario.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		srpPainelTabela.setViewportView(tbTabelaFuncionario);

		btnConfirma = new JButton("Voltar ao cadastro de funcionario");
		btnConfirma.addActionListener(this);
		btnConfirma.setBounds(562, 446, 220, 28);
		btnConfirma.setVisible(false);
		getContentPane().add(btnConfirma);

	}

	public void focusGained(FocusEvent e) {
		try {

			if (e.getSource() == txtNome) {
				txtNome_focusGained();
			} else if (e.getSource() == txtCPF) {
				txtCPF_focusGained();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private void txtNome_focusGained() throws SQLException {
		cbRegistro.setSelectedItem(null);
		txtCPF.setText(null);
		cbCargo.setSelectedItem(null);

		DAOFuncionario dao = new DAOFuncionario();
		FuncionarioTableModel model = new FuncionarioTableModel();
		model.addListaDeFuncionario(dao.listaFuncionario());
		tbTabelaFuncionario.setModel(model);
	}

	private void txtCPF_focusGained() throws SQLException {
		cbRegistro.setSelectedItem(null);
		txtNome.setText(null);
		cbCargo.setSelectedItem(null);

		DAOFuncionario dao = new DAOFuncionario();
		FuncionarioTableModel model = new FuncionarioTableModel();
		model.addListaDeFuncionario(dao.listaFuncionario());
		tbTabelaFuncionario.setModel(model);
	}

	public void focusLost(FocusEvent e) {
	}

	@Override
	public void changedUpdate(DocumentEvent e) {

	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		if (e.getDocument() == txtNome.getDocument()) {
			try {
				txtNome_documentUpdate();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		if (e.getDocument() == txtNome.getDocument()) {
			try {
				txtNome_documentUpdate();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	private void txtNome_documentUpdate() throws SQLException {
		DAOFuncionario dao = new DAOFuncionario();

		FuncionarioTableModel model = new FuncionarioTableModel();
		model.addListaDeFuncionario(dao.listaFuncionario(txtNome.getText()));

		tbTabelaFuncionario.setModel(model);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == cbRegistro) {
				cbRegistro_click();
			} else if (e.getSource() == btnConfirma) {
				btnConfirma_click();
			}
		} catch (NumberFormatException | SQLException | ParseException ex) {
			ex.printStackTrace();
		}
	}

	private void cbRegistro_click() throws NumberFormatException, SQLException {
		txtNome.setText(null);
		txtCPF.setText(null);
		cbCargo.setSelectedItem(null);

		if (cbRegistro.getSelectedIndex() > -1) {
			DAOFuncionario dao = new DAOFuncionario();
			FuncionarioTableModel model = new FuncionarioTableModel();
			model.addListaDeFuncionario(
					dao.listaFuncionario(Integer.parseInt(cbRegistro.getSelectedItem().toString())));
			tbTabelaFuncionario.setModel(model);
		}
	}

	private void btnConfirma_click() throws ParseException, SQLException {
		if (btnConfirma.getText().equals("Volta ao cadastro de funcionario")) {
			frmCadastroFuncionario.getFrmCadastroFuncionario().preencheCadastro(new Funcionario());
		}
	}

	public JButton getBtnConfirma() {
		return btnConfirma;
	}
}

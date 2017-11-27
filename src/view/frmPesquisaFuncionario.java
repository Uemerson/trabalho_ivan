package view;

import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataListener;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import controler.ComboKeyHandler;
import controler.FuncoesGlobais;
import dao.DAOCargo;
import dao.DAOConexaoMySQL;
import dao.DAOFuncionario;
import javafx.scene.control.ComboBox;
import model.Funcionario;
import tableModel.FuncionarioTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmPesquisaFuncionario extends JInternalFrame implements FocusListener, DocumentListener, ActionListener {
	private JTextField txtNome;
	private JTable tbTabelaFuncionario;
	private JButton btnAbrirCadastroFuncionario;
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

		cbRegistro = new JComboBox();
		cbRegistro.setBounds(69, 23, 72, 28);
		
		// Preenche a comboBox com os registros
		DefaultComboBoxModel<String> modelCbRegistro = new DefaultComboBoxModel<>();
		
		for (int i = 0; i < daoFuncionario.listaFuncionario().size(); i++) {
			modelCbRegistro.addElement(Integer.toString(daoFuncionario.listaFuncionario().get(i).getRegistro()));
		}
		
		cbRegistro.setModel(modelCbRegistro);
		cbRegistro.setSelectedItem(null);
		cbRegistro.setEditable(true);
		JTextField edtCbRegistro = (JTextField) cbRegistro.getEditor().getEditorComponent();
		edtCbRegistro.addKeyListener(new ComboKeyHandler(cbRegistro));
		edtCbRegistro.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				txtNome.setText(null);
				txtCPF.setText(null);
				cbCargo.setSelectedItem(null);
			}
		});

		cbRegistro.addActionListener(this);
		pnlFiltros.add(cbRegistro);
		
		cbCargo = new JComboBox();
		cbCargo.setBounds(640, 23, 122, 28);
		
		// Preenche a comboBox com os cargos
		DefaultComboBoxModel<String> modelCbCargo = new DefaultComboBoxModel<>();
		
		for (int i = 0; i < daoCargo.listaCargo().size(); i++) {
			modelCbCargo.addElement(daoCargo.listaCargo().get(i).getNome());
		}
		
		cbCargo.setModel(modelCbCargo);
		cbCargo.setSelectedItem(null);
		cbCargo.setEditable(true);
		JTextField edtCbCargo = (JTextField) cbCargo.getEditor().getEditorComponent();
		edtCbCargo.addKeyListener(new ComboKeyHandler(cbCargo));
		edtCbCargo.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				txtNome.setText(null);
				txtCPF.setText(null);
				cbRegistro.setSelectedItem(null);
			}
		});
		
		pnlFiltros.add(cbCargo);

		lblCargo = new JLabel("Cargo");
		lblCargo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCargo.setBounds(594, 23, 48, 28);
		pnlFiltros.add(lblCargo);

		btnLimparFiltros = new JButton("Limpar Filtros");
		btnLimparFiltros.setBounds(640, 62, 122, 28);
		pnlFiltros.add(btnLimparFiltros);

		srpPainelTabela = new JScrollPane();
		srpPainelTabela.setBounds(10, 125, 772, 316);
		getContentPane().add(srpPainelTabela);

		tbTabelaFuncionario = new JTable();
		tbTabelaFuncionario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		FuncionarioTableModel model = new FuncionarioTableModel();
		tbTabelaFuncionario.setModel(model);

		// Preenche a Table com a lista de usuarios
		model.addListaDeFuncionario(daoFuncionario.listaFuncionario());

		tbTabelaFuncionario.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		srpPainelTabela.setViewportView(tbTabelaFuncionario);

		btnAbrirCadastroFuncionario = new JButton("Abrir cadastro de funcionario");
		btnAbrirCadastroFuncionario.addActionListener(this);
		btnAbrirCadastroFuncionario.setBounds(594, 446, 188, 28);
		getContentPane().add(btnAbrirCadastroFuncionario);

		JButton btnAbrirCadastroDeUsuario = new JButton("Abrir cadastro de usuario");
		btnAbrirCadastroDeUsuario.setBounds(404, 446, 180, 28);
		getContentPane().add(btnAbrirCadastroDeUsuario);
	}

	public void focusGained(FocusEvent e) {
		System.out.println("teste");
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
			} else if (e.getSource() == btnAbrirCadastroFuncionario) {
				btnAbrirCadastroFuncionario_click();
			} else if (e.getSource() == cbCargo) {
				cbCargo_click();
			}
		} catch (NumberFormatException | SQLException | ParseException | PropertyVetoException ex) {
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
			model.addListaDeFuncionario(dao.listaFuncionario(Integer.parseInt(cbRegistro.getSelectedItem().toString())));
			tbTabelaFuncionario.setModel(model);
		}
	}
	
	private void cbCargo_click() throws NumberFormatException, SQLException {
		txtNome.setText(null);
		txtCPF.setText(null);	
		cbRegistro.setSelectedItem(null);
		
		if (cbCargo.getSelectedIndex() > -1) {
			DAOFuncionario dao = new DAOFuncionario();
			FuncionarioTableModel model = new FuncionarioTableModel();
			model.addListaDeFuncionario(dao.listaFuncionarioCargo(cbCargo.getSelectedItem().toString()));
			tbTabelaFuncionario.setModel(model);
		}
	}
	
	private void btnAbrirCadastroFuncionario_click() throws ParseException, SQLException, PropertyVetoException {
		// frmCadastroFuncionario.getFrmCadastroFuncionario().preencheCadastro();
		if (tbTabelaFuncionario.getSelectedRow() > -1) {
			FuncionarioTableModel model = new FuncionarioTableModel();
			model = (FuncionarioTableModel) tbTabelaFuncionario.getModel();

			frmCadastroFuncionario.getFrmCadastroFuncionario();

			if (frmCadastroFuncionario.getFrmCadastroFuncionario().isVisible()) {
				frmCadastroFuncionario.getFrmCadastroFuncionario().preencheCadastro(new DAOFuncionario().buscaFuncionario(model.getFuncionario(tbTabelaFuncionario.getSelectedRow()).getRegistro()));
				frmCadastroFuncionario.getFrmCadastroFuncionario().setSelected(true);
			}else {
				
				frmCadastroFuncionario.getFrmCadastroFuncionario().setVisible(true);
				frmMenu.getFrmMenu().getDskPrincipal().add(frmCadastroFuncionario.getFrmCadastroFuncionario());
				frmCadastroFuncionario.getFrmCadastroFuncionario().setSelected(true);
				frmCadastroFuncionario.getFrmCadastroFuncionario().preencheCadastro(new DAOFuncionario().buscaFuncionario(model.getFuncionario(tbTabelaFuncionario.getSelectedRow()).getRegistro()));
				
			}
			
		}else {
			JOptionPane.showMessageDialog(this, "Selecione um registro para abrir o cadastro de funcionario!", "Sistema", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void resetaFormulario() throws SQLException {
		cbRegistro.removeAllItems();
		cbCargo.removeAllItems();
		
		DAOCargo daoCargo = new DAOCargo();
		DAOFuncionario daoFuncionario = new DAOFuncionario();

		for (int i = 0; i < daoCargo.listaCargo().size(); i++) {
			cbCargo.addItem(daoCargo.listaCargo().get(i).getNome());
		}

		for (int i = 0; i < daoFuncionario.listaFuncionario().size(); i++) {
			cbRegistro.addItem(daoFuncionario.listaFuncionario().get(i).getRegistro());
		}

		FuncionarioTableModel model = new FuncionarioTableModel();
		model.addListaDeFuncionario(daoFuncionario.listaFuncionario());

		tbTabelaFuncionario.setModel(model);
		FuncoesGlobais.limpaCampos(pnlFiltros);

	}

}

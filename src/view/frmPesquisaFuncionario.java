package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.ButtonGroup;
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

import controler.ComboKeyHandler;
import controler.FuncoesGlobais;
import dao.DAOCargo;
import dao.DAOFuncionario;
import tableModel.FuncionarioTableModel;
import javax.swing.event.InternalFrameListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JRadioButton;

public class frmPesquisaFuncionario extends JInternalFrame implements DocumentListener, ActionListener, InternalFrameListener {
	private JTable tbTabelaFuncionario;
	private JButton btnAbrirCadastroFuncionario;

	private static frmPesquisaFuncionario singleton = null;
	private JPanel pnlFiltros;
	private JScrollPane srpPainelTabela;
	private JRadioButton rdbtnFiltrarPorCargo;
	private JRadioButton rdbtnFiltrarPorRegistro;
	private JRadioButton rdbtnFiltrarPorNome;
	private JRadioButton rdbtnFiltrarPorCpf;
	private JTextField txtBuscarPor;
	private JLabel lblBuscarPor;

	public static frmPesquisaFuncionario getInstance() throws ParseException, SQLException {

		if (singleton == null) {
			singleton = new frmPesquisaFuncionario();
		}

		return singleton;
	}

	public frmPesquisaFuncionario() throws SQLException {
		addInternalFrameListener(this);
		setClosable(true);
		setTitle("Pesquisar Funcion\u00E1rio");
		setBounds(100, 100, 808, 515);
		getContentPane().setLayout(null);

		// Hack para remover icone do nimbus
		Container pane = ((BasicInternalFrameUI) this.getUI()).getNorthPane();
		// pane.remove(0);
		pane.getComponent(0).setVisible(false);

		DAOFuncionario daoFuncionario = new DAOFuncionario();

		pnlFiltros = new JPanel();
		pnlFiltros.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlFiltros.setBounds(10, 11, 772, 84);
		getContentPane().add(pnlFiltros);
		pnlFiltros.setLayout(null);

		ButtonGroup btngpGrupoFiltros = new ButtonGroup();

		rdbtnFiltrarPorRegistro = new JRadioButton("Filtrar por registro");
		rdbtnFiltrarPorRegistro.addActionListener(this);
		rdbtnFiltrarPorRegistro.setSelected(true);
		rdbtnFiltrarPorRegistro.setBounds(10, 13, 122, 23);
		pnlFiltros.add(rdbtnFiltrarPorRegistro);

		rdbtnFiltrarPorNome = new JRadioButton("Filtrar por nome");
		rdbtnFiltrarPorNome.setBounds(144, 13, 122, 23);
		pnlFiltros.add(rdbtnFiltrarPorNome);

		rdbtnFiltrarPorCpf = new JRadioButton("Filtrar por CPF");
		rdbtnFiltrarPorCpf.setBounds(266, 13, 122, 23);
		pnlFiltros.add(rdbtnFiltrarPorCpf);

		rdbtnFiltrarPorCargo = new JRadioButton("Filtrar por cargo");
		rdbtnFiltrarPorCargo.setBounds(387, 13, 122, 23);
		pnlFiltros.add(rdbtnFiltrarPorCargo);

		btngpGrupoFiltros.add(rdbtnFiltrarPorNome);
		btngpGrupoFiltros.add(rdbtnFiltrarPorCpf);
		btngpGrupoFiltros.add(rdbtnFiltrarPorRegistro);
		btngpGrupoFiltros.add(rdbtnFiltrarPorCargo);

		lblBuscarPor = new JLabel("Buscar por registro");
		lblBuscarPor.setBounds(10, 50, 122, 14);
		pnlFiltros.add(lblBuscarPor);

		txtBuscarPor = new JTextField();
		txtBuscarPor.setBounds(144, 43, 365, 28);
		pnlFiltros.add(txtBuscarPor);
		txtBuscarPor.setColumns(10);
		txtBuscarPor.getDocument().addDocumentListener(this);

		srpPainelTabela = new JScrollPane();
		srpPainelTabela.setBounds(10, 107, 772, 334);
		getContentPane().add(srpPainelTabela);

		tbTabelaFuncionario = new JTable();
		tbTabelaFuncionario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		FuncionarioTableModel model = new FuncionarioTableModel();
		tbTabelaFuncionario.setModel(model);

		// Preenche a Table com a lista de usuarios
		model.addListaDeFuncionario(daoFuncionario.listaFuncionario());
		srpPainelTabela.setViewportView(tbTabelaFuncionario);

		btnAbrirCadastroFuncionario = new JButton("Abrir cadastro de funcionario");
		btnAbrirCadastroFuncionario.addActionListener(this);
		btnAbrirCadastroFuncionario.setBounds(594, 446, 188, 28);
		getContentPane().add(btnAbrirCadastroFuncionario);

		JButton btnAbrirCadastroDeUsuario = new JButton("Abrir cadastro de usuario");
		btnAbrirCadastroDeUsuario.setBounds(404, 446, 180, 28);
		getContentPane().add(btnAbrirCadastroDeUsuario);
	}

	public void focusLost(FocusEvent e) {
	}

	@Override
	public void changedUpdate(DocumentEvent e) {

	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		try {
			if (e.getDocument() == txtBuscarPor.getDocument()) {
				txtBuscarPor_documentUpdate();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		try {
			if (e.getDocument() == txtBuscarPor.getDocument()) {
				txtBuscarPor_documentUpdate();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private void txtBuscarPor_documentUpdate() throws SQLException {
		DAOFuncionario dao = new DAOFuncionario();
		System.out.println(dao.buscaFuncionario(0));
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == rdbtnFiltrarPorCargo) {
				rdbtnFiltrarPorCargo_click();
			} else if (e.getSource() == btnAbrirCadastroFuncionario) {
				btnAbrirCadastroFuncionario_click();
			}
		} catch (ParseException | SQLException | PropertyVetoException ex) {
			JOptionPane.showMessageDialog(this, "Erro ao tentar abrir o cadastro de funcionario!", "Sistema", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void btnAbrirCadastroFuncionario_click() throws ParseException, SQLException, PropertyVetoException {
		if (tbTabelaFuncionario.getSelectedRow() > -1) {
			FuncionarioTableModel model = new FuncionarioTableModel();
			model = (FuncionarioTableModel) tbTabelaFuncionario.getModel();

			frmCadastroFuncionario.getInstance();

			if (frmCadastroFuncionario.getInstance().isVisible()) {
				frmCadastroFuncionario.getInstance().preencheCadastro(new DAOFuncionario()
						.buscaFuncionario(model.getFuncionario(tbTabelaFuncionario.getSelectedRow()).getRegistro()));
				frmCadastroFuncionario.getInstance().setSelected(true);
			} else {

				frmCadastroFuncionario.getInstance().setVisible(true);
				frmMenu.getFrmMenu().getDskPrincipal().add(frmCadastroFuncionario.getInstance());
				frmCadastroFuncionario.getInstance().setSelected(true);
				frmCadastroFuncionario.getInstance().preencheCadastro(new DAOFuncionario()
						.buscaFuncionario(model.getFuncionario(tbTabelaFuncionario.getSelectedRow()).getRegistro()));
			}

			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "Selecione um registro para abrir o cadastro de funcionario!",
					"Sistema", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void rdbtnFiltrarPorCargo_click() {

	}

	// Atualiza os dados dos funcionarios cadastrados
	public void atualizaDados() throws SQLException {

		DAOCargo daoCargo = new DAOCargo();
		DAOFuncionario daoFuncionario = new DAOFuncionario();

		FuncionarioTableModel model = new FuncionarioTableModel();
		model.addListaDeFuncionario(daoFuncionario.listaFuncionario());

		tbTabelaFuncionario.setModel(model);
		FuncoesGlobais.limpaCampos(pnlFiltros);
	}

	public void internalFrameActivated(InternalFrameEvent e) {
	}

	public void internalFrameClosed(InternalFrameEvent e) {
		this.singleton = null;
	}

	public void internalFrameClosing(InternalFrameEvent e) {
		
	}

	public void internalFrameDeactivated(InternalFrameEvent e) {
	}

	public void internalFrameDeiconified(InternalFrameEvent e) {
	}

	public void internalFrameIconified(InternalFrameEvent e) {
	}

	public void internalFrameOpened(InternalFrameEvent e) {
	}
}

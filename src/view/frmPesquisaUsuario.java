package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import controler.ComboKeyHandler;
import controler.FuncoesGlobais;
import dao.DAOCargo;
import dao.DAOFuncionario;
import tableModel.FuncionarioTableModel;
import tableModel.UsuarioTableModel;
import javax.swing.table.DefaultTableModel;

public class frmPesquisaUsuario extends JInternalFrame implements ActionListener, InternalFrameListener {
	private JTextField txtLogin;
	private JTable tbTabelaUsuario;
	private JButton btnLimparFiltros;
	private JLabel lblRegistro;
	private JLabel lblNome;

	private static frmPesquisaUsuario singleton = null;
	private JComboBox cbRegistro;
	private JPanel pnlFiltros;
	private JScrollPane srpPainelTabela;
	private UsuarioTableModel usuarioTableModel;

	public static frmPesquisaUsuario getInstance() throws ParseException, SQLException {

		if (singleton == null) {
			singleton = new frmPesquisaUsuario();
		}

		return singleton;
	}

	public frmPesquisaUsuario() throws SQLException {
		addInternalFrameListener(this);
		setClosable(true);
		setTitle("Pesquisar Usu\u00E1rio");
		setBounds(100, 100, 663, 396);
		getContentPane().setLayout(null);

		DAOFuncionario daoFuncionario = new DAOFuncionario();
		DAOCargo daoCargo = new DAOCargo();

		pnlFiltros = new JPanel();
		pnlFiltros.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlFiltros.setBounds(10, 11, 627, 103);
		getContentPane().add(pnlFiltros);
		pnlFiltros.setLayout(null);

		lblRegistro = new JLabel("Registro");
		lblRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegistro.setBounds(10, 25, 60, 28);
		pnlFiltros.add(lblRegistro);

		lblNome = new JLabel("Login");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(162, 25, 43, 28);
		pnlFiltros.add(lblNome);

		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		txtLogin.setBounds(215, 25, 155, 28);
		pnlFiltros.add(txtLogin);

		cbRegistro = new JComboBox();
		cbRegistro.setBounds(80, 25, 72, 28);

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
				txtLogin.setText(null);
			}
		});

		pnlFiltros.add(cbRegistro);

		btnLimparFiltros = new JButton("Limpar Filtros");
		btnLimparFiltros.addActionListener(this);
		btnLimparFiltros.setBounds(495, 64, 122, 28);
		pnlFiltros.add(btnLimparFiltros);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setBounds(467, 25, 150, 28);
		pnlFiltros.add(comboBox);
		
		JLabel lblNomeDoFuncionario = new JLabel("Funcion\u00E1rio");
		lblNomeDoFuncionario.setHorizontalAlignment(SwingConstants.LEFT);
		lblNomeDoFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeDoFuncionario.setBounds(380, 25, 77, 28);
		pnlFiltros.add(lblNomeDoFuncionario);

		srpPainelTabela = new JScrollPane();
		srpPainelTabela.setBounds(10, 125, 627, 191);
		getContentPane().add(srpPainelTabela);
		
		usuarioTableModel = new UsuarioTableModel();
		tbTabelaUsuario = new JTable(usuarioTableModel);
		srpPainelTabela.setViewportView(tbTabelaUsuario);

		JButton btnAbrirCadastroDeUsuario = new JButton("Abrir cadastro de usu\u00E1rio");
		btnAbrirCadastroDeUsuario.setBounds(457, 327, 180, 28);
		getContentPane().add(btnAbrirCadastroDeUsuario);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLimparFiltros) {
			btnLimparFiltros_click();
		}
	}

	private void btnLimparFiltros_click() {
		FuncoesGlobais.limpaCampos(pnlFiltros);
	}
	
	public void internalFrameActivated(InternalFrameEvent e) {
	}

	public void internalFrameClosed(InternalFrameEvent e) {
	}

	public void internalFrameClosing(InternalFrameEvent e) {
		this.singleton = null;
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

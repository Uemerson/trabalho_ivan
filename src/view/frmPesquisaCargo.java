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

public class frmPesquisaCargo extends JInternalFrame implements ActionListener, InternalFrameListener {
	private JTextField txtNome;
	private JTable tbTabelaCargo;
	private JButton btnLimparFiltros;
	private JLabel lblRegistro;
	private JLabel lblNome;

	private static frmPesquisaCargo singleton = null;
	private JComboBox cbRegistro;
	private JPanel pnlFiltros;
	private JScrollPane srpPainelTabela;

	public static frmPesquisaCargo getInstance() throws ParseException, SQLException {

		if (singleton == null) {
			singleton = new frmPesquisaCargo();
		}

		return singleton;
	}

	public frmPesquisaCargo() throws SQLException {
		addInternalFrameListener(this);
		setClosable(true);
		setTitle("Pesquisar Cargo");
		setBounds(100, 100, 512, 396);
		getContentPane().setLayout(null);

		DAOFuncionario daoFuncionario = new DAOFuncionario();
		DAOCargo daoCargo = new DAOCargo();

		pnlFiltros = new JPanel();
		pnlFiltros.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlFiltros.setBounds(10, 11, 475, 103);
		getContentPane().add(pnlFiltros);
		pnlFiltros.setLayout(null);

		lblRegistro = new JLabel("Registro");
		lblRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegistro.setBounds(10, 25, 60, 28);
		pnlFiltros.add(lblRegistro);

		lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(162, 25, 43, 28);
		pnlFiltros.add(lblNome);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(215, 25, 245, 28);
		pnlFiltros.add(txtNome);

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
				txtNome.setText(null);
			}
		});

		pnlFiltros.add(cbRegistro);

		btnLimparFiltros = new JButton("Limpar Filtros");
		btnLimparFiltros.addActionListener(this);
		btnLimparFiltros.setBounds(338, 64, 122, 28);
		pnlFiltros.add(btnLimparFiltros);

		srpPainelTabela = new JScrollPane();
		srpPainelTabela.setBounds(10, 125, 475, 191);
		getContentPane().add(srpPainelTabela);
		
		tbTabelaCargo = new JTable();
		srpPainelTabela.setViewportView(tbTabelaCargo);

		JButton btnAbrirCadastroDeCargo = new JButton("Abrir cadastro de cargo");
		btnAbrirCadastroDeCargo.setBounds(305, 327, 180, 28);
		getContentPane().add(btnAbrirCadastroDeCargo);
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

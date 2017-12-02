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
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;

public class frmPesquisaMensalidade extends JInternalFrame implements ActionListener, InternalFrameListener {
	private JTable tbTabelaMensalidade;
	private JButton btnLimparFiltros;
	private JLabel lblRegistro;

	private static frmPesquisaMensalidade singleton = null;
	private JComboBox cbRegistro;
	private JPanel pnlFiltros;
	private JScrollPane srpPainelTabela;
	
	private MaskFormatter mskDataMatricula;
	private JComboBox cbSerie;
	
	public static frmPesquisaMensalidade getInstance() throws ParseException, SQLException {

		if (singleton == null) {
			singleton = new frmPesquisaMensalidade();
		}

		return singleton;
	}

	public frmPesquisaMensalidade() throws SQLException, ParseException {
		addInternalFrameListener(this);
		setClosable(true);
		setTitle("Pesquisar Mensalidade");
		setBounds(100, 100, 401, 396);
		getContentPane().setLayout(null);

		DAOFuncionario daoFuncionario = new DAOFuncionario();
		DAOCargo daoCargo = new DAOCargo();

		pnlFiltros = new JPanel();
		pnlFiltros.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlFiltros.setBounds(10, 11, 366, 103);
		getContentPane().add(pnlFiltros);
		pnlFiltros.setLayout(null);

		lblRegistro = new JLabel("Registro");
		lblRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegistro.setBounds(10, 25, 60, 28);
		pnlFiltros.add(lblRegistro);

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
				cbSerie.setSelectedItem(null);
			}
		});

		pnlFiltros.add(cbRegistro);

		btnLimparFiltros = new JButton("Limpar Filtros");
		btnLimparFiltros.addActionListener(this);
		btnLimparFiltros.setBounds(232, 64, 122, 28);
		pnlFiltros.add(btnLimparFiltros);
		
		cbSerie = new JComboBox();
		cbSerie.setEditable(true);
		cbSerie.setBounds(232, 25, 122, 28);
		pnlFiltros.add(cbSerie);
		
		JLabel lblSrie = new JLabel("S\u00E9rie");
		lblSrie.setHorizontalAlignment(SwingConstants.LEFT);
		lblSrie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSrie.setBounds(184, 25, 38, 28);
		pnlFiltros.add(lblSrie);
		
		mskDataMatricula = new MaskFormatter("##/##/####");

		srpPainelTabela = new JScrollPane();
		srpPainelTabela.setBounds(10, 125, 366, 191);
		getContentPane().add(srpPainelTabela);
		
		tbTabelaMensalidade = new JTable();
		srpPainelTabela.setViewportView(tbTabelaMensalidade);

		JButton btnAbrirCadastroDeMatricula = new JButton("Abrir cadastro de mensalidade");
		btnAbrirCadastroDeMatricula.setBounds(165, 327, 211, 28);
		getContentPane().add(btnAbrirCadastroDeMatricula);
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

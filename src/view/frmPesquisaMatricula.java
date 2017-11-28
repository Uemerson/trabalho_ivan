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

public class frmPesquisaMatricula extends JInternalFrame implements ActionListener, InternalFrameListener {
	private JTextField txtAluno;
	private JTable tbTabelaMatricula;
	private JButton btnLimparFiltros;
	private JLabel lblRegistro;
	private JLabel lblNome;

	private static frmPesquisaMatricula singleton = null;
	private JComboBox cbRegistro;
	private JPanel pnlFiltros;
	private JScrollPane srpPainelTabela;
	
	private MaskFormatter mskDataMatricula;
	private JFormattedTextField txtDataMatricula;
	
	public static frmPesquisaMatricula getInstance() throws ParseException, SQLException {

		if (singleton == null) {
			singleton = new frmPesquisaMatricula();
		}

		return singleton;
	}

	public frmPesquisaMatricula() throws SQLException, ParseException {
		System.out.println("Abrindo frmPesquisa");
		addInternalFrameListener(this);
		setClosable(true);
		setTitle("Pesquisar Matr\u00EDcula");
		setBounds(100, 100, 752, 396);
		getContentPane().setLayout(null);

		DAOFuncionario daoFuncionario = new DAOFuncionario();
		DAOCargo daoCargo = new DAOCargo();

		pnlFiltros = new JPanel();
		pnlFiltros.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlFiltros.setBounds(10, 11, 716, 103);
		getContentPane().add(pnlFiltros);
		pnlFiltros.setLayout(null);

		lblRegistro = new JLabel("Registro");
		lblRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegistro.setBounds(10, 25, 60, 28);
		pnlFiltros.add(lblRegistro);

		lblNome = new JLabel("Aluno");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(162, 25, 43, 28);
		pnlFiltros.add(lblNome);

		txtAluno = new JTextField();
		txtAluno.setColumns(10);
		txtAluno.setBounds(215, 25, 245, 28);
		pnlFiltros.add(txtAluno);

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
				txtAluno.setText(null);
			}
		});

		pnlFiltros.add(cbRegistro);

		btnLimparFiltros = new JButton("Limpar Filtros");
		btnLimparFiltros.addActionListener(this);
		btnLimparFiltros.setBounds(584, 64, 122, 28);
		pnlFiltros.add(btnLimparFiltros);
		
		JLabel label = new JLabel("Data da Matr\u00EDcula");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(470, 25, 116, 28);
		pnlFiltros.add(label);
		
		mskDataMatricula = new MaskFormatter("##/##/####");
		txtDataMatricula = new JFormattedTextField(mskDataMatricula);
		txtDataMatricula.setBounds(596, 25, 110, 28);
		pnlFiltros.add(txtDataMatricula);

		srpPainelTabela = new JScrollPane();
		srpPainelTabela.setBounds(10, 125, 716, 191);
		getContentPane().add(srpPainelTabela);
		
		tbTabelaMatricula = new JTable();
		srpPainelTabela.setViewportView(tbTabelaMatricula);

		JButton btnAbrirCadastroDeResponsavel = new JButton("Abrir cadastro de matr\u00EDcula");
		btnAbrirCadastroDeResponsavel.setBounds(531, 327, 195, 28);
		getContentPane().add(btnAbrirCadastroDeResponsavel);
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

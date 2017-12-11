package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import org.omg.PortableServer.ThreadPolicyOperations;

import controler.ComboKeyHandler;
import controler.FuncoesGlobais;
import dao.DAOCargo;
import dao.DAOFuncionario;
import model.Cargo;
import model.Funcionario;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.SwingPropertyChangeSupport;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.event.InternalFrameEvent;
import javax.swing.ImageIcon;

public class frmCadastroFuncionario extends JInternalFrame implements ActionListener, InternalFrameListener {

	private JButton btnExcluir;
	private JPanel pnlInformacaoFuncionario;
	private JButton btnNovo;
	private JButton btnAlterar;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnPesquisar;
	private JLabel lblFuncionario;
	private JComboBox cbCargo;
	private JTextField txtNome;
	private JLabel lblRegistro;
	private JTextField txtRegistro;
	private JLabel lblCPF;
	private JLabel lblDataDeNascimento;
	private JTextField txtRG;
	private JTextField txtEndereco;
	private JLabel lblEndereco;
	private JLabel lblRG;
	private JLabel lblCidade;
	private JTextField txtCidade;
	private JLabel lblBairro;
	private JTextField txtBairro;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblEstado;
	private JComboBox cbEstado;
	private JLabel lblTelefoneComercial;
	private JLabel lblTelefoneResidencial;
	private JTextField txtSalario;
	private JTextField txtFormacaoAcademica;
	private JTextField txtNumerodeAutorizacao;
	private JTextField txtNumeroDoRegistroDoDiploma;
	private JLabel lblLogradouro;
	private JComboBox cbLogradouro;
	private JLabel lblNumero;
	private JTextField txtNumero;
	private JLabel lblCelular;
	private JFormattedTextField txtTelComercial;
	private JFormattedTextField txtTelResidencial;
	private JFormattedTextField txtCelular;
	private JFormattedTextField txtDataDeAdimissao;
	private JFormattedTextField txtDataDeDemissao;
	private JFormattedTextField txtCPF;
	private JFormattedTextField txtDataDeNascimento;
	private JFormattedTextField txtDataDeAutorizacaoDaSER;

	// Variaveis
	private JPanel pnlInformacaoDeProfessores;
	private JPanel pnlBotoes;
	private static frmCadastroFuncionario singleton = null;

	public static frmCadastroFuncionario getInstance() throws ParseException, SQLException {
		if (singleton == null) {
			singleton = new frmCadastroFuncionario();
		}

		return singleton;
	}
	
	public static void setInstance(frmCadastroFuncionario estado) {
		singleton = estado;
	}
	
	public frmCadastroFuncionario() throws ParseException, SQLException {
		addInternalFrameListener(this);
		getContentPane().setLayout(null);

		// Hack para remover icone do nimbus
		Container pane = ((BasicInternalFrameUI) this.getUI()).getNorthPane();
		// pane.remove(0);
		pane.getComponent(0).setVisible(false);

		pnlBotoes = new JPanel();
		pnlBotoes.setBounds(10, 11, 555, 98);
		pnlBotoes.setLayout(null);
		getContentPane().add(pnlBotoes);

		btnNovo = new JButton("Novo");
		btnNovo.setIcon(new ImageIcon(frmCadastroFuncionario.class.getResource("/imagens/novo 48x48.png")));
		btnNovo.addActionListener(this);
		btnNovo.setBounds(10, 11, 80, 79);
		btnNovo.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNovo.setHorizontalTextPosition(SwingConstants.CENTER);
		pnlBotoes.add(btnNovo);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(frmCadastroFuncionario.class.getResource("/imagens/excluir 48x48.png")));
		btnExcluir.addActionListener(this);
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(100, 11, 80, 79);
		btnExcluir.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnExcluir.setHorizontalTextPosition(SwingConstants.CENTER);
		pnlBotoes.add(btnExcluir);

		btnAlterar = new JButton("Alterar");
		btnAlterar.setVerticalAlignment(SwingConstants.TOP);
		btnAlterar.setIcon(new ImageIcon(frmCadastroFuncionario.class.getResource("/imagens/editar 48x48.png")));
		btnAlterar.addActionListener(this);
		btnAlterar.setEnabled(false);
		btnAlterar.setBounds(190, 11, 80, 79);
		btnAlterar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAlterar.setHorizontalTextPosition(SwingConstants.CENTER);
		pnlBotoes.add(btnAlterar);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(frmCadastroFuncionario.class.getResource("/imagens/salvar 48x48.png")));
		btnSalvar.addActionListener(this);
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(280, 11, 80, 79);
		btnSalvar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalvar.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlBotoes.add(btnSalvar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setVerticalAlignment(SwingConstants.TOP);
		btnCancelar.setIcon(new ImageIcon(frmCadastroFuncionario.class.getResource("/imagens/cancelar 48x48.png")));
		btnCancelar.addActionListener(this);
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(370, 11, 80, 79);
		btnCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
		pnlBotoes.add(btnCancelar);

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setIcon(new ImageIcon(frmCadastroFuncionario.class.getResource("/imagens/lupa 48x48.png")));
		btnPesquisar.setBounds(460, 11, 85, 79);
		btnPesquisar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPesquisar.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlBotoes.add(btnPesquisar);
		btnPesquisar.addActionListener(this);

		pnlInformacaoFuncionario = new JPanel();
		pnlInformacaoFuncionario.setBorder(new TitledBorder(null, "Informa\u00E7\u00E3o do Funcionario",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlInformacaoFuncionario.setBounds(10, 112, 788, 334);
		getContentPane().add(pnlInformacaoFuncionario);
		pnlInformacaoFuncionario.setLayout(null);

		lblFuncionario = new JLabel("Cargo");
		lblFuncionario.setHorizontalAlignment(SwingConstants.LEFT);
		lblFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFuncionario.setBounds(10, 22, 90, 28);
		pnlInformacaoFuncionario.add(lblFuncionario);

		/*
		 * Preenche a lista de cargo e adiciona no jcombobox
		 */

		cbCargo = new JComboBox();
		cbCargo.setEnabled(false);
		cbCargo.setEditable(true);
		DefaultComboBoxModel<String> cbCargoModel = new DefaultComboBoxModel<>();
		for (int i = 0; i < new DAOCargo().listaCargos().size(); i++) {
			cbCargoModel.addElement(new DAOCargo().listaCargos().get(i).getNome());
		}
		
		cbCargo.setModel(cbCargoModel);
		cbCargo.setSelectedItem(null);
		cbCargo.addActionListener(this);
		cbCargo.setBounds(120, 22, 106, 28);
		JTextField edtCbCargo = (JTextField) cbCargo.getEditor().getEditorComponent();
		edtCbCargo.addKeyListener(new ComboKeyHandler(cbCargo));
		
		pnlInformacaoFuncionario.add(cbCargo);

		lblRegistro = new JLabel("Registro");
		lblRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegistro.setBounds(10, 61, 90, 28);
		pnlInformacaoFuncionario.add(lblRegistro);

		txtRegistro = new JTextField();
		txtRegistro.setEnabled(false);
		txtRegistro.setColumns(10);
		txtRegistro.setBounds(120, 61, 338, 28);
		pnlInformacaoFuncionario.add(txtRegistro);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 100, 90, 28);
		pnlInformacaoFuncionario.add(lblNome);

		txtNome = new JTextField();
		txtNome.setEnabled(false);
		txtNome.setColumns(10);
		txtNome.setBounds(120, 100, 338, 28);
		pnlInformacaoFuncionario.add(txtNome);

		lblCPF = new JLabel("CPF");
		lblCPF.setHorizontalAlignment(SwingConstants.LEFT);
		lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCPF.setBounds(468, 61, 129, 28);
		pnlInformacaoFuncionario.add(lblCPF);

		lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setHorizontalAlignment(SwingConstants.LEFT);
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDeNascimento.setBounds(468, 100, 129, 28);
		pnlInformacaoFuncionario.add(lblDataDeNascimento);

		lblRG = new JLabel("RG");
		lblRG.setHorizontalAlignment(SwingConstants.LEFT);
		lblRG.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRG.setBounds(10, 139, 90, 28);
		pnlInformacaoFuncionario.add(lblRG);

		lblEndereco = new JLabel("Endere\u00E7o");
		lblEndereco.setHorizontalAlignment(SwingConstants.LEFT);
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEndereco.setBounds(257, 217, 74, 28);
		pnlInformacaoFuncionario.add(lblEndereco);

		txtRG = new JTextField();
		txtRG.setEnabled(false);
		txtRG.setColumns(10);
		txtRG.setBounds(120, 139, 338, 28);
		pnlInformacaoFuncionario.add(txtRG);

		txtEndereco = new JTextField();
		txtEndereco.setEnabled(false);
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(359, 217, 328, 28);
		pnlInformacaoFuncionario.add(txtEndereco);

		lblCidade = new JLabel("Cidade");
		lblCidade.setHorizontalAlignment(SwingConstants.LEFT);
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCidade.setBounds(468, 180, 129, 28);
		pnlInformacaoFuncionario.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setEnabled(false);
		txtCidade.setColumns(10);
		txtCidade.setBounds(603, 180, 165, 28);
		pnlInformacaoFuncionario.add(txtCidade);

		lblBairro = new JLabel("Bairro");
		lblBairro.setHorizontalAlignment(SwingConstants.LEFT);
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBairro.setBounds(10, 258, 62, 28);
		pnlInformacaoFuncionario.add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.setEnabled(false);
		txtBairro.setColumns(10);
		txtBairro.setBounds(120, 258, 149, 28);
		pnlInformacaoFuncionario.add(txtBairro);

		lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(10, 178, 90, 28);
		pnlInformacaoFuncionario.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setEnabled(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(120, 180, 338, 28);
		pnlInformacaoFuncionario.add(txtEmail);

		lblEstado = new JLabel("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.LEFT);
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstado.setBounds(468, 139, 80, 28);
		pnlInformacaoFuncionario.add(lblEstado);

		cbEstado = new JComboBox();
		cbEstado.setEnabled(false);
		cbEstado.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO"}));
		cbEstado.setSelectedItem(null);
		cbEstado.setBounds(603, 139, 165, 28);
		
		cbEstado.setEditable(true);
		JTextField edtCbEstado = (JTextField) cbEstado.getEditor().getEditorComponent();
		edtCbEstado.addKeyListener(new ComboKeyHandler(cbEstado));
		
		pnlInformacaoFuncionario.add(cbEstado);

		lblTelefoneComercial = new JLabel("Tel Comercial");
		lblTelefoneComercial.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefoneComercial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefoneComercial.setBounds(10, 295, 90, 28);
		pnlInformacaoFuncionario.add(lblTelefoneComercial);

		lblTelefoneResidencial = new JLabel("Tel Residencial");
		lblTelefoneResidencial.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefoneResidencial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefoneResidencial.setBounds(279, 258, 104, 28);
		pnlInformacaoFuncionario.add(lblTelefoneResidencial);

		JLabel lblDataDeAdimissao = new JLabel("Data de Adimiss\u00E3o");
		lblDataDeAdimissao.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataDeAdimissao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDeAdimissao.setBounds(231, 22, 138, 28);
		pnlInformacaoFuncionario.add(lblDataDeAdimissao);

		JLabel lblDataDeDemissao = new JLabel("Data de Demiss\u00E3o");
		lblDataDeDemissao.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataDeDemissao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDeDemissao.setBounds(530, 22, 129, 28);
		pnlInformacaoFuncionario.add(lblDataDeDemissao);

		JLabel lblSalario = new JLabel("Sal\u00E1rio");
		lblSalario.setHorizontalAlignment(SwingConstants.LEFT);
		lblSalario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSalario.setBounds(279, 295, 90, 28);
		pnlInformacaoFuncionario.add(lblSalario);

		txtSalario = new JTextField();
		txtSalario.setEnabled(false);
		txtSalario.setColumns(10);
		txtSalario.setBounds(393, 295, 149, 28);
		pnlInformacaoFuncionario.add(txtSalario);

		lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setHorizontalAlignment(SwingConstants.LEFT);
		lblLogradouro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLogradouro.setBounds(10, 217, 80, 28);
		pnlInformacaoFuncionario.add(lblLogradouro);

		cbLogradouro = new JComboBox();
		cbLogradouro.addActionListener(this);
		cbLogradouro.setEnabled(false);
		cbLogradouro.setModel(new DefaultComboBoxModel(new String[] { "Avenida", "Rua", "Pra\u00E7a", "Zona Rural" }));
		cbLogradouro.setBounds(118, 217, 108, 28);
		
		cbLogradouro.setEditable(true);
		JTextField edtCbLogradouro = (JTextField) cbLogradouro.getEditor().getEditorComponent();
		edtCbLogradouro.addKeyListener(new ComboKeyHandler(cbLogradouro));
		
		cbLogradouro.setSelectedItem(null);

		pnlInformacaoFuncionario.add(cbLogradouro);

		lblNumero = new JLabel(",");
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumero.setBounds(697, 217, 14, 28);
		pnlInformacaoFuncionario.add(lblNumero);

		txtNumero = new JTextField();
		txtNumero.setEnabled(false);
		txtNumero.setHorizontalAlignment(SwingConstants.RIGHT);
		txtNumero.setColumns(10);
		txtNumero.setBounds(721, 217, 47, 28);
		pnlInformacaoFuncionario.add(txtNumero);

		lblCelular = new JLabel("Celular");
		lblCelular.setHorizontalAlignment(SwingConstants.LEFT);
		lblCelular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCelular.setBounds(547, 258, 62, 28);
		pnlInformacaoFuncionario.add(lblCelular);

		txtTelComercial = new JFormattedTextField(new MaskFormatter("(##)####-####"));
		txtTelComercial.setEnabled(false);
		txtTelComercial.setBounds(120, 295, 149, 28);
		pnlInformacaoFuncionario.add(txtTelComercial);

		txtTelResidencial = new JFormattedTextField(new MaskFormatter("(##)####-####"));
		txtTelResidencial.setEnabled(false);
		txtTelResidencial.setBounds(393, 258, 149, 28);
		pnlInformacaoFuncionario.add(txtTelResidencial);

		// MaskFormatter mascaraCelular = new MaskFormatter("(##)*####-####");
		// mascaraCelular.setValidCharacters("0123456789 ");

		txtCelular = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		txtCelular.setEnabled(false);
		txtCelular.setBounds(619, 258, 149, 28);
		pnlInformacaoFuncionario.add(txtCelular);

		txtDataDeAdimissao = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataDeAdimissao.setEnabled(false);
		txtDataDeAdimissao.setBounds(368, 22, 90, 28);
		pnlInformacaoFuncionario.add(txtDataDeAdimissao);

		txtDataDeDemissao = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataDeDemissao.setEnabled(false);
		txtDataDeDemissao.setBounds(669, 22, 99, 28);
		pnlInformacaoFuncionario.add(txtDataDeDemissao);

		txtCPF = new JFormattedTextField(new MaskFormatter("###.###.###.##"));
		txtCPF.setEnabled(false);
		txtCPF.setBounds(603, 61, 165, 28);
		pnlInformacaoFuncionario.add(txtCPF);

		txtDataDeNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataDeNascimento.setEnabled(false);
		txtDataDeNascimento.setBounds(603, 100, 165, 28);
		pnlInformacaoFuncionario.add(txtDataDeNascimento);

		pnlInformacaoDeProfessores = new JPanel();
		pnlInformacaoDeProfessores.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Informa\u00E7\u00E3o do professor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlInformacaoDeProfessores.setBounds(10, 457, 788, 138);
		getContentPane().add(pnlInformacaoDeProfessores);
		pnlInformacaoDeProfessores.setLayout(null);

		JLabel lblFormaoAcademicaqualificao = new JLabel("Forma\u00E7\u00E3o acad\u00EAmica/qualifica\u00E7\u00E3o ");
		lblFormaoAcademicaqualificao.setHorizontalAlignment(SwingConstants.LEFT);
		lblFormaoAcademicaqualificao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFormaoAcademicaqualificao.setBounds(10, 21, 221, 28);
		pnlInformacaoDeProfessores.add(lblFormaoAcademicaqualificao);

		txtFormacaoAcademica = new JTextField();
		txtFormacaoAcademica.setEnabled(false);
		txtFormacaoAcademica.setColumns(10);
		txtFormacaoAcademica.setBounds(241, 21, 526, 28);
		pnlInformacaoDeProfessores.add(txtFormacaoAcademica);

		JLabel lblNumeroDeAutorizacao = new JLabel("N\u00FAmero de autoriza\u00E7\u00E3o (SER)");
		lblNumeroDeAutorizacao.setHorizontalAlignment(SwingConstants.LEFT);
		lblNumeroDeAutorizacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumeroDeAutorizacao.setBounds(10, 58, 221, 28);
		pnlInformacaoDeProfessores.add(lblNumeroDeAutorizacao);

		txtNumerodeAutorizacao = new JTextField();
		txtNumerodeAutorizacao.setEnabled(false);
		txtNumerodeAutorizacao.setColumns(10);
		txtNumerodeAutorizacao.setBounds(241, 58, 163, 28);
		pnlInformacaoDeProfessores.add(txtNumerodeAutorizacao);

		JLabel lblDataDeAutorizacao = new JLabel("Data de Autoriza\u00E7\u00E3o da SER");
		lblDataDeAutorizacao.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataDeAutorizacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDeAutorizacao.setBounds(433, 58, 184, 28);
		pnlInformacaoDeProfessores.add(lblDataDeAutorizacao);

		JLabel lblNumeroDoRegistroDoDiploma = new JLabel("N\u00FAmero do registro do Diploma");
		lblNumeroDoRegistroDoDiploma.setHorizontalAlignment(SwingConstants.LEFT);
		lblNumeroDoRegistroDoDiploma.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumeroDoRegistroDoDiploma.setBounds(10, 97, 221, 28);
		pnlInformacaoDeProfessores.add(lblNumeroDoRegistroDoDiploma);

		txtNumeroDoRegistroDoDiploma = new JTextField();
		txtNumeroDoRegistroDoDiploma.setEnabled(false);
		txtNumeroDoRegistroDoDiploma.setColumns(10);
		txtNumeroDoRegistroDoDiploma.setBounds(241, 97, 163, 28);
		pnlInformacaoDeProfessores.add(txtNumeroDoRegistroDoDiploma);

		txtDataDeAutorizacaoDaSER = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataDeAutorizacaoDaSER.setEnabled(false);
		txtDataDeAutorizacaoDaSER.setBounds(651, 58, 116, 28);
		pnlInformacaoDeProfessores.add(txtDataDeAutorizacaoDaSER);
		setBounds(100, 100, 821, 631);
		setClosable(true);
		setTitle("Cadastro de Funcion\u00E1rio");
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnNovo) {
				btnNovo_click();
			}

			else if (e.getSource() == cbCargo) {
				cbCargo_click();
			}

			else if (e.getSource() == btnCancelar) {
				btnCancelar_click();
			}

			else if (e.getSource() == btnSalvar) {
				btnSalvar_click();
			}

			else if (e.getSource() == btnAlterar) {
				btnAlterar_click();
			}

			else if (e.getSource() == btnExcluir) {
				btnExcluir_click();
			}

			else if (e.getSource() == btnPesquisar) {
				btnPesquisar_click();
			}

		} catch (ParseException | SQLException | PropertyVetoException ex) {
			// ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro ao tentar concluir ação, tente novamente!", "Sistema",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void btnPesquisar_click() throws ParseException, SQLException, PropertyVetoException {
		if (frmPesquisaFuncionario.getInstance().isVisible()) {
			frmPesquisaFuncionario.getInstance().atualizaDados(); // Atualiza os dados do formulario
			frmPesquisaFuncionario.getInstance().getBtnConfirma().setVisible(true);
			frmPesquisaFuncionario.getInstance().getBtnConfirma().setText("Abrir cadastro de funcionário");
			frmPesquisaFuncionario.getInstance().setSelected(true);
		} else {
			frmPesquisaFuncionario.getInstance().atualizaDados(); // Atualiza os dados do formulario
			frmMenu.getInstance().getDskPrincipal().add(frmPesquisaFuncionario.getInstance());
			frmPesquisaFuncionario.getInstance().getBtnConfirma().setVisible(true);
			frmPesquisaFuncionario.getInstance().getBtnConfirma().setText("Abrir cadastro de funcionário");
			frmPesquisaFuncionario.getInstance().setVisible(true);
			frmPesquisaFuncionario.getInstance().setSelected(true);
		}
	}

	private void btnNovo_click() {
		FuncoesGlobais.limpaCampos(pnlInformacaoFuncionario);
		FuncoesGlobais.limpaCampos(pnlInformacaoDeProfessores);
		FuncoesGlobais.ativaCampos(pnlInformacaoFuncionario);
		FuncoesGlobais.desativaCampos(pnlBotoes);

		btnSalvar.setEnabled(true);
		btnCancelar.setEnabled(true);
		txtRegistro.setText("NOVO");
		txtRegistro.setEnabled(false);
		cbCargo.requestFocus();
	}

	private void btnAlterar_click() {
		FuncoesGlobais.ativaCampos(pnlInformacaoFuncionario);

		if (cbCargo.getSelectedItem().toString().replace("(a)", "").equals("Professor")) {
			FuncoesGlobais.ativaCampos(pnlInformacaoDeProfessores);
		}

		FuncoesGlobais.desativaCampos(pnlBotoes);

		btnSalvar.setEnabled(true);
		btnCancelar.setEnabled(true);
		txtRegistro.setEnabled(false);
		cbCargo.requestFocus();
	}

	private void cbCargo_click() {

		if (cbCargo.getSelectedIndex() > -1) {
			if (cbCargo.getSelectedItem().toString().replace("(a)", "").equals("Professor") || 
					cbCargo.getSelectedItem().toString().replace("(a)", "").equals("Diretor")) {
				FuncoesGlobais.limpaCampos(pnlInformacaoDeProfessores);
				FuncoesGlobais.ativaCampos(pnlInformacaoDeProfessores);
			} else {
				FuncoesGlobais.limpaCampos(pnlInformacaoDeProfessores);
				FuncoesGlobais.desativaCampos(pnlInformacaoDeProfessores);
				FuncoesGlobais.resetaBordaPadrao(pnlInformacaoDeProfessores);
			}
		}
	}

	private void btnCancelar_click() {

		if (JOptionPane.showConfirmDialog(this, "Deseja realmente cancelar o cadastrado?", "Sistema",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

			FuncoesGlobais.limpaCampos(pnlInformacaoFuncionario);
			FuncoesGlobais.limpaCampos(pnlInformacaoDeProfessores);
			FuncoesGlobais.desativaCampos(pnlInformacaoFuncionario);
			FuncoesGlobais.desativaCampos(pnlInformacaoDeProfessores);
			FuncoesGlobais.desativaCampos(pnlBotoes);
			FuncoesGlobais.resetaBordaPadrao(pnlInformacaoFuncionario);
			FuncoesGlobais.resetaBordaPadrao(pnlInformacaoDeProfessores);
			btnNovo.setEnabled(true);
			btnPesquisar.setEnabled(true);

			JOptionPane.showMessageDialog(this, "Cancelado com sucesso!", "Sistema", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void btnSalvar_click() {
		try {
			// Adiciona a lista de campos que serão ignorados campos nulos
			ArrayList<Component> listaCompInformacoesFuncionario = new ArrayList<>();
			listaCompInformacoesFuncionario.add(txtDataDeDemissao);
			listaCompInformacoesFuncionario.add(txtEmail);
			listaCompInformacoesFuncionario.add(txtTelResidencial);
			listaCompInformacoesFuncionario.add(txtTelComercial);
			listaCompInformacoesFuncionario.add(txtNumero);
			listaCompInformacoesFuncionario.add(txtBairro);

			ArrayList<Component> listaCompInformacoesProfessor = new ArrayList<>();
			/* Num autorizao ou Num de diploma */
			/* Resetar borda dos comp que estao na lista */
			if (!txtNumerodeAutorizacao.getText().isEmpty()) {
				listaCompInformacoesProfessor.add(txtNumeroDoRegistroDoDiploma);
				JTextField bordaPadrao = new JTextField();
				txtNumeroDoRegistroDoDiploma.setBorder(bordaPadrao.getBorder());
			} else if (!txtNumeroDoRegistroDoDiploma.getText().isEmpty()) {
				listaCompInformacoesProfessor.add(txtNumerodeAutorizacao);
				listaCompInformacoesProfessor.add(txtDataDeAutorizacaoDaSER);
				JTextField bordaPadrao = new JTextField();
				txtNumerodeAutorizacao.setBorder(bordaPadrao.getBorder());
				txtDataDeAutorizacaoDaSER.setBorder(bordaPadrao.getBorder());
			}

			if (FuncoesGlobais.verificaCampos(pnlInformacaoFuncionario, listaCompInformacoesFuncionario) == true
					| (cbCargo.getSelectedIndex() > -1 && cbCargo.getSelectedItem().toString().replace("(a)", "").equals("Professor")
							&& FuncoesGlobais.verificaCampos(pnlInformacaoDeProfessores,
									listaCompInformacoesProfessor) == true)) {

				JOptionPane.showMessageDialog(this, "Erro - Os campos em vermelho devem ser preenchidos!", "Sistema",
						JOptionPane.ERROR_MESSAGE);

			} else {

				if (JOptionPane.showConfirmDialog(this, "Deseja realmente salvar o cadastrado?", "Sistema",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					// Salvando o registro
					Funcionario funcionario = new Funcionario();

					// funcionario.setCargo(cbCargo.getSelectedItem().toString());
					funcionario.setCargo(new DAOCargo().idCargo(cbCargo.getSelectedItem().toString()));

					funcionario.setData_de_Admissao(
							new SimpleDateFormat("dd/MM/yyyy").parse(txtDataDeAdimissao.getText()));
					funcionario.setData_de_Demissao(txtDataDeDemissao.getValue() == null ? null
							: new SimpleDateFormat("dd/MM/yyyy").parse(txtDataDeDemissao.getText()));
					funcionario.setCPF(txtCPF.getText().replace(".", ""));
					funcionario.setNome(txtNome.getText());
					funcionario.setData_de_Nascimento(
							new SimpleDateFormat("dd/MM/yyyy").parse(txtDataDeNascimento.getText()));
					funcionario.setRG(txtRG.getText());
					funcionario.setEstado(cbEstado.getSelectedItem().toString());
					funcionario.setEmail(txtEmail.getText().isEmpty() ? null
							: txtEmail.getText());
					funcionario.setCidade(txtCidade.getText());
					funcionario.setLogradouro(cbLogradouro.getSelectedItem().toString());
					funcionario.setEndereco(txtEndereco.getText());
					funcionario.setNumero_da_Casa(txtNumero.getText().isEmpty() ? 0
							: Integer.parseInt(txtNumero.getText()));
					funcionario.setBairro(txtBairro.getText().isEmpty() ? null
							: txtBairro.getText());
					funcionario.setTel_Residencial(txtTelResidencial.getValue() == null ? null
							: txtTelResidencial.getText().replace("(", "").replace(")", "").replace("-", ""));
					funcionario.setTel_Comercial(txtTelComercial.getValue() == null ? null
							: txtTelComercial.getText().replace("(", "").replace(")", "").replaceAll("-", ""));
					funcionario.setCelular(txtCelular.getText().replace("(", "").replace(")", "").replace("-", ""));
					funcionario.setSalario(Double.parseDouble(txtSalario.getText()));

					if (cbCargo.getSelectedIndex() > -1 && cbCargo.getSelectedItem().toString().replace("(a)", "").equals("Professor")) {
						funcionario.setFormacao_Academica(txtFormacaoAcademica.getText());
						funcionario.setNumero_de_Autorizacao_da_SER(txtNumerodeAutorizacao.getText().isEmpty() ? 0
								: Integer.parseInt(txtNumerodeAutorizacao.getText()));
						funcionario.setData_de_Autorizacao(txtDataDeAutorizacaoDaSER.getValue() == null ? null
								: new SimpleDateFormat("dd/MM/yyyy").parse(txtDataDeAutorizacaoDaSER.getText()));
						funcionario
								.setNumero_do_Registro_do_Diploma(txtNumeroDoRegistroDoDiploma.getText().isEmpty() ? 0
										: Integer.parseInt(txtNumeroDoRegistroDoDiploma.getText()));
					}

					// Novo registro
					if (txtRegistro.getText().equals("NOVO")) {
						DAOFuncionario dao = new DAOFuncionario();
						dao.novoFuncionario(funcionario);
					}

					// Atualiza o registro
					else {
						funcionario.setRegistro(Integer.parseInt(txtRegistro.getText()));
						DAOFuncionario dao = new DAOFuncionario();
						dao.atualizaFuncionario(funcionario);
					}

					FuncoesGlobais.limpaCampos(pnlInformacaoFuncionario);
					FuncoesGlobais.limpaCampos(pnlInformacaoDeProfessores);
					FuncoesGlobais.desativaCampos(pnlInformacaoFuncionario);
					FuncoesGlobais.desativaCampos(pnlInformacaoDeProfessores);
					FuncoesGlobais.desativaCampos(pnlBotoes);

					btnNovo.setEnabled(true);
					btnPesquisar.setEnabled(true);
					btnNovo.requestFocus();

					// Novo Registro
					if (txtRegistro.getText().equals("NOVO")) {
						JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sistema",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(this, "Cadastro atualizado com sucesso!", "Sistema",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}

		} catch (SQLException | ParseException ex) {
			JOptionPane.showMessageDialog(this, "Erro ao tentar salvar o cadastro!", "Sistema", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}

	private void btnExcluir_click() throws NumberFormatException, SQLException {
		if (JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o cadastro?", "Sistema",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			DAOFuncionario daoFuncionario = new DAOFuncionario();

			daoFuncionario.excluirFuncionario(Integer.parseInt(txtRegistro.getText()));
			JOptionPane.showMessageDialog(this, "Registro excluido com sucesso!", "Sistema",
					JOptionPane.INFORMATION_MESSAGE);

			FuncoesGlobais.limpaCampos(pnlInformacaoFuncionario);
			FuncoesGlobais.limpaCampos(pnlInformacaoDeProfessores);
			FuncoesGlobais.desativaCampos(pnlInformacaoFuncionario);
			FuncoesGlobais.desativaCampos(pnlInformacaoDeProfessores);
			FuncoesGlobais.desativaCampos(pnlBotoes);

			btnNovo.setEnabled(true);
			btnPesquisar.setEnabled(true);
			btnNovo.requestFocus();
		}
	}

	public void preencheCadastro(Funcionario funcionario) throws ParseException {
		FuncoesGlobais.desativaCampos(pnlBotoes);
		FuncoesGlobais.limpaCampos(pnlInformacaoDeProfessores);
		FuncoesGlobais.limpaCampos(pnlInformacaoFuncionario);
		FuncoesGlobais.resetaBordaPadrao(pnlInformacaoDeProfessores);
		FuncoesGlobais.resetaBordaPadrao(pnlInformacaoFuncionario);
		FuncoesGlobais.desativaCampos(pnlInformacaoFuncionario);

		pnlBotoes.requestFocusInWindow();

		btnNovo.setEnabled(true);
		btnAlterar.setEnabled(true);
		btnExcluir.setEnabled(true);
		btnPesquisar.setEnabled(true);

		cbCargo.setSelectedItem(funcionario.getNomeCargo());

		txtDataDeAdimissao.setValue(new SimpleDateFormat("dd/MM/yyyy").format(funcionario.getData_de_Admissao()));
		txtDataDeDemissao.setValue(funcionario.getData_de_Demissao() == null ? null
				: new SimpleDateFormat("dd/MM/yyyy").format(funcionario.getData_de_Demissao()));
		txtRegistro.setText(Integer.toString(funcionario.getRegistro()));

		MaskFormatter mask = new MaskFormatter("###.###.###.##");
		mask.setValueContainsLiteralCharacters(false);

		txtCPF.setValue(mask.valueToString(funcionario.getCPF()));
		txtNome.setText(funcionario.getNome());
		txtDataDeNascimento.setValue(funcionario.getData_de_Nascimento() == null ? null
				: new SimpleDateFormat("dd/MM/yyyy").format(funcionario.getData_de_Nascimento()));
		txtRG.setText(funcionario.getRG());
		cbEstado.setSelectedItem(funcionario.getEstado());
		txtEmail.setText(funcionario.getEmail());
		txtCidade.setText(funcionario.getCidade());
		cbLogradouro.setSelectedItem(funcionario.getLogradouro());
		txtEndereco.setText(funcionario.getEndereco());
		txtNumero.setText(
				funcionario.getNumero_da_Casa() == 0 ? null : Integer.toString(funcionario.getNumero_da_Casa()));
		txtBairro.setText(funcionario.getBairro());

		mask.setMask("(##)#####-####");
		txtCelular.setValue(mask.valueToString(funcionario.getCelular()));

		mask.setMask("(##)####-####");
		txtTelComercial.setValue(mask.valueToString(funcionario.getTel_Comercial()));
		txtTelResidencial.setValue(mask.valueToString(funcionario.getTel_Residencial()));

		txtSalario.setText(Double.toString(funcionario.getSalario()));
		txtFormacaoAcademica.setText(funcionario.getFormacao_Academica());
		txtDataDeAutorizacaoDaSER.setValue(funcionario.getData_de_Autorizacao() == null ? null
				: new SimpleDateFormat("dd/MM/yyyy").format(funcionario.getData_de_Autorizacao()));
		txtNumerodeAutorizacao.setText(funcionario.getNumero_de_Autorizacao_da_SER() == 0 ? null
				: Integer.toString(funcionario.getNumero_de_Autorizacao_da_SER()));
		txtNumeroDoRegistroDoDiploma.setText(funcionario.getNumero_do_Registro_do_Diploma() == 0 ? null
				: Integer.toString(funcionario.getNumero_do_Registro_do_Diploma()));

		FuncoesGlobais.desativaCampos(pnlInformacaoDeProfessores);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				btnNovo.requestFocusInWindow();
			}
		});

	}

	public void internalFrameActivated(InternalFrameEvent e) {
	}
	
	//Limpa buffer de memória
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

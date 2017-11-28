


package view;

import java.awt.Color;
import java.awt.Component;
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

import controler.FuncoesGlobais;
import dao.DAOCargo;
import dao.DAOFuncionario;
import model.Cargo;
import model.Funcionario;

public class frmCadastroFuncionario extends JInternalFrame implements ActionListener {
	
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
	
	//Variaveis
	private ArrayList<Cargo> listaCargo;
	private JPanel pnlInformacaoDeProfessores;
	private JPanel pnlBotoes;
	private static frmCadastroFuncionario singleton = null;
	
	public static frmCadastroFuncionario getFrmCadastroFuncionario() throws ParseException, SQLException {
		if (singleton == null) {
			singleton = new frmCadastroFuncionario();
		}
		
		return singleton;
	}
	
	public frmCadastroFuncionario() throws ParseException, SQLException {
		getContentPane().setLayout(null);
		
		pnlBotoes = new JPanel();
		pnlBotoes.setBounds(10, 11, 555, 64);
		pnlBotoes.setLayout(null);
		getContentPane().add(pnlBotoes);
		
		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(this);
		btnNovo.setBounds(10, 11, 80, 44);
		pnlBotoes.add(btnNovo);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(100, 11, 80, 44);
		pnlBotoes.add(btnExcluir);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(this);
		btnAlterar.setEnabled(false);
		btnAlterar.setBounds(190, 11, 80, 44);
		pnlBotoes.add(btnAlterar);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(this);
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(280, 11, 80, 44);
		pnlBotoes.add(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(370, 11, 80, 44);
		pnlBotoes.add(btnCancelar);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(this);
		btnPesquisar.setBounds(460, 11, 85, 44);
		pnlBotoes.add(btnPesquisar);
		
		pnlInformacaoFuncionario = new JPanel();
		pnlInformacaoFuncionario.setBorder(new TitledBorder(null, "Informa\u00E7\u00E3o do Funcionario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlInformacaoFuncionario.setBounds(10, 74, 788, 334);
		getContentPane().add(pnlInformacaoFuncionario);
		pnlInformacaoFuncionario.setLayout(null);
		
		lblFuncionario = new JLabel("Cargo");
		lblFuncionario.setHorizontalAlignment(SwingConstants.LEFT);
		lblFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFuncionario.setBounds(10, 22, 90, 28);
		pnlInformacaoFuncionario.add(lblFuncionario);
		
		/*
		 *					Preenche a lista de cargo e adiciona no jcombobox 
		 * */
		
		cbCargo = new JComboBox();
		
		listaCargo = new ArrayList<>();
		listaCargo = new DAOCargo().listaCargo();
		
		for (int i = 0; i < listaCargo.size(); i++) {
			cbCargo.addItem(listaCargo.get(i).getNome());
		}
		
		cbCargo.setSelectedItem(null);
		
		cbCargo.addActionListener(this);
		cbCargo.setEnabled(false);
		cbCargo.setBounds(120, 22, 106, 28);
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
		cbEstado.setModel(new DefaultComboBoxModel(new String[] {"MG", "SP", "RJ", "ES", "RS", "PR", "SC", "MT", "GO", "DF", "MTS", "MA", "RN", "AL", "PE", "JP"}));
		cbEstado.setSelectedItem(null);
		cbEstado.setBounds(603, 139, 165, 28);
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
		cbLogradouro.setModel(new DefaultComboBoxModel(new String[] {"Avenida", "Rua", "Pra\u00E7a", "Zona Rural"}));
		cbLogradouro.setBounds(118, 217, 108, 28);
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
		
		//MaskFormatter mascaraCelular = new MaskFormatter("(##)*####-####");
		//mascaraCelular.setValidCharacters("0123456789 ");
		
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
		pnlInformacaoDeProfessores.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informa\u00E7\u00E3o do professor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlInformacaoDeProfessores.setBounds(10, 419, 788, 138);
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
		setBounds(100, 100, 821, 597);
		setClosable(true);
		setTitle("Cadastro de Funcionario");
	}
	
	public void actionPerformed(ActionEvent e) {
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
			try {
				btnSalvar_click();
			} catch (SQLException | ParseException ex) {
				ex.printStackTrace();
			}
		}
		
		else if (e.getSource() == btnAlterar) {
			btnAlterar_click();
		}
		
		else if (e.getSource() == btnPesquisar) {
			try {
				btnPesquisar_click();
			} catch (ParseException | SQLException | PropertyVetoException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private void btnPesquisar_click() throws ParseException, SQLException, PropertyVetoException {

		frmPesquisaFuncionario.getFrmPesquisaFuncionario();
		
		if (frmPesquisaFuncionario.getFrmPesquisaFuncionario().isVisible()) {
			frmPesquisaFuncionario.getFrmPesquisaFuncionario().resetaFormulario();
			
			frmPesquisaFuncionario.getFrmPesquisaFuncionario().setSelected(true);
		} else {
			frmPesquisaFuncionario.getFrmPesquisaFuncionario().setVisible(true);
			frmMenu.getFrmMenu().getDskPrincipal().add(frmPesquisaFuncionario.getFrmPesquisaFuncionario());
			frmPesquisaFuncionario.getFrmPesquisaFuncionario().setSelected(true);
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
		
		if (cbCargo.getSelectedItem().toString().equals("Professor")) {
			FuncoesGlobais.ativaCampos(pnlInformacaoDeProfessores);
			System.out.println("teste");
		}
		
		FuncoesGlobais.desativaCampos(pnlBotoes);
		
		btnSalvar.setEnabled(true);
		btnCancelar.setEnabled(true);
		txtRegistro.setEnabled(false);
		cbCargo.requestFocus();
	}
	
	private void cbCargo_click() {
		
		if (cbCargo.getSelectedIndex() > -1) {
			if (listaCargo.get(cbCargo.getSelectedIndex()).getNome().equals("Professor")) {
				FuncoesGlobais.limpaCampos(pnlInformacaoDeProfessores);
				FuncoesGlobais.ativaCampos(pnlInformacaoDeProfessores);
			}else {
				FuncoesGlobais.limpaCampos(pnlInformacaoDeProfessores);
				FuncoesGlobais.desativaCampos(pnlInformacaoDeProfessores);
				FuncoesGlobais.resetaBordaPadrao(pnlInformacaoDeProfessores);
			}
		}
	}
	
	private void btnCancelar_click() {
		
		if(JOptionPane.showConfirmDialog(this, 
				"Deseja realmente cancelar o novo cadastrado?", 
				"Sistema", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
			
		
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
	
	private void btnSalvar_click() throws SQLException, ParseException {
		
		//Adiciona a lista de campos que serão ignorados campos nulos
		ArrayList<Component> listaCompInformacoesFuncionario = new ArrayList<>();
		listaCompInformacoesFuncionario.add(txtDataDeDemissao);
		listaCompInformacoesFuncionario.add(txtEmail);
		listaCompInformacoesFuncionario.add(txtTelResidencial);
		listaCompInformacoesFuncionario.add(txtTelComercial);
		listaCompInformacoesFuncionario.add(txtNumero);
		listaCompInformacoesFuncionario.add(txtBairro);
		
		ArrayList<Component> listaCompInformacoesProfessor = new ArrayList<>();
		/*Num autorizao ou Num de diploma*/
		/*Resetar borda dos comp que estao na lista*/
		if (!txtNumerodeAutorizacao.getText().isEmpty()) {
			listaCompInformacoesProfessor.add(txtNumeroDoRegistroDoDiploma);
			JTextField bordaPadrao = new JTextField();
			txtNumeroDoRegistroDoDiploma.setBorder(bordaPadrao.getBorder());
		}else if (!txtNumeroDoRegistroDoDiploma.getText().isEmpty()) {
			listaCompInformacoesProfessor.add(txtNumerodeAutorizacao);
			JTextField bordaPadrao = new JTextField();
			txtNumerodeAutorizacao.setBorder(bordaPadrao.getBorder());
		}
		
		if (FuncoesGlobais.verificaCampos(pnlInformacaoFuncionario, listaCompInformacoesFuncionario) == true | 
			(cbCargo.getSelectedIndex() > -1 && cbCargo.getSelectedItem().toString().equals("Professor") && 
			FuncoesGlobais.verificaCampos(pnlInformacaoDeProfessores, listaCompInformacoesProfessor) == true)){
			
			JOptionPane.showMessageDialog(this, "Erro - Os campos em vermelho devem ser preenchidos!", "Sistema", JOptionPane.ERROR_MESSAGE);
			
		}else {
			
				if(JOptionPane.showConfirmDialog(this, 
												"Deseja realmente salvar o novo cadastrado?", 
												"Sistema", 
												JOptionPane.YES_NO_OPTION, 
												JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
					//Salvando o registro
					Funcionario funcionario = new Funcionario();
					
					funcionario.setCargo(cbCargo.getSelectedItem().toString());
					funcionario.setData_de_Admissao(new SimpleDateFormat("dd/MM/yyyy").parse(txtDataDeAdimissao.getText()));
					funcionario.setData_de_Demissao(txtDataDeDemissao.getValue() == null ? null : new SimpleDateFormat("dd/MM/yyyy").parse(txtDataDeDemissao.getText()));
					funcionario.setCPF(txtCPF.getText().replace(".", ""));
					funcionario.setNome(txtNome.getText());
					funcionario.setData_de_Nascimento(new SimpleDateFormat("dd/MM/yyyy").parse(txtDataDeNascimento.getText()));
					funcionario.setRG(txtRG.getText());
					funcionario.setEstado(cbEstado.getSelectedItem().toString());
					funcionario.setEmail(txtEmail.getText().equals(null) || txtEmail.getText().equals("") ? null : txtEmail.getText());
					funcionario.setCidade(txtCidade.getText());
					funcionario.setLogradouro(cbLogradouro.getSelectedItem().toString());
					funcionario.setEndereco(txtEndereco.getText());
					funcionario.setNumero_da_Casa(txtNumero.getText().equals("") || txtNumero.getText().equals(null) ? 0 : Integer.parseInt(txtNumero.getText()));
					funcionario.setBairro(txtBairro.getText().equals(null) || txtBairro.getText().equals("") ? null : txtBairro.getText());
					funcionario.setTel_Residencial(txtTelResidencial.getValue() == null ? null : txtTelResidencial.getText().replace("(", "").replace(")", "").replace("-", ""));
					funcionario.setTel_Comercial(txtTelComercial.getValue() == null ? null : txtTelComercial.getText().replace("(", "").replace(")", "").replaceAll("-", ""));
					funcionario.setCelular(txtCelular.getText().replace("(", "").replace(")", "").replace("-", ""));
					funcionario.setSalario(Double.parseDouble(txtSalario.getText()));
					
					if (cbCargo.getSelectedIndex() > -1 && cbCargo.getSelectedItem().toString().equals("Professor")) {
						funcionario.setFormacao_Academica(txtFormacaoAcademica.getText());
						funcionario.setNumero_de_Autorizacao_da_SER(Integer.parseInt(txtNumerodeAutorizacao.getText()));
						funcionario.setData_de_Autorizacao(new SimpleDateFormat("dd/MM/yyyy").parse(txtDataDeAutorizacaoDaSER.getText()));
						funcionario.setNumero_do_Registro_do_Diploma(Integer.parseInt(txtNumeroDoRegistroDoDiploma.getText()));
					}
					
					
					//Novo registro
					if (txtRegistro.getText().equals("NOVO")) {
						DAOFuncionario dao = new DAOFuncionario();
						dao.novoFuncionario(funcionario);
					}
					
					//Atualiza o registro
					else {
						
					}
					
					FuncoesGlobais.limpaCampos(pnlInformacaoFuncionario);
					FuncoesGlobais.limpaCampos(pnlInformacaoDeProfessores);
					FuncoesGlobais.desativaCampos(pnlInformacaoFuncionario);
					FuncoesGlobais.desativaCampos(pnlInformacaoDeProfessores);
					FuncoesGlobais.desativaCampos(pnlBotoes);
					
					btnNovo.setEnabled(true);
					btnPesquisar.setEnabled(true);
					btnNovo.requestFocus();
					
					//Novo Registro
					if (txtRegistro.getText().equals("NOVO")) {
						JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sistema", JOptionPane.INFORMATION_MESSAGE);
					}
			}
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
		
		
		cbCargo.setSelectedItem(funcionario.getCargo());
		
		txtDataDeAdimissao.setValue(new SimpleDateFormat("dd/MM/yyyy").format(funcionario.getData_de_Admissao()));
		txtDataDeDemissao.setValue(funcionario.getData_de_Demissao() == null ? null : new SimpleDateFormat("dd/MM/yyyy").format(funcionario.getData_de_Demissao()));
		txtRegistro.setText(Integer.toString(funcionario.getRegistro()));
		
		MaskFormatter mask = new MaskFormatter("###.###.###.##");
		mask.setValueContainsLiteralCharacters(false);

		txtCPF.setValue(mask.valueToString(funcionario.getCPF()));
		txtNome.setText(funcionario.getNome());
		txtDataDeNascimento.setValue(funcionario.getData_de_Nascimento() == null ? null : new SimpleDateFormat("dd/MM/yyyy").format(funcionario.getData_de_Nascimento()));
		txtRG.setText(funcionario.getRG());
		cbEstado.setSelectedItem(funcionario.getEstado());
		txtEmail.setText(funcionario.getEmail());
		txtCidade.setText(funcionario.getCidade());
		cbLogradouro.setSelectedItem(funcionario.getLogradouro());
		txtEndereco.setText(funcionario.getEndereco());
		txtNumero.setText(funcionario.getNumero_da_Casa() == 0 ? null : Integer.toString(funcionario.getNumero_da_Casa()));
		txtBairro.setText(funcionario.getBairro());
		
		mask.setMask("(##)#####-####");
		txtCelular.setValue(mask.valueToString(funcionario.getCelular()));
		
		mask.setMask("(##)####-####");
		txtTelComercial.setValue(mask.valueToString(funcionario.getTel_Comercial()));
		txtTelResidencial.setValue(mask.valueToString(funcionario.getTel_Residencial()));
		
		txtSalario.setText(Double.toString(funcionario.getSalario()));
		txtFormacaoAcademica.setText(funcionario.getFormacao_Academica());
		txtDataDeAutorizacaoDaSER.setValue(funcionario.getData_de_Autorizacao() == null ? null : new SimpleDateFormat("dd/MM/yyyy").format(funcionario.getData_de_Autorizacao()));
		txtNumerodeAutorizacao.setText(funcionario.getNumero_de_Autorizacao_da_SER() == 0 ? null : Integer.toString(funcionario.getNumero_de_Autorizacao_da_SER()));
		txtNumeroDoRegistroDoDiploma.setText(funcionario.getNumero_do_Registro_do_Diploma() == 0 ? null : Integer.toString(funcionario.getNumero_do_Registro_do_Diploma()));
	
		FuncoesGlobais.desativaCampos(pnlInformacaoDeProfessores);
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				btnNovo.requestFocusInWindow();
			}
		});
		
	}

	public void resetaFormulario() {
		FuncoesGlobais.desativaCampos(pnlBotoes);
		FuncoesGlobais.limpaCampos(pnlInformacaoDeProfessores);
		FuncoesGlobais.limpaCampos(pnlInformacaoFuncionario);
		FuncoesGlobais.resetaBordaPadrao(pnlInformacaoDeProfessores);
		FuncoesGlobais.resetaBordaPadrao(pnlInformacaoFuncionario);
		FuncoesGlobais.desativaCampos(pnlInformacaoDeProfessores);
		FuncoesGlobais.desativaCampos(pnlInformacaoFuncionario);
		
		btnNovo.setEnabled(true);
		btnPesquisar.setEnabled(true);
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				btnNovo.requestFocusInWindow();
			}
		});
	}
}

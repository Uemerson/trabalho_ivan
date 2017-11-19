


package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JFormattedTextField;

public class frmCadastroFuncionario extends JInternalFrame {
	private static frmCadastroAluno singleton = null;
	private JButton btnExcluir;
	private JPanel pnl;
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
	private JLabel txtTelefoneResidencial;
	private JLabel lblCelular;
	private JTextField txtSalario;
	private JTextField txtFormacaoAcademica;
	private JTextField txtNumerodeAutorizacao;
	private JTextField txtNumeroDoRegistroDoDiploma;
	private JLabel label;
	private JComboBox cbLogradouro;
	private JLabel lblNumero;
	private JTextField txtNumero;
	private JLabel lblCelular_1;
	private JFormattedTextField txtTelComercial;
	private JFormattedTextField txtTelResidencial;
	private JFormattedTextField txtCelular;
	private JFormattedTextField txtDataDeAdimissao;
	private JFormattedTextField txtDataDeDemissao;
	private JFormattedTextField txtCPF;
	private JFormattedTextField txtDataDeNascimento;
	private JFormattedTextField DataDeAutorizacaoDaSER;

	
	public frmCadastroFuncionario() throws ParseException {
		getContentPane().setEnabled(false);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 555, 64);
		panel.setLayout(null);
		getContentPane().add(panel);
		
		btnNovo = new JButton("Novo");
		btnNovo.setBounds(10, 11, 80, 44);
		panel.add(btnNovo);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(100, 11, 80, 44);
		panel.add(btnExcluir);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setEnabled(false);
		btnAlterar.setBounds(190, 11, 80, 44);
		panel.add(btnAlterar);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(280, 11, 80, 44);
		panel.add(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(370, 11, 80, 44);
		panel.add(btnCancelar);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setEnabled(false);
		btnPesquisar.setBounds(460, 11, 85, 44);
		panel.add(btnPesquisar);
		
		pnl = new JPanel();
		pnl.setBorder(new TitledBorder(null, "Informa\u00E7\u00E3o do Funcionario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl.setBounds(10, 74, 788, 334);
		getContentPane().add(pnl);
		pnl.setLayout(null);
		
		lblFuncionario = new JLabel("Cargo");
		lblFuncionario.setHorizontalAlignment(SwingConstants.LEFT);
		lblFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFuncionario.setBounds(10, 20, 90, 28);
		pnl.add(lblFuncionario);
		
		cbCargo = new JComboBox();
		cbCargo.setEnabled(false);
		cbCargo.setModel(new DefaultComboBoxModel(new String[] {"Professor", "Secret\u00E1rio", "Coordenador", "Diretor"}));
		cbCargo.setToolTipText("");
		cbCargo.setBounds(120, 22, 90, 28);
		pnl.add(cbCargo);
		
		lblRegistro = new JLabel("Registro");
		lblRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegistro.setBounds(10, 61, 90, 28);
		pnl.add(lblRegistro);
		
		txtRegistro = new JTextField();
		txtRegistro.setEnabled(false);
		txtRegistro.setColumns(10);
		txtRegistro.setBounds(120, 61, 338, 28);
		pnl.add(txtRegistro);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 100, 90, 28);
		pnl.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setEnabled(false);
		txtNome.setColumns(10);
		txtNome.setBounds(120, 100, 338, 28);
		pnl.add(txtNome);
		
		lblCPF = new JLabel("CPF");
		lblCPF.setHorizontalAlignment(SwingConstants.LEFT);
		lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCPF.setBounds(468, 61, 129, 28);
		pnl.add(lblCPF);
		
		lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setHorizontalAlignment(SwingConstants.LEFT);
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDeNascimento.setBounds(468, 100, 129, 28);
		pnl.add(lblDataDeNascimento);
		
		lblRG = new JLabel("RG");
		lblRG.setHorizontalAlignment(SwingConstants.LEFT);
		lblRG.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRG.setBounds(10, 139, 90, 28);
		pnl.add(lblRG);
		
		lblEndereco = new JLabel("Endere\u00E7o");
		lblEndereco.setHorizontalAlignment(SwingConstants.LEFT);
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEndereco.setBounds(257, 217, 74, 28);
		pnl.add(lblEndereco);
		
		txtRG = new JTextField();
		txtRG.setEnabled(false);
		txtRG.setColumns(10);
		txtRG.setBounds(120, 139, 338, 28);
		pnl.add(txtRG);
		
		txtEndereco = new JTextField();
		txtEndereco.setEnabled(false);
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(359, 219, 328, 28);
		pnl.add(txtEndereco);
		
		lblCidade = new JLabel("Cidade");
		lblCidade.setHorizontalAlignment(SwingConstants.LEFT);
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCidade.setBounds(468, 178, 129, 28);
		pnl.add(lblCidade);
		
		txtCidade = new JTextField();
		txtCidade.setEnabled(false);
		txtCidade.setColumns(10);
		txtCidade.setBounds(603, 180, 165, 28);
		pnl.add(txtCidade);
		
		lblBairro = new JLabel("Bairro");
		lblBairro.setHorizontalAlignment(SwingConstants.LEFT);
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBairro.setBounds(10, 256, 62, 28);
		pnl.add(lblBairro);
		
		txtBairro = new JTextField();
		txtBairro.setEnabled(false);
		txtBairro.setColumns(10);
		txtBairro.setBounds(120, 258, 149, 28);
		pnl.add(txtBairro);
		
		lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(10, 178, 90, 28);
		pnl.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setEnabled(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(120, 180, 338, 28);
		pnl.add(txtEmail);
		
		lblEstado = new JLabel("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.LEFT);
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstado.setBounds(468, 139, 80, 28);
		pnl.add(lblEstado);
		
		cbEstado = new JComboBox();
		cbEstado.setEnabled(false);
		cbEstado.setModel(new DefaultComboBoxModel(new String[] {"MG", "SP", "RJ", "ES", "RS", "PR", "SC", "MT", "GO", "DF", "MTS", "MA", "RN", "AL", "PE", "JP"}));
		cbEstado.setToolTipText("");
		cbEstado.setBounds(603, 141, 165, 28);
		pnl.add(cbEstado);
		
		lblTelefoneComercial = new JLabel("Tel Comercial");
		lblTelefoneComercial.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefoneComercial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefoneComercial.setBounds(10, 295, 90, 28);
		pnl.add(lblTelefoneComercial);
		
		txtTelefoneResidencial = new JLabel("Tel Residencial");
		txtTelefoneResidencial.setHorizontalAlignment(SwingConstants.LEFT);
		txtTelefoneResidencial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTelefoneResidencial.setBounds(279, 256, 104, 28);
		pnl.add(txtTelefoneResidencial);
		
		JLabel lblDataDeAdimissao = new JLabel("Data de Adimiss\u00E3o");
		lblDataDeAdimissao.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataDeAdimissao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDeAdimissao.setBounds(220, 22, 129, 28);
		pnl.add(lblDataDeAdimissao);
		
		JLabel lblDataDeDemissao = new JLabel("Data de Demiss\u00E3o");
		lblDataDeDemissao.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataDeDemissao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDeDemissao.setBounds(530, 22, 129, 28);
		pnl.add(lblDataDeDemissao);
		
		JLabel lblSalario = new JLabel("Sal\u00E1rio");
		lblSalario.setHorizontalAlignment(SwingConstants.LEFT);
		lblSalario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSalario.setBounds(279, 295, 90, 28);
		pnl.add(lblSalario);
		
		txtSalario = new JTextField();
		txtSalario.setEnabled(false);
		txtSalario.setColumns(10);
		txtSalario.setBounds(393, 297, 149, 28);
		pnl.add(txtSalario);
		
		label = new JLabel("Logradouro");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(10, 217, 80, 28);
		pnl.add(label);
		
		cbLogradouro = new JComboBox();
		cbLogradouro.setEnabled(false);
		cbLogradouro.setModel(new DefaultComboBoxModel(new String[] {"Avenida", "Rua", "Pra\u00E7a", "Aeroporto"}));
		cbLogradouro.setBounds(118, 217, 108, 28);
		pnl.add(cbLogradouro);
		
		lblNumero = new JLabel(",");
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumero.setBounds(697, 217, 14, 28);
		pnl.add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setEnabled(false);
		txtNumero.setHorizontalAlignment(SwingConstants.RIGHT);
		txtNumero.setColumns(10);
		txtNumero.setBounds(721, 217, 47, 28);
		pnl.add(txtNumero);
		
		lblCelular_1 = new JLabel("Celular");
		lblCelular_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCelular_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCelular_1.setBounds(547, 258, 62, 28);
		pnl.add(lblCelular_1);
		
		txtTelComercial = new JFormattedTextField(new MaskFormatter("(##)####-####"));
		txtTelComercial.setEnabled(false);
		txtTelComercial.setBounds(120, 297, 149, 28);
		pnl.add(txtTelComercial);
		
		txtTelResidencial = new JFormattedTextField(new MaskFormatter("(##)####-####"));
		txtTelResidencial.setEnabled(false);
		txtTelResidencial.setBounds(393, 258, 149, 28);
		pnl.add(txtTelResidencial);
		
		txtCelular = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		txtCelular.setEnabled(false);
		txtCelular.setBounds(619, 258, 149, 28);
		pnl.add(txtCelular);
		
		txtDataDeAdimissao = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataDeAdimissao.setEnabled(false);
		txtDataDeAdimissao.setBounds(359, 26, 99, 28);
		pnl.add(txtDataDeAdimissao);
		
		txtDataDeDemissao = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataDeDemissao.setEnabled(false);
		txtDataDeDemissao.setBounds(669, 26, 99, 28);
		pnl.add(txtDataDeDemissao);
		
		txtCPF = new JFormattedTextField(new MaskFormatter("###.###.###.##"));
		txtCPF.setEnabled(false);
		txtCPF.setBounds(603, 67, 165, 28);
		pnl.add(txtCPF);
		
		txtDataDeNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataDeNascimento.setEnabled(false);
		txtDataDeNascimento.setBounds(603, 106, 165, 28);
		pnl.add(txtDataDeNascimento);
		
		JPanel pnlInformacaoDeProfessores = new JPanel();
		pnlInformacaoDeProfessores.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informa\u00E7\u00F5e de professor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlInformacaoDeProfessores.setBounds(10, 419, 788, 138);
		getContentPane().add(pnlInformacaoDeProfessores);
		pnlInformacaoDeProfessores.setLayout(null);
		
		JLabel lblFormaoAcademicaqualificao = new JLabel("Forma\u00E7\u00E3o acad\u00EAmica/qualifica\u00E7\u00E3o ");
		lblFormaoAcademicaqualificao.setHorizontalAlignment(SwingConstants.LEFT);
		lblFormaoAcademicaqualificao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFormaoAcademicaqualificao.setBounds(10, 19, 221, 28);
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
		txtNumerodeAutorizacao.setBounds(241, 60, 163, 28);
		pnlInformacaoDeProfessores.add(txtNumerodeAutorizacao);
		
		JLabel lblDataDeAutorizacao = new JLabel("Data de Autoriza\u00E7\u00E3o da SER");
		lblDataDeAutorizacao.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataDeAutorizacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDeAutorizacao.setBounds(433, 60, 184, 28);
		pnlInformacaoDeProfessores.add(lblDataDeAutorizacao);
		
		JLabel lblNumeroDoRegistroDoDiploma = new JLabel("N\u00FAmero do registro do Diploma");
		lblNumeroDoRegistroDoDiploma.setHorizontalAlignment(SwingConstants.LEFT);
		lblNumeroDoRegistroDoDiploma.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumeroDoRegistroDoDiploma.setBounds(10, 97, 221, 28);
		pnlInformacaoDeProfessores.add(lblNumeroDoRegistroDoDiploma);
		
		txtNumeroDoRegistroDoDiploma = new JTextField();
		txtNumeroDoRegistroDoDiploma.setEnabled(false);
		txtNumeroDoRegistroDoDiploma.setColumns(10);
		txtNumeroDoRegistroDoDiploma.setBounds(241, 99, 163, 28);
		pnlInformacaoDeProfessores.add(txtNumeroDoRegistroDoDiploma);
		
		DataDeAutorizacaoDaSER = new JFormattedTextField(new MaskFormatter("##/##/####"));
		DataDeAutorizacaoDaSER.setEnabled(false);
		DataDeAutorizacaoDaSER.setBounds(651, 60, 116, 28);
		pnlInformacaoDeProfessores.add(DataDeAutorizacaoDaSER);
		
		lblCelular = new JLabel("Celular");
		lblCelular.setBounds(516, 11, 67, 28);
		getContentPane().add(lblCelular);
		lblCelular.setHorizontalAlignment(SwingConstants.CENTER);
		lblCelular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		setBounds(100, 100, 821, 597);
		setClosable(true);
		setTitle("Cadastro de Funcionario");

	}
}
	
	/*public static frmCadastroFuncionario getFrmCadastroFuncionario() {
		if (singleton == null) {
			singleton = new frmCadastroFuncionario();
		}
		
		//singleton.setVisible(true);
		
		return singleton;
	}
}*/

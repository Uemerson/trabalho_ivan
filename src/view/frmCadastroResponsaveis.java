package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import java.awt.Color;


public class frmCadastroResponsaveis extends JInternalFrame {
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnNovo;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnPesquisar;
	private JPanel pnlCadastroResponsavel;
	private JTextField txtRegistro;
	private JLabel lblRegistro;
	private JLabel lblNome;
	private JTextField txtNome;
	private JLabel lblRg;
	private JTextField txtRG;
	private JLabel lblCpf;
	private JFormattedTextField txtCPF;
	private JLabel lblGrauDeInstrucao;
	private JComboBox comboBox;
	private JLabel lblProfissao;
	private JTextField textField;
	private JLabel lblDataDeNascimento;
	private JFormattedTextField txtDataDeNascimento;
	private JPanel pnlLocalDeTrabalho;
	private JLabel lblNomeDoLocal;
	private JTextField TxtNomeDoLocal;
	private JLabel label;
	private JComboBox cbLogradouro;
	private JLabel lblEndereco;
	private JTextField txtEndereco;
	private JLabel lblNumero;
	private JTextField txtNumero;
	private JPanel pnlPerfilSocioEconomico;
	private JLabel lblRenda;
	private JComboBox cbRenda;
	private JLabel lblCasaPropria;
	private JComboBox comboBox_1;
	private JLabel lblNumeroDeFilhos;
	private JComboBox comboBox_2;
	private JLabel lblNumeroDePessoasQueResidemNaCasa;
	private JComboBox NumeroDePessoasQueResidemNaCasa;
	private JLabel lblHorario;
	private JFormattedTextField txtHorario;
	private JComboBox cbHorario;

	
	public frmCadastroResponsaveis() throws ParseException {
		setBounds(100, 100, 840, 533);
		setClosable(true);
		setTitle("Cadastro de Responsavel");
		getContentPane().setLayout(null);
		
		JPanel pnlBotoes = new JPanel();
		pnlBotoes.setBounds(10, 11, 804, 64);
		pnlBotoes.setLayout(null);
		getContentPane().add(pnlBotoes);
		
		btnNovo = new JButton("Novo");
		btnNovo.setBounds(10, 11, 80, 44);
		pnlBotoes.add(btnNovo);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(100, 11, 80, 44);
		pnlBotoes.add(btnExcluir);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setEnabled(false);
		btnAlterar.setBounds(190, 11, 80, 44);
		pnlBotoes.add(btnAlterar);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(280, 11, 80, 44);
		pnlBotoes.add(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(370, 11, 80, 44);
		pnlBotoes.add(btnCancelar);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setEnabled(false);
		btnPesquisar.setBounds(460, 11, 85, 44);
		pnlBotoes.add(btnPesquisar);
		
		pnlCadastroResponsavel = new JPanel();
		pnlCadastroResponsavel.setBounds(10, 86, 804, 179);
		pnlCadastroResponsavel.setBorder(new TitledBorder(null, "Cadastro de Responsavel", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlCadastroResponsavel);
		pnlCadastroResponsavel.setLayout(null);
		
		lblRegistro = new JLabel("Registro");
		lblRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegistro.setBounds(10, 17, 90, 28);
		pnlCadastroResponsavel.add(lblRegistro);
		
		txtRegistro = new JTextField();
		txtRegistro.setEnabled(false);
		txtRegistro.setColumns(10);
		txtRegistro.setBounds(110, 17, 338, 28);
		pnlCadastroResponsavel.add(txtRegistro);
		
		lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 56, 90, 28);
		pnlCadastroResponsavel.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setEnabled(false);
		txtNome.setColumns(10);
		txtNome.setBounds(110, 56, 338, 28);
		pnlCadastroResponsavel.add(txtNome);
		
		lblRg = new JLabel("RG");
		lblRg.setHorizontalAlignment(SwingConstants.LEFT);
		lblRg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRg.setBounds(10, 95, 90, 28);
		pnlCadastroResponsavel.add(lblRg);
		
		txtRG = new JTextField();
		txtRG.setEnabled(false);
		txtRG.setColumns(10);
		txtRG.setBounds(110, 95, 338, 28);
		pnlCadastroResponsavel.add(txtRG);
		
		lblCpf = new JLabel("CPF");
		lblCpf.setHorizontalAlignment(SwingConstants.LEFT);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpf.setBounds(458, 17, 90, 28);
		pnlCadastroResponsavel.add(lblCpf);
		
		txtCPF = new JFormattedTextField(new MaskFormatter("###.###.###.##"));
		txtCPF.setEnabled(false);
		txtCPF.setBounds(558, 18, 236, 28);
		pnlCadastroResponsavel.add(txtCPF);
		
		lblGrauDeInstrucao = new JLabel("Grau de Instru\u00E7\u00E3o");
		lblGrauDeInstrucao.setHorizontalAlignment(SwingConstants.LEFT);
		lblGrauDeInstrucao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGrauDeInstrucao.setBounds(458, 56, 129, 28);
		pnlCadastroResponsavel.add(lblGrauDeInstrucao);
		
		comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Ensino Fundamental Incompleto", "Ensino Fundamental Completo", "Ensino M\u00E9dio Incompleto", "Ensino M\u00E9dio Completo", "Ensino Superior Incompleto", "Ensino Superio Completo"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(597, 56, 197, 28);
		pnlCadastroResponsavel.add(comboBox);
		
		lblProfissao = new JLabel("Profiss\u00E3o");
		lblProfissao.setHorizontalAlignment(SwingConstants.LEFT);
		lblProfissao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProfissao.setBounds(458, 95, 90, 28);
		pnlCadastroResponsavel.add(lblProfissao);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setColumns(10);
		textField.setBounds(543, 97, 251, 28);
		pnlCadastroResponsavel.add(textField);
		
		lblDataDeNascimento = new JLabel("Data De Nascimento");
		lblDataDeNascimento.setHorizontalAlignment(SwingConstants.LEFT);
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDeNascimento.setBounds(10, 134, 129, 28);
		pnlCadastroResponsavel.add(lblDataDeNascimento);
		
		txtDataDeNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataDeNascimento.setEnabled(false);
		txtDataDeNascimento.setBounds(149, 134, 123, 28);
		pnlCadastroResponsavel.add(txtDataDeNascimento);
		
		pnlLocalDeTrabalho = new JPanel();
		pnlLocalDeTrabalho.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Local de Trabalho", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlLocalDeTrabalho.setBounds(10, 276, 804, 100);
		getContentPane().add(pnlLocalDeTrabalho);
		pnlLocalDeTrabalho.setLayout(null);
		
		lblNomeDoLocal = new JLabel("Nome do Local");
		lblNomeDoLocal.setHorizontalAlignment(SwingConstants.LEFT);
		lblNomeDoLocal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeDoLocal.setBounds(10, 22, 99, 28);
		pnlLocalDeTrabalho.add(lblNomeDoLocal);
		
		TxtNomeDoLocal = new JTextField();
		TxtNomeDoLocal.setEnabled(false);
		TxtNomeDoLocal.setColumns(10);
		TxtNomeDoLocal.setBounds(112, 22, 238, 28);
		pnlLocalDeTrabalho.add(TxtNomeDoLocal);
		
		label = new JLabel("Logradouro");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(10, 61, 80, 28);
		pnlLocalDeTrabalho.add(label);
		
		cbLogradouro = new JComboBox();
		cbLogradouro.setEnabled(false);
		cbLogradouro.setModel(new DefaultComboBoxModel(new String[] {"Avenida", "Rua", "Pra\u00E7a", "Aeroporto", ""}));
		cbLogradouro.setBounds(132, 61, 122, 28);
		pnlLocalDeTrabalho.add(cbLogradouro);
		
		lblEndereco = new JLabel("Endere\u00E7o");
		lblEndereco.setHorizontalAlignment(SwingConstants.LEFT);
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEndereco.setBounds(281, 61, 69, 28);
		pnlLocalDeTrabalho.add(lblEndereco);
		
		txtEndereco = new JTextField();
		txtEndereco.setEnabled(false);
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(360, 61, 328, 28);
		pnlLocalDeTrabalho.add(txtEndereco);
		
		lblNumero = new JLabel(",");
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumero.setBounds(698, 61, 14, 28);
		pnlLocalDeTrabalho.add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setHorizontalAlignment(SwingConstants.RIGHT);
		txtNumero.setColumns(10);
		txtNumero.setBounds(722, 61, 72, 28);
		pnlLocalDeTrabalho.add(txtNumero);
		
		lblHorario = new JLabel("Horario");
		lblHorario.setHorizontalAlignment(SwingConstants.LEFT);
		lblHorario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHorario.setBounds(360, 22, 63, 28);
		pnlLocalDeTrabalho.add(lblHorario);
		
		txtHorario = new JFormattedTextField(new MaskFormatter("##:##"));
		txtHorario.setEnabled(false);
		txtHorario.setBounds(433, 22, 47, 28);
		pnlLocalDeTrabalho.add(txtHorario);
		
		cbHorario = new JComboBox();
		cbHorario.setEnabled(false);
		cbHorario.setModel(new DefaultComboBoxModel(new String[] {"AM", "PM"}));
		cbHorario.setBounds(490, 22, 58, 28);
		pnlLocalDeTrabalho.add(cbHorario);
		
		pnlPerfilSocioEconomico = new JPanel();
		pnlPerfilSocioEconomico.setBorder(new TitledBorder(null, "Perfil S\u00F3cio Economico", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlPerfilSocioEconomico.setBounds(10, 387, 554, 106);
		getContentPane().add(pnlPerfilSocioEconomico);
		pnlPerfilSocioEconomico.setLayout(null);
		
		lblRenda = new JLabel("Renda");
		lblRenda.setHorizontalAlignment(SwingConstants.LEFT);
		lblRenda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRenda.setBounds(10, 21, 69, 28);
		pnlPerfilSocioEconomico.add(lblRenda);
		
		cbRenda = new JComboBox();
		cbRenda.setEnabled(false);
		cbRenda.setModel(new DefaultComboBoxModel(new String[] {"1 Sal\u00E1rio", "2 Sal\u00E1rios", "3 Sal\u00E1rios", "4 Sal\u00E1rios", "5 Sal\u00E1rios", "6 Sal\u00E1rios ou mais"}));
		cbRenda.setBounds(59, 21, 119, 28);
		pnlPerfilSocioEconomico.add(cbRenda);
		
		lblCasaPropria = new JLabel("Casa Propria");
		lblCasaPropria.setHorizontalAlignment(SwingConstants.LEFT);
		lblCasaPropria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCasaPropria.setBounds(188, 21, 95, 28);
		pnlPerfilSocioEconomico.add(lblCasaPropria);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setEnabled(false);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Sim", "N\u00E3o"}));
		comboBox_1.setBounds(293, 21, 58, 28);
		pnlPerfilSocioEconomico.add(comboBox_1);
		
		lblNumeroDeFilhos = new JLabel("Numero de Filhos");
		lblNumeroDeFilhos.setHorizontalAlignment(SwingConstants.LEFT);
		lblNumeroDeFilhos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumeroDeFilhos.setBounds(361, 21, 130, 28);
		pnlPerfilSocioEconomico.add(lblNumeroDeFilhos);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setEnabled(false);
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6 ou mais"}));
		comboBox_2.setBounds(478, 21, 66, 28);
		pnlPerfilSocioEconomico.add(comboBox_2);
		
		lblNumeroDePessoasQueResidemNaCasa = new JLabel("Numero de Pessoas que Residem na Casa");
		lblNumeroDePessoasQueResidemNaCasa.setHorizontalAlignment(SwingConstants.LEFT);
		lblNumeroDePessoasQueResidemNaCasa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumeroDePessoasQueResidemNaCasa.setBounds(10, 60, 283, 28);
		pnlPerfilSocioEconomico.add(lblNumeroDePessoasQueResidemNaCasa);
		
		NumeroDePessoasQueResidemNaCasa = new JComboBox();
		NumeroDePessoasQueResidemNaCasa.setEnabled(false);
		NumeroDePessoasQueResidemNaCasa.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6 ou mais"}));
		NumeroDePessoasQueResidemNaCasa.setBounds(293, 60, 69, 28);
		pnlPerfilSocioEconomico.add(NumeroDePessoasQueResidemNaCasa);

	}

}

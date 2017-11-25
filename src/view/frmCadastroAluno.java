package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import controler.FuncoesGlobais;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;


public class frmCadastroAluno extends JInternalFrame implements ActionListener {
	private JButton btnNovo;
	private JButton btnExcluir;
	private JButton btnAlterar;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnPesquisar;
	private JTextField txtNome;
	private JLabel lblRegistro;
	private JLabel lblNome;
	private JLabel lblRg;
	private JLabel lblOrgaoEmissor;
	private JLabel lblCpf;
	private JTextField txtRegistro;
	private JTextField txtRG;
	private JTextField txtOrgaoEmissor;
	private JComboBox cbSexo;
	private JLabel lblSexo;
	private JLabel lblCor;
	private JComboBox cbCor;
	private JLabel lblRaca;
	private JComboBox cbRa�a;
	private JLabel lblEndereco;
	private JTextField txtEndereco;
	private JLabel lblDataDeNascimento;
	private JLabel lblTelefoneResidencial;
	private JLabel lblTelefoneComercial;
	private JLabel lblCelular;
	private JLabel lblEmail;
	private JTextField textEmail;
	private JLabel lblLocalDeOrigem;
	private JLabel lblRedeEstabelecimentoDe;
	private JLabel lblSituacaoDoAlunoAnoAnterior;
	private JLabel lblObservacoesDoAluno;
	private JTextField txtLocalOrigem;
	private JTextField txtRedeEstabelecimentoDeOrdemDoAluno;
	private JTextField txtSituacaoAnoAnterior;
	private JEditorPane edpObservacoesAluno;
	private JFormattedTextField txtTelefoneResidencial;
	private JLabel lblLogradouro;
	private JComboBox cbLogradouro;
	private JLabel lblNumero;
	private JTextField textField;
	private JFormattedTextField txtTelResidencial;
	private JFormattedTextField txtCelular;
	private JFormattedTextField txtDataDeNascimento;
	private JFormattedTextField txtCPF;
	
	private static frmCadastroAluno singleton = null;
	private JPanel pnlInformacoesPessoaisDoAluno;
	private JPanel pnlInformacoesAluno;
	private JPanel pnlBotoes;
	
	public static frmCadastroAluno getFrmCadastroAluno() throws ParseException {
		if (singleton == null) {
			singleton = new frmCadastroAluno();
		}
		
		return singleton;
	}
	
	public frmCadastroAluno() throws ParseException {
		setClosable(true);
		setTitle("Cadastro de aluno");
		setBounds(100, 100, 840, 622);
		getContentPane().setLayout(null);
		
		pnlBotoes = new JPanel();
		pnlBotoes.setBounds(10, 11, 555, 64);
		getContentPane().add(pnlBotoes);
		pnlBotoes.setLayout(null);
		
		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(this);
		btnNovo.setBounds(10, 11, 80, 44);
		pnlBotoes.add(btnNovo);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(100, 11, 80, 44);
		pnlBotoes.add(btnExcluir);
		btnExcluir.setEnabled(false);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(190, 11, 80, 44);
		pnlBotoes.add(btnAlterar);
		btnAlterar.setEnabled(false);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(this);
		btnSalvar.setBounds(280, 11, 80, 44);
		pnlBotoes.add(btnSalvar);
		btnSalvar.setEnabled(false);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(370, 11, 80, 44);
		pnlBotoes.add(btnCancelar);
		btnCancelar.setEnabled(false);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(460, 11, 85, 44);
		pnlBotoes.add(btnPesquisar);
		
		pnlInformacoesAluno = new JPanel();
		pnlInformacoesAluno.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es do aluno", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlInformacoesAluno.setBounds(10, 86, 799, 264);
		getContentPane().add(pnlInformacoesAluno);
		pnlInformacoesAluno.setLayout(null);
		
		lblRegistro = new JLabel("Registro");
		lblRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegistro.setBounds(10, 17, 80, 28);
		pnlInformacoesAluno.add(lblRegistro);
		
		txtNome = new JTextField();
		txtNome.setEnabled(false);
		txtNome.setBounds(100, 58, 338, 28);
		pnlInformacoesAluno.add(txtNome);
		txtNome.setColumns(10);
		
		lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 58, 80, 28);
		pnlInformacoesAluno.add(lblNome);
		
		txtRegistro = new JTextField();
		txtRegistro.setEnabled(false);
		txtRegistro.setColumns(10);
		txtRegistro.setBounds(100, 19, 338, 28);
		pnlInformacoesAluno.add(txtRegistro);
		
		lblRg = new JLabel("RG");
		lblRg.setHorizontalAlignment(SwingConstants.LEFT);
		lblRg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRg.setBounds(10, 97, 80, 28);
		pnlInformacoesAluno.add(lblRg);
		
		txtRG = new JTextField();
		txtRG.setEnabled(false);
		txtRG.setColumns(10);
		txtRG.setBounds(100, 97, 338, 28);
		pnlInformacoesAluno.add(txtRG);
		
		lblOrgaoEmissor = new JLabel("\u00D3rg\u00E3o  Emissor");
		lblOrgaoEmissor.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrgaoEmissor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOrgaoEmissor.setBounds(468, 97, 101, 28);
		pnlInformacoesAluno.add(lblOrgaoEmissor);
		
		txtOrgaoEmissor = new JTextField();
		txtOrgaoEmissor.setEnabled(false);
		txtOrgaoEmissor.setColumns(10);
		txtOrgaoEmissor.setBounds(646, 97, 143, 28);
		pnlInformacoesAluno.add(txtOrgaoEmissor);
		
		lblCpf = new JLabel("CPF");
		lblCpf.setHorizontalAlignment(SwingConstants.CENTER);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpf.setBounds(448, 19, 80, 28);
		pnlInformacoesAluno.add(lblCpf);
		
		cbSexo = new JComboBox();
		cbSexo.setEnabled(false);
		cbSexo.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Feminino"}));
		cbSexo.setSelectedItem(null);
		cbSexo.setBounds(697, 177, 92, 28);
		pnlInformacoesAluno.add(cbSexo);
		
		lblSexo = new JLabel("Sexo");
		lblSexo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSexo.setBounds(607, 177, 80, 28);
		pnlInformacoesAluno.add(lblSexo);
		
		lblCor = new JLabel("Cor");
		lblCor.setHorizontalAlignment(SwingConstants.CENTER);
		lblCor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCor.setBounds(607, 136, 80, 28);
		pnlInformacoesAluno.add(lblCor);
		
		cbCor = new JComboBox();
		cbCor.setEnabled(false);
		cbCor.setModel(new DefaultComboBoxModel(new String[] {"Branco", "Moreno", "Negro", "Pardo"}));
		cbCor.setBounds(697, 136, 92, 28);
		cbCor.setSelectedItem(null);
		pnlInformacoesAluno.add(cbCor);
		
		lblRaca = new JLabel("Ra\u00E7a");
		lblRaca.setHorizontalAlignment(SwingConstants.CENTER);
		lblRaca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRaca.setBounds(448, 177, 80, 28);
		pnlInformacoesAluno.add(lblRaca);
		
		cbRa�a = new JComboBox();
		cbRa�a.setEnabled(false);
		cbRa�a.setBounds(515, 177, 90, 28);
		cbRa�a.setSelectedItem(null);
		pnlInformacoesAluno.add(cbRa�a);
		
		lblEndereco = new JLabel("Endere\u00E7o");
		lblEndereco.setHorizontalAlignment(SwingConstants.LEFT);
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEndereco.setBounds(178, 136, 69, 28);
		pnlInformacoesAluno.add(lblEndereco);
		
		txtEndereco = new JTextField();
		txtEndereco.setEnabled(false);
		txtEndereco.setHorizontalAlignment(SwingConstants.RIGHT);
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(558, 136, 47, 28);
		pnlInformacoesAluno.add(txtEndereco);
		
		lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDeNascimento.setBounds(468, 58, 129, 28);
		pnlInformacoesAluno.add(lblDataDeNascimento);
		
		lblTelefoneResidencial = new JLabel("Telefone Residencial");
		lblTelefoneResidencial.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefoneResidencial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefoneResidencial.setBounds(288, 214, 129, 28);
		pnlInformacoesAluno.add(lblTelefoneResidencial);
		
		lblTelefoneComercial = new JLabel("Telefone Comercial");
		lblTelefoneComercial.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefoneComercial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefoneComercial.setBounds(10, 214, 119, 28);
		pnlInformacoesAluno.add(lblTelefoneComercial);
		
		lblCelular = new JLabel("Celular");
		lblCelular.setHorizontalAlignment(SwingConstants.CENTER);
		lblCelular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCelular.setBounds(565, 214, 80, 28);
		pnlInformacoesAluno.add(lblCelular);
		
		lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(10, 177, 80, 28);
		pnlInformacoesAluno.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setEnabled(false);
		textEmail.setColumns(10);
		textEmail.setBounds(100, 177, 338, 28);
		pnlInformacoesAluno.add(textEmail);
		
		txtTelefoneResidencial = new JFormattedTextField(new MaskFormatter("(##)####-####"));
		txtTelefoneResidencial.setEnabled(false);
		txtTelefoneResidencial.setBounds(139, 214, 139, 30);
		pnlInformacoesAluno.add(txtTelefoneResidencial);
		
		lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setHorizontalAlignment(SwingConstants.LEFT);
		lblLogradouro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLogradouro.setBounds(10, 136, 80, 28);
		pnlInformacoesAluno.add(lblLogradouro);
		
		cbLogradouro = new JComboBox();
		cbLogradouro.setEnabled(false);
		cbLogradouro.setModel(new DefaultComboBoxModel(new String[] {"Avenida", "Rua", "Pra\u00E7a", "Zona Rural"}));
		cbLogradouro.setBounds(100, 136, 69, 28);
		cbLogradouro.setSelectedItem(null);
		pnlInformacoesAluno.add(cbLogradouro);
		
		lblNumero = new JLabel(",");
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumero.setBounds(534, 136, 14, 28);
		pnlInformacoesAluno.add(lblNumero);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setColumns(10);
		textField.setBounds(257, 136, 271, 28);
		pnlInformacoesAluno.add(textField);
		
		txtTelResidencial = new JFormattedTextField(new MaskFormatter("(##)####-####"));
		txtTelResidencial.setEnabled(false);
		txtTelResidencial.setBounds(430, 214, 139, 30);
		pnlInformacoesAluno.add(txtTelResidencial);
		
		txtCelular = new JFormattedTextField(new MaskFormatter("(##)####-####"));
		txtCelular.setEnabled(false);
		txtCelular.setBounds(646, 214, 143, 30);
		pnlInformacoesAluno.add(txtCelular);
		
		txtDataDeNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataDeNascimento.setEnabled(false);
		txtDataDeNascimento.setBounds(646, 58, 143, 30);
		pnlInformacoesAluno.add(txtDataDeNascimento);
		
		txtCPF = new JFormattedTextField(new MaskFormatter("###.###.###.##"));
		txtCPF.setEnabled(false);
		txtCPF.setBounds(555, 19, 234, 30);
		pnlInformacoesAluno.add(txtCPF);
		
		pnlInformacoesPessoaisDoAluno = new JPanel();
		pnlInformacoesPessoaisDoAluno.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es Pessoais do Aluno", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlInformacoesPessoaisDoAluno.setBounds(10, 362, 799, 220);
		getContentPane().add(pnlInformacoesPessoaisDoAluno);
		pnlInformacoesPessoaisDoAluno.setLayout(null);
		
		lblLocalDeOrigem = new JLabel("Local de Origem do Aluno");
		lblLocalDeOrigem.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocalDeOrigem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLocalDeOrigem.setBounds(10, 25, 198, 28);
		pnlInformacoesPessoaisDoAluno.add(lblLocalDeOrigem);
		
		lblRedeEstabelecimentoDe = new JLabel("Rede Estabelecimento de Ordem do Aluno");
		lblRedeEstabelecimentoDe.setHorizontalAlignment(SwingConstants.LEFT);
		lblRedeEstabelecimentoDe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRedeEstabelecimentoDe.setBounds(10, 64, 283, 28);
		pnlInformacoesPessoaisDoAluno.add(lblRedeEstabelecimentoDe);
		
		lblSituacaoDoAlunoAnoAnterior = new JLabel("Situa\u00E7\u00E3o do Aluno no Ano Anterior");
		lblSituacaoDoAlunoAnoAnterior.setHorizontalAlignment(SwingConstants.LEFT);
		lblSituacaoDoAlunoAnoAnterior.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSituacaoDoAlunoAnoAnterior.setBounds(10, 103, 221, 28);
		pnlInformacoesPessoaisDoAluno.add(lblSituacaoDoAlunoAnoAnterior);
		
		lblObservacoesDoAluno = new JLabel("Observa\u00E7\u00F5es do Aluno");
		lblObservacoesDoAluno.setHorizontalAlignment(SwingConstants.LEFT);
		lblObservacoesDoAluno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblObservacoesDoAluno.setBounds(10, 142, 210, 28);
		pnlInformacoesPessoaisDoAluno.add(lblObservacoesDoAluno);
		
		txtLocalOrigem = new JTextField();
		txtLocalOrigem.setEnabled(false);
		txtLocalOrigem.setColumns(10);
		txtLocalOrigem.setBounds(303, 25, 486, 28);
		pnlInformacoesPessoaisDoAluno.add(txtLocalOrigem);
		
		txtRedeEstabelecimentoDeOrdemDoAluno = new JTextField();
		txtRedeEstabelecimentoDeOrdemDoAluno.setEnabled(false);
		txtRedeEstabelecimentoDeOrdemDoAluno.setColumns(10);
		txtRedeEstabelecimentoDeOrdemDoAluno.setBounds(303, 64, 486, 28);
		pnlInformacoesPessoaisDoAluno.add(txtRedeEstabelecimentoDeOrdemDoAluno);
		
		txtSituacaoAnoAnterior = new JTextField();
		txtSituacaoAnoAnterior.setEnabled(false);
		txtSituacaoAnoAnterior.setColumns(10);
		txtSituacaoAnoAnterior.setBounds(303, 103, 486, 28);
		pnlInformacoesPessoaisDoAluno.add(txtSituacaoAnoAnterior);
		
		edpObservacoesAluno = new JEditorPane();
		edpObservacoesAluno.setEnabled(false);
		edpObservacoesAluno.setBounds(303, 142, 486, 67);
		pnlInformacoesPessoaisDoAluno.add(edpObservacoesAluno);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNovo) {
			btnNovo_click();
		}else if (e.getSource() == btnCancelar) {
			btnCancelar_click();
		}else if (e.getSource() == btnSalvar) {
			btnSalvar_click();
		}
	}
	
	private void btnNovo_click() {
		FuncoesGlobais.limpaCampos(pnlInformacoesAluno);
		FuncoesGlobais.limpaCampos(pnlInformacoesPessoaisDoAluno);
		FuncoesGlobais.desativaCampos(pnlBotoes);
		FuncoesGlobais.ativaCampos(pnlInformacoesAluno);
		FuncoesGlobais.ativaCampos(pnlInformacoesPessoaisDoAluno);
		
		btnSalvar.setEnabled(true);
		btnCancelar.setEnabled(true);
		txtRegistro.setText("NOVO");
		txtRegistro.setEnabled(false);
		txtCPF.requestFocus();
	}
	
	private void btnCancelar_click() {
		if(JOptionPane.showConfirmDialog(this, 
				"Deseja realmente cancelar o novo cadastrado?", 
				"Sistema", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
			
		
			FuncoesGlobais.limpaCampos(pnlInformacoesAluno);
			FuncoesGlobais.limpaCampos(pnlInformacoesPessoaisDoAluno);
			FuncoesGlobais.desativaCampos(pnlInformacoesAluno);
			FuncoesGlobais.desativaCampos(pnlInformacoesPessoaisDoAluno);
			FuncoesGlobais.desativaCampos(pnlBotoes);
			FuncoesGlobais.resetaBordaPadrao(pnlInformacoesAluno);
			FuncoesGlobais.resetaBordaPadrao(pnlInformacoesPessoaisDoAluno);
			btnNovo.setEnabled(true);
			btnPesquisar.setEnabled(true);
			
			JOptionPane.showMessageDialog(this, "Cancelado com sucesso!", "Sistema", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void btnSalvar_click() {
		if (FuncoesGlobais.verificaCampos(pnlInformacoesAluno) == true | FuncoesGlobais.verificaCampos(pnlInformacoesPessoaisDoAluno)){
			
			JOptionPane.showMessageDialog(this, "Erro - Os campos em vermelho devem ser preenchidos!", "Sistema", JOptionPane.ERROR_MESSAGE);
		
		}else {
			
			if(JOptionPane.showConfirmDialog(this, 
											"Deseja realmente salvar o novo cadastrado?", 
											"Sistema", 
											JOptionPane.YES_NO_OPTION, 
											JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
				
				FuncoesGlobais.limpaCampos(pnlInformacoesAluno);
				FuncoesGlobais.limpaCampos(pnlInformacoesPessoaisDoAluno);
				FuncoesGlobais.desativaCampos(pnlBotoes);
				
				btnNovo.setEnabled(true);
				btnPesquisar.setEnabled(true);
				
				JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sistema", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}

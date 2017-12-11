package view;

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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.text.MaskFormatter;

import controler.FuncoesGlobais;
import dao.DAOAluno;
import dao.DAOFuncionario;
import dao.DAOResponsavel;
import model.Aluno;
import model.Responsavel;

public class frmCadastroAluno extends JInternalFrame implements ActionListener, InternalFrameListener {
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
	private JComboBox cbRaca;
	private JLabel lblEndereco;
	private JTextField txtNumeroCasa;
	private JLabel lblDataDeNascimento;
	private JLabel lblTelefoneResidencial;
	private JLabel lblCelular;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblLocalDeOrigem;
	private JLabel lblRedeEstabelecimentoDe;
	private JLabel lblSituacaoDoAlunoAnoAnterior;
	private JLabel lblObservacoesDoAluno;
	private JTextField txtLocalOrigem;
	private JTextField txtRedeEstabelecimentoDeOrdemDoAluno;
	private JTextField txtSituacaoAnoAnterior;
	private JEditorPane edpObservacoesAluno;
	private JLabel lblLogradouro;
	private JComboBox cbLogradouro;
	private JLabel lblNumero;
	private JTextField txtEndereco;
	private JFormattedTextField txtTelResidencial;
	private JFormattedTextField txtCelular;
	private JFormattedTextField txtDataDeNascimento;
	private JFormattedTextField txtCPF;

	private static frmCadastroAluno singleton = null;
	private JPanel pnlInformacoesPessoaisDoAluno;
	private JPanel pnlInformacoesAluno;
	private JPanel pnlBotoes;
	private JComboBox cbPai;
	private JLabel lblMae;
	private JButton btnPesquisaPai;
	private JComboBox cbMae;
	private JButton btnPesquisaMae;
	private int idPai, idMae;
	private JTextField txtBairro;
	private JLabel lblCidade;
	private JTextField txtCidade;
	private JComboBox cbEstado;
	private JLabel label;

	public static frmCadastroAluno getInstance() throws ParseException, SQLException {
		if (singleton == null) {
			singleton = new frmCadastroAluno();
		}

		return singleton;
	}

	public frmCadastroAluno() throws ParseException, SQLException {
		// Hack para remover icone do nimbus
		Container pane = ((BasicInternalFrameUI) this.getUI()).getNorthPane();
		// pane.remove(0);
		pane.getComponent(0).setVisible(false);

		addInternalFrameListener(this);
		setClosable(true);
		setTitle("Cadastro de aluno");
		setBounds(100, 100, 887, 657);
		getContentPane().setLayout(null);

		pnlBotoes = new JPanel();
		pnlBotoes.setBounds(10, 11, 555, 99);
		getContentPane().add(pnlBotoes);
		pnlBotoes.setLayout(null);

		btnNovo = new JButton("Novo");
		btnNovo.setIcon(new ImageIcon(frmCadastroAluno.class.getResource("/imagens/novo 48x48.png")));
		btnNovo.addActionListener(this);
		btnNovo.setBounds(10, 11, 80, 79);
		btnNovo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNovo.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlBotoes.add(btnNovo);

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(this);
		btnExcluir.setIcon(new ImageIcon(frmCadastroAluno.class.getResource("/imagens/excluir 48x48.png")));
		btnExcluir.setBounds(100, 11, 80, 79);
		btnExcluir.setHorizontalTextPosition(SwingConstants.CENTER);
		btnExcluir.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnExcluir.setEnabled(false);
		pnlBotoes.add(btnExcluir);

		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(this);
		btnAlterar.setIcon(new ImageIcon(frmCadastroAluno.class.getResource("/imagens/editar 48x48.png")));
		btnAlterar.setBounds(190, 11, 80, 79);
		btnAlterar.setEnabled(false);
		btnAlterar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAlterar.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlBotoes.add(btnAlterar);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(frmCadastroAluno.class.getResource("/imagens/salvar 48x48.png")));
		btnSalvar.addActionListener(this);
		btnSalvar.setBounds(280, 11, 80, 79);
		btnSalvar.setEnabled(false);
		btnSalvar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalvar.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlBotoes.add(btnSalvar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(frmCadastroAluno.class.getResource("/imagens/cancelar 48x48.png")));
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(370, 11, 80, 79);
		btnCancelar.setEnabled(false);
		btnCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlBotoes.add(btnCancelar);

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setIcon(new ImageIcon(frmCadastroAluno.class.getResource("/imagens/lupa 48x48.png")));
		btnPesquisar.addActionListener(this);
		btnPesquisar.setBounds(460, 11, 85, 79);
		btnPesquisar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPesquisar.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlBotoes.add(btnPesquisar);

		pnlInformacoesAluno = new JPanel();
		pnlInformacoesAluno.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es do aluno", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnlInformacoesAluno.setBounds(10, 121, 851, 260);
		getContentPane().add(pnlInformacoesAluno);
		pnlInformacoesAluno.setLayout(null);

		lblRegistro = new JLabel("Registro");
		lblRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegistro.setBounds(10, 17, 80, 28);
		pnlInformacoesAluno.add(lblRegistro);

		txtNome = new JTextField();
		txtNome.setEnabled(false);
		txtNome.setBounds(100, 97, 296, 28);
		pnlInformacoesAluno.add(txtNome);
		txtNome.setColumns(10);

		lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 97, 80, 28);
		pnlInformacoesAluno.add(lblNome);

		txtRegistro = new JTextField();
		txtRegistro.setEnabled(false);
		txtRegistro.setColumns(10);
		txtRegistro.setBounds(100, 19, 171, 28);
		pnlInformacoesAluno.add(txtRegistro);

		lblRg = new JLabel("RG");
		lblRg.setHorizontalAlignment(SwingConstants.LEFT);
		lblRg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRg.setBounds(406, 97, 36, 28);
		pnlInformacoesAluno.add(lblRg);

		txtRG = new JTextField();
		txtRG.setEnabled(false);
		txtRG.setColumns(10);
		txtRG.setBounds(452, 97, 124, 28);
		pnlInformacoesAluno.add(txtRG);

		lblOrgaoEmissor = new JLabel("\u00D3rg\u00E3o  Emissor");
		lblOrgaoEmissor.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrgaoEmissor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOrgaoEmissor.setBounds(586, 97, 101, 28);
		pnlInformacoesAluno.add(lblOrgaoEmissor);

		txtOrgaoEmissor = new JTextField();
		txtOrgaoEmissor.setEnabled(false);
		txtOrgaoEmissor.setColumns(10);
		txtOrgaoEmissor.setBounds(697, 97, 137, 28);
		pnlInformacoesAluno.add(txtOrgaoEmissor);

		lblCpf = new JLabel("CPF");
		lblCpf.setHorizontalAlignment(SwingConstants.CENTER);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpf.setBounds(640, 17, 47, 28);
		pnlInformacoesAluno.add(lblCpf);

		cbSexo = new JComboBox();
		cbSexo.setEnabled(false);
		cbSexo.setModel(new DefaultComboBoxModel(new String[] { "Masculino", "Feminino" }));
		cbSexo.setSelectedItem(null);
		cbSexo.setBounds(288, 216, 80, 28);
		pnlInformacoesAluno.add(cbSexo);

		lblSexo = new JLabel("Sexo");
		lblSexo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSexo.setBounds(218, 214, 53, 28);
		pnlInformacoesAluno.add(lblSexo);

		lblCor = new JLabel("Cor");
		lblCor.setHorizontalAlignment(SwingConstants.CENTER);
		lblCor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCor.setBounds(716, 175, 36, 28);
		pnlInformacoesAluno.add(lblCor);

		cbCor = new JComboBox();
		cbCor.setEnabled(false);
		cbCor.setModel(new DefaultComboBoxModel(new String[] { "Branco", "Moreno", "Negro", "Pardo" }));
		cbCor.setBounds(762, 175, 72, 28);
		cbCor.setSelectedItem(null);
		pnlInformacoesAluno.add(cbCor);

		lblRaca = new JLabel("Ra\u00E7a");
		lblRaca.setHorizontalAlignment(SwingConstants.LEFT);
		lblRaca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRaca.setBounds(10, 214, 80, 28);
		pnlInformacoesAluno.add(lblRaca);

		cbRaca = new JComboBox();
		cbRaca.setModel(new DefaultComboBoxModel(new String[] { "Ind\u00EDgina", "Afrodescendente" }));
		cbRaca.setEnabled(false);
		cbRaca.setBounds(100, 214, 100, 28);
		cbRaca.setSelectedItem(null);
		pnlInformacoesAluno.add(cbRaca);

		lblEndereco = new JLabel("Endere\u00E7o");
		lblEndereco.setHorizontalAlignment(SwingConstants.LEFT);
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEndereco.setBounds(178, 136, 69, 28);
		pnlInformacoesAluno.add(lblEndereco);

		txtNumeroCasa = new JTextField();
		txtNumeroCasa.setEnabled(false);
		txtNumeroCasa.setHorizontalAlignment(SwingConstants.RIGHT);
		txtNumeroCasa.setColumns(10);
		txtNumeroCasa.setBounds(558, 136, 47, 28);
		pnlInformacoesAluno.add(txtNumeroCasa);

		lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDeNascimento.setBounds(288, 19, 129, 28);
		pnlInformacoesAluno.add(lblDataDeNascimento);

		lblTelefoneResidencial = new JLabel("Telefone Residencial");
		lblTelefoneResidencial.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefoneResidencial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefoneResidencial.setBounds(379, 212, 129, 28);
		pnlInformacoesAluno.add(lblTelefoneResidencial);

		lblCelular = new JLabel("Celular");
		lblCelular.setHorizontalAlignment(SwingConstants.CENTER);
		lblCelular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCelular.setBounds(635, 217, 63, 28);
		pnlInformacoesAluno.add(lblCelular);

		lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(370, 175, 47, 28);
		pnlInformacoesAluno.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setEnabled(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(427, 177, 279, 28);
		pnlInformacoesAluno.add(txtEmail);

		lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setHorizontalAlignment(SwingConstants.LEFT);
		lblLogradouro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLogradouro.setBounds(10, 136, 80, 28);
		pnlInformacoesAluno.add(lblLogradouro);

		cbLogradouro = new JComboBox();
		cbLogradouro.setEnabled(false);
		cbLogradouro.setModel(new DefaultComboBoxModel(new String[] { "Avenida", "Rua", "Pra\u00E7a", "Zona Rural" }));
		cbLogradouro.setBounds(100, 136, 69, 28);
		cbLogradouro.setSelectedItem(null);
		pnlInformacoesAluno.add(cbLogradouro);

		lblNumero = new JLabel(",");
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumero.setBounds(534, 136, 14, 28);
		pnlInformacoesAluno.add(lblNumero);

		txtEndereco = new JTextField();
		txtEndereco.setEnabled(false);
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(257, 136, 271, 28);
		pnlInformacoesAluno.add(txtEndereco);

		txtTelResidencial = new JFormattedTextField(new MaskFormatter("(##)####-####"));
		txtTelResidencial.setEnabled(false);
		txtTelResidencial.setBounds(518, 213, 117, 30);
		pnlInformacoesAluno.add(txtTelResidencial);

		txtCelular = new JFormattedTextField(new MaskFormatter("(##)####-####"));
		txtCelular.setEnabled(false);
		txtCelular.setBounds(707, 215, 127, 30);
		pnlInformacoesAluno.add(txtCelular);

		txtDataDeNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataDeNascimento.setEnabled(false);
		txtDataDeNascimento.setBounds(427, 19, 143, 30);
		pnlInformacoesAluno.add(txtDataDeNascimento);

		txtCPF = new JFormattedTextField(new MaskFormatter("###.###.###.##"));
		txtCPF.setEnabled(false);
		txtCPF.setBounds(691, 18, 143, 30);
		pnlInformacoesAluno.add(txtCPF);

		JLabel lblPai = new JLabel("Pai");
		lblPai.setHorizontalAlignment(SwingConstants.LEFT);
		lblPai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPai.setBounds(10, 58, 80, 28);
		pnlInformacoesAluno.add(lblPai);

		cbPai = new JComboBox();
		cbPai.setEnabled(false);
		cbPai.setBounds(100, 58, 258, 28);

		DefaultComboBoxModel<String> cbPaiModel = new DefaultComboBoxModel<>();

		for (int i = 0; i < new DAOResponsavel().listaResponsavel().size(); i++) {
			cbPaiModel.addElement(new DAOResponsavel().listaResponsavel().get(i).getNome_do_Responsavel());
		}

		cbPai.setModel(cbPaiModel);
		cbPai.setSelectedItem(null);
		pnlInformacoesAluno.add(cbPai);

		btnPesquisaPai = new JButton("");
		btnPesquisaPai.addActionListener(this);
		btnPesquisaPai.setIcon(new ImageIcon(frmCadastroAluno.class.getResource("/imagens/pesquisar.png")));
		btnPesquisaPai.setEnabled(false);
		btnPesquisaPai.setBounds(368, 58, 28, 28);
		pnlInformacoesAluno.add(btnPesquisaPai);

		btnPesquisaMae = new JButton("");
		btnPesquisaMae.addActionListener(this);
		btnPesquisaMae.setIcon(new ImageIcon(frmCadastroAluno.class.getResource("/imagens/pesquisar.png")));
		btnPesquisaMae.setEnabled(false);
		btnPesquisaMae.setBounds(806, 58, 28, 28);
		pnlInformacoesAluno.add(btnPesquisaMae);

		cbMae = new JComboBox();
		cbMae.setEnabled(false);
		cbMae.setBounds(538, 58, 258, 28);

		DefaultComboBoxModel<String> cbMaeModel = new DefaultComboBoxModel<>();
		for (int i = 0; i < new DAOResponsavel().listaResponsavel().size(); i++) {
			cbMaeModel.addElement(new DAOResponsavel().listaResponsavel().get(i).getNome_do_Responsavel());
		}

		cbMae.setModel(cbMaeModel);
		cbMae.setSelectedItem(null);
		pnlInformacoesAluno.add(cbMae);

		lblMae = new JLabel("M\u00E3e");
		lblMae.setHorizontalAlignment(SwingConstants.LEFT);
		lblMae.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMae.setBounds(492, 56, 36, 28);
		pnlInformacoesAluno.add(lblMae);

		txtBairro = new JTextField();
		txtBairro.setEnabled(false);
		txtBairro.setColumns(10);
		txtBairro.setBounds(672, 136, 162, 28);
		pnlInformacoesAluno.add(txtBairro);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setHorizontalAlignment(SwingConstants.LEFT);
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBairro.setBounds(615, 136, 47, 28);
		pnlInformacoesAluno.add(lblBairro);

		lblCidade = new JLabel("Cidade");
		lblCidade.setHorizontalAlignment(SwingConstants.LEFT);
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCidade.setBounds(10, 175, 80, 28);
		pnlInformacoesAluno.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setEnabled(false);
		txtCidade.setColumns(10);
		txtCidade.setBounds(100, 177, 100, 28);
		pnlInformacoesAluno.add(txtCidade);

		cbEstado = new JComboBox();
		cbEstado.setModel(new DefaultComboBoxModel(
				new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
						"PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" }));
		cbEstado.setEnabled(false);
		cbEstado.setBounds(288, 177, 70, 28);
		cbEstado.setSelectedItem(null);
		pnlInformacoesAluno.add(cbEstado);

		label = new JLabel("Estado");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(218, 177, 63, 28);
		pnlInformacoesAluno.add(label);

		pnlInformacoesPessoaisDoAluno = new JPanel();
		pnlInformacoesPessoaisDoAluno.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es Pessoais do Aluno",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlInformacoesPessoaisDoAluno.setBounds(10, 392, 851, 224);
		getContentPane().add(pnlInformacoesPessoaisDoAluno);
		pnlInformacoesPessoaisDoAluno.setLayout(null);

		lblLocalDeOrigem = new JLabel("Local de Origem do Aluno");
		lblLocalDeOrigem.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocalDeOrigem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLocalDeOrigem.setBounds(10, 11, 198, 28);
		pnlInformacoesPessoaisDoAluno.add(lblLocalDeOrigem);

		lblRedeEstabelecimentoDe = new JLabel("Rede Estabelecimento de Ordem do Aluno");
		lblRedeEstabelecimentoDe.setHorizontalAlignment(SwingConstants.LEFT);
		lblRedeEstabelecimentoDe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRedeEstabelecimentoDe.setBounds(10, 50, 283, 28);
		pnlInformacoesPessoaisDoAluno.add(lblRedeEstabelecimentoDe);

		lblSituacaoDoAlunoAnoAnterior = new JLabel("Situa\u00E7\u00E3o do Aluno no Ano Anterior");
		lblSituacaoDoAlunoAnoAnterior.setHorizontalAlignment(SwingConstants.LEFT);
		lblSituacaoDoAlunoAnoAnterior.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSituacaoDoAlunoAnoAnterior.setBounds(10, 89, 221, 28);
		pnlInformacoesPessoaisDoAluno.add(lblSituacaoDoAlunoAnoAnterior);

		lblObservacoesDoAluno = new JLabel("Observa\u00E7\u00F5es do Aluno");
		lblObservacoesDoAluno.setHorizontalAlignment(SwingConstants.LEFT);
		lblObservacoesDoAluno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblObservacoesDoAluno.setBounds(10, 128, 210, 28);
		pnlInformacoesPessoaisDoAluno.add(lblObservacoesDoAluno);

		txtLocalOrigem = new JTextField();
		txtLocalOrigem.setEnabled(false);
		txtLocalOrigem.setColumns(10);
		txtLocalOrigem.setBounds(303, 11, 538, 28);
		pnlInformacoesPessoaisDoAluno.add(txtLocalOrigem);

		txtRedeEstabelecimentoDeOrdemDoAluno = new JTextField();
		txtRedeEstabelecimentoDeOrdemDoAluno.setEnabled(false);
		txtRedeEstabelecimentoDeOrdemDoAluno.setColumns(10);
		txtRedeEstabelecimentoDeOrdemDoAluno.setBounds(303, 50, 538, 28);
		pnlInformacoesPessoaisDoAluno.add(txtRedeEstabelecimentoDeOrdemDoAluno);

		txtSituacaoAnoAnterior = new JTextField();
		txtSituacaoAnoAnterior.setEnabled(false);
		txtSituacaoAnoAnterior.setColumns(10);
		txtSituacaoAnoAnterior.setBounds(303, 89, 538, 28);
		pnlInformacoesPessoaisDoAluno.add(txtSituacaoAnoAnterior);

		edpObservacoesAluno = new JEditorPane();
		edpObservacoesAluno.setEnabled(false);
		edpObservacoesAluno.setBounds(303, 128, 538, 76);
		pnlInformacoesPessoaisDoAluno.add(edpObservacoesAluno);

		this.idPai = 0;
		this.idMae = 0;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnNovo) {
				btnNovo_click();
			} else if (e.getSource() == btnExcluir) {
				btnExcluir_click();
			} else if (e.getSource() == btnCancelar) {
				btnCancelar_click();
			} else if (e.getSource() == btnSalvar) {
				btnSalvar_click();
			} else if (e.getSource() == btnPesquisar) {
				btnPesquisar_click();
			} else if (e.getSource() == btnPesquisaPai) {
				btnPesquisaPai_click();
			} else if (e.getSource() == btnPesquisaMae) {
				btnPesquisaMae_click();
			} else if (e.getSource() == btnAlterar) {
				btnAlterar_click();
			}
		} catch (ParseException | SQLException | PropertyVetoException ex) {
			ex.printStackTrace();
		}
	}

	private void btnAlterar_click() {
		FuncoesGlobais.ativaCampos(pnlInformacoesAluno);
		FuncoesGlobais.ativaCampos(pnlInformacoesPessoaisDoAluno);
		FuncoesGlobais.desativaCampos(pnlBotoes);

		btnSalvar.setEnabled(true);
		btnCancelar.setEnabled(true);
		txtRegistro.setEnabled(false);
		cbPai.setEnabled(false);
		cbMae.setEnabled(false);
		txtDataDeNascimento.requestFocusInWindow();
	}

	private void btnPesquisaPai_click() throws ParseException, SQLException, PropertyVetoException {
		DefaultComboBoxModel<String> cbPaiModel = new DefaultComboBoxModel<>();

		for (int i = 0; i < new DAOResponsavel().listaResponsavel().size(); i++) {
			cbPaiModel.addElement(new DAOResponsavel().listaResponsavel().get(i).getNome_do_Responsavel());
		}

		cbPai.setModel(cbPaiModel);
		cbPai.setSelectedItem(null);
		idPai = 0;

		if (frmPesquisaPais.getInstance().isVisible()) {
			frmPesquisaPais.getInstance().setTitle("Pesquisar por Pai");
			frmPesquisaPais.getInstance().atualizaDados(); // Atualiza os dados do formulario
			frmPesquisaPais.getInstance().getBtnConfirma().setVisible(true);
			frmPesquisaPais.getInstance().getBtnConfirma().setText("Abrir cadastro de aluno");
			frmPesquisaPais.getInstance().setSelected(true);
		} else {
			frmPesquisaPais.getInstance().setTitle("Pesquisar por Pai");
			frmPesquisaPais.getInstance().atualizaDados(); // Atualiza os dados do formulario
			frmMenu.getFrmMenu().getDskPrincipal().add(frmPesquisaPais.getInstance());
			frmPesquisaPais.getInstance().getBtnConfirma().setVisible(true);
			frmPesquisaPais.getInstance().getBtnConfirma().setText("Abrir cadastro de aluno");
			frmPesquisaPais.getInstance().setVisible(true);
			frmPesquisaPais.getInstance().setSelected(true);
		}
	}

	private void btnPesquisaMae_click() throws ParseException, SQLException, PropertyVetoException {
		DefaultComboBoxModel<String> cbMaeModel = new DefaultComboBoxModel<>();

		for (int i = 0; i < new DAOResponsavel().listaResponsavel().size(); i++) {
			cbMaeModel.addElement(new DAOResponsavel().listaResponsavel().get(i).getNome_do_Responsavel());
		}

		cbMae.setModel(cbMaeModel);
		cbMae.setSelectedItem(null);
		idMae = 0;

		if (frmPesquisaPais.getInstance().isVisible()) {
			frmPesquisaPais.getInstance().setTitle("Pesquisar por Mãe");
			frmPesquisaPais.getInstance().atualizaDados(); // Atualiza os dados do formulario
			frmPesquisaPais.getInstance().getBtnConfirma().setVisible(true);
			frmPesquisaPais.getInstance().getBtnConfirma().setText("Abrir cadastro de aluno");
			frmPesquisaPais.getInstance().setSelected(true);
		} else {
			frmPesquisaPais.getInstance().setTitle("Pesquisar por Mãe");
			frmPesquisaPais.getInstance().atualizaDados(); // Atualiza os dados do formulario
			frmMenu.getFrmMenu().getDskPrincipal().add(frmPesquisaPais.getInstance());
			frmPesquisaPais.getInstance().getBtnConfirma().setVisible(true);
			frmPesquisaPais.getInstance().getBtnConfirma().setText("Abrir cadastro de aluno");
			frmPesquisaPais.getInstance().setVisible(true);
			frmPesquisaPais.getInstance().setSelected(true);
		}
	}

	public void preenchePai(Responsavel responsavel) {
		this.idPai = responsavel.getRegistro();
		cbPai.setSelectedItem(responsavel.getNome_do_Responsavel());
	}

	public void preencheMae(Responsavel responsavel) {
		this.idMae = responsavel.getRegistro();
		cbMae.setSelectedItem(responsavel.getNome_do_Responsavel());
	}

	private void btnExcluir_click() throws NumberFormatException, SQLException {
		if (JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o cadastro?", "Sistema",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			DAOAluno daoAluno = new DAOAluno();

			daoAluno.excluirAluno(Integer.parseInt(txtRegistro.getText()));
			JOptionPane.showMessageDialog(this, "Registro excluido com sucesso!", "Sistema",
					JOptionPane.INFORMATION_MESSAGE);

			FuncoesGlobais.limpaCampos(pnlInformacoesAluno);
			FuncoesGlobais.limpaCampos(pnlInformacoesPessoaisDoAluno);
			FuncoesGlobais.desativaCampos(pnlInformacoesAluno);
			FuncoesGlobais.desativaCampos(pnlInformacoesPessoaisDoAluno);
			FuncoesGlobais.desativaCampos(pnlBotoes);

			btnNovo.setEnabled(true);
			btnPesquisar.setEnabled(true);
			btnNovo.requestFocus();
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

		cbPai.setEnabled(false);
		cbMae.setEnabled(false);

		idPai = 0;
		idMae = 0;
	}

	private void btnCancelar_click() {
		if (JOptionPane.showConfirmDialog(this, "Deseja realmente cancelar o novo cadastrado?", "Sistema",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {

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

	private void btnSalvar_click() throws SQLException {
		ArrayList<Component> listaCompInfoAluno = new ArrayList<>(), listaCompInfoPessoais = new ArrayList<>();

		listaCompInfoAluno.add(txtTelResidencial);
		listaCompInfoAluno.add(txtEmail);
		listaCompInfoAluno.add(txtNumeroCasa);

		listaCompInfoPessoais.add(txtLocalOrigem);
		listaCompInfoPessoais.add(txtRedeEstabelecimentoDeOrdemDoAluno);
		listaCompInfoPessoais.add(txtSituacaoAnoAnterior);
		listaCompInfoPessoais.add(edpObservacoesAluno);

		if (FuncoesGlobais.verificaCampos(pnlInformacoesAluno, listaCompInfoAluno) == true
				| FuncoesGlobais.verificaCampos(pnlInformacoesPessoaisDoAluno, listaCompInfoPessoais)) {

			JOptionPane.showMessageDialog(this, "Erro - Os campos em vermelho devem ser preenchidos!", "Sistema",
					JOptionPane.ERROR_MESSAGE);

		} else {

			if (JOptionPane.showConfirmDialog(this, "Deseja realmente salvar cadastrado?", "Sistema",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
				try {

					Aluno aluno = new Aluno();

					aluno.setData_de_Nascimento(
							new SimpleDateFormat("dd/MM/yyyy").parse(txtDataDeNascimento.getText()));
					aluno.setCPF(txtCPF.getText().replace(".", ""));
					aluno.setIdPai(idPai);
					aluno.setIdMae(idMae);
					aluno.setNome(txtNome.getText());
					aluno.setRG(txtRG.getText());
					aluno.setOrgao_Emissor(txtOrgaoEmissor.getText());
					aluno.setLogradouro(cbLogradouro.getSelectedItem().toString());
					aluno.setEnderco(txtEndereco.getText());
					aluno.setNumero_da_Casa(
							txtNumeroCasa.getText().isEmpty() ? 0 : Integer.parseInt(txtNumeroCasa.getText()));
					aluno.setCor(cbCor.getSelectedItem().toString());
					aluno.setEmail(txtEmail.getText().isEmpty() ? null : txtEmail.getText());
					aluno.setRaca(cbRaca.getSelectedItem().toString());
					aluno.setSexo(cbSexo.getSelectedItem().toString());
					aluno.setTel_Residencial(txtTelResidencial.getValue() == null ? null
							: txtTelResidencial.getText().replace("(", "").replace(")", "").replace("-", ""));
					aluno.setCelular(txtCelular.getText().replace("(", "").replace(")", "").replace("-", ""));
					aluno.setLocal_de_Origem_do_Aluno(
							txtLocalOrigem.getText().isEmpty() ? null : txtLocalOrigem.getText());
					aluno.setRede_Estabelecimento_de_Ordem_do_aluno(
							txtRedeEstabelecimentoDeOrdemDoAluno.getText().isEmpty() ? null
									: txtRedeEstabelecimentoDeOrdemDoAluno.getText());
					aluno.setSituacao_do_Aluno_no_Ano_Anterior(
							txtSituacaoAnoAnterior.getText().isEmpty() ? null : txtSituacaoAnoAnterior.getText());
					aluno.setObservacoes_do_Aluno(
							edpObservacoesAluno.getText().isEmpty() ? null : edpObservacoesAluno.getText());
					aluno.setBairro(txtBairro.getText());
					aluno.setLocal_de_Origem_do_Aluno(
							txtLocalOrigem.getText().isEmpty() ? null : txtLocalOrigem.getText());
					aluno.setCidade(txtCidade.getText());
					aluno.setEstado(cbEstado.getSelectedItem().toString());

					DAOAluno daoAluno = new DAOAluno();

					if (txtRegistro.getText().equals("NOVO")) {
						daoAluno.novoAluno(aluno);
					}else {
						aluno.setRegistro(Integer.parseInt(txtRegistro.getText()));
						daoAluno.atualizaAluno(aluno);
					}

					FuncoesGlobais.limpaCampos(pnlInformacoesAluno);
					FuncoesGlobais.limpaCampos(pnlInformacoesPessoaisDoAluno);
					FuncoesGlobais.desativaCampos(pnlInformacoesAluno);
					FuncoesGlobais.desativaCampos(pnlInformacoesPessoaisDoAluno);
					FuncoesGlobais.desativaCampos(pnlBotoes);

					btnNovo.setEnabled(true);
					btnPesquisar.setEnabled(true);

					idPai = 0;
					idMae = 0;

					if (txtRegistro.getText().equals("NOVO")) {
						JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sistema",
								JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(this, "Cadastro atualizado com sucesso!", "Sistema",
								JOptionPane.INFORMATION_MESSAGE);
					}

				} catch (ParseException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private void btnPesquisar_click() throws ParseException, SQLException, PropertyVetoException {
		if (frmPesquisaAluno.getInstance().isVisible()) {
			frmPesquisaAluno.getInstance().atualizaDados(); // Atualiza os dados do formulario
			frmPesquisaAluno.getInstance().getBtnConfirma().setVisible(true);
			frmPesquisaAluno.getInstance().getBtnConfirma().setText("Abrir cadastro de aluno");
			frmPesquisaAluno.getInstance().setSelected(true);
		} else {
			frmPesquisaAluno.getInstance().atualizaDados(); // Atualiza os dados do formulario
			frmMenu.getFrmMenu().getDskPrincipal().add(frmPesquisaAluno.getInstance());
			frmPesquisaAluno.getInstance().getBtnConfirma().setVisible(true);
			frmPesquisaAluno.getInstance().getBtnConfirma().setText("Abrir cadastro de aluno");
			frmPesquisaAluno.getInstance().setVisible(true);
			frmPesquisaAluno.getInstance().setSelected(true);
		}
	}

	public void preencheCadastro(Aluno aluno) throws ParseException {
		FuncoesGlobais.desativaCampos(pnlBotoes);
		FuncoesGlobais.limpaCampos(pnlInformacoesAluno);
		FuncoesGlobais.limpaCampos(pnlInformacoesPessoaisDoAluno);
		FuncoesGlobais.resetaBordaPadrao(pnlInformacoesAluno);
		FuncoesGlobais.resetaBordaPadrao(pnlInformacoesPessoaisDoAluno);
		FuncoesGlobais.desativaCampos(pnlInformacoesAluno);
		FuncoesGlobais.desativaCampos(pnlInformacoesPessoaisDoAluno);

		btnNovo.setEnabled(true);
		btnAlterar.setEnabled(true);
		btnExcluir.setEnabled(true);
		btnPesquisar.setEnabled(true);

		txtRegistro.setText(Integer.toString(aluno.getRegistro()));
		txtDataDeNascimento.setValue(new SimpleDateFormat("dd/MM/yyyy").format(aluno.getData_de_Nascimento()));

		MaskFormatter mask = new MaskFormatter("###.###.###.##");
		mask.setValueContainsLiteralCharacters(false);

		txtCPF.setValue(mask.valueToString(aluno.getCPF()));
		cbPai.setSelectedItem(aluno.getNome_Pai());
		cbMae.setSelectedItem(aluno.getNome_Mae());
		txtNome.setText(aluno.getNome());
		txtRG.setText(aluno.getRG());
		txtOrgaoEmissor.setText(aluno.getOrgao_Emissor());
		cbLogradouro.setSelectedItem(aluno.getLogradouro());
		txtEndereco.setText(aluno.getEnderco());
		txtNumeroCasa.setText(aluno.getNumero_da_Casa() == 0 ? null : Integer.toString(aluno.getNumero_da_Casa()));
		txtBairro.setText(aluno.getBairro());
		txtCidade.setText(aluno.getCidade());
		cbEstado.setSelectedItem(aluno.getEstado());
		txtEmail.setText(aluno.getEmail());
		cbCor.setSelectedItem(aluno.getCor());
		cbRaca.setSelectedItem(aluno.getRaca());
		cbSexo.setSelectedItem(aluno.getSexo());
		mask.setMask("(##)####-####");
		txtTelResidencial.setValue(mask.valueToString(aluno.getTel_Residencial()));
		mask.setMask("(##)####-####");
		txtCelular.setValue(mask.valueToString(aluno.getCelular()));
		txtLocalOrigem.setText(aluno.getLocal_de_Origem_do_Aluno());
		txtRedeEstabelecimentoDeOrdemDoAluno.setText(aluno.getRede_Estabelecimento_de_Ordem_do_aluno());
		txtSituacaoAnoAnterior.setText(aluno.getSituacao_do_Aluno_no_Ano_Anterior());
		edpObservacoesAluno.setText(aluno.getObservacoes_do_Aluno());
		
		idPai = aluno.getIdPai();
		idMae = aluno.getIdMae();
	}

	public void internalFrameActivated(InternalFrameEvent e) {
	}

	// Limpa memoria
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

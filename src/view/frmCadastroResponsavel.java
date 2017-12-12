package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.sql.Time;
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
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.text.MaskFormatter;

import controler.ComboKeyHandler;
import controler.FuncoesGlobais;
import dao.DAOFuncionario;
import dao.DAOResponsavel;
import model.Responsavel;

import javax.swing.ImageIcon;

public class frmCadastroResponsavel extends JInternalFrame implements ActionListener, InternalFrameListener {
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
	private JComboBox cbGrauInstrucao;
	private JLabel lblProfissao;
	private JTextField txtProfissao;
	private JLabel lblDataDeNascimento;
	private JFormattedTextField txtDataDeNascimento;
	private JPanel pnlLocalDeTrabalho;
	private JLabel lblNomeDoLocal;
	private JTextField txtNomeDoLocal;
	private JLabel label;
	private JComboBox cbLogradouro;
	private JLabel lblEndereco;
	private JTextField txtEndereco;
	private JLabel lblNumero;
	private JTextField txtNumero;
	private JPanel pnlPerfilSocioEconomico;
	private JLabel lblRenda;
	private JLabel lblCasaPropria;
	private JComboBox cbCasaPropria;
	private JLabel lblNumeroDeFilhos;
	private JLabel lblNumeroDePessoasQueResidemNaCasa;
	private JLabel lblHorario;
	private JFormattedTextField txtHorario;

	private static frmCadastroResponsavel singleton = null;
	private JPanel pnlBotoes;
	private JTextField txtRenda;
	private JTextField txtNumeroFilho;
	private JTextField txtNumeroPessoasCasa;

	public static frmCadastroResponsavel getInstance() throws ParseException {
		if (singleton == null) {
			singleton = new frmCadastroResponsavel();
		}

		return singleton;
	}
	
	public static void setInstance(frmCadastroResponsavel estado) {
		singleton = estado;
	}
	
	public frmCadastroResponsavel() throws ParseException {
		// Hack para remover icone do nimbus
		Container pane = ((BasicInternalFrameUI) this.getUI()).getNorthPane();
		// pane.remove(0);
		pane.getComponent(0).setVisible(false);

		addInternalFrameListener(this);
		setBounds(100, 100, 840, 558);
		setClosable(true);
		setTitle("Cadastro de Respons\u00E1vel");
		getContentPane().setLayout(null);

		pnlBotoes = new JPanel();
		pnlBotoes.setBounds(10, 11, 804, 94);
		pnlBotoes.setLayout(null);
		getContentPane().add(pnlBotoes);

		btnNovo = new JButton("Novo");
		btnNovo.setIcon(new ImageIcon(frmCadastroResponsavel.class.getResource("/imagens/novo 48x48.png")));
		btnNovo.addActionListener(this);
		btnNovo.setBounds(10, 11, 80, 79);
		btnNovo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNovo.setVerticalTextPosition(SwingConstants.BOTTOM);

		pnlBotoes.add(btnNovo);

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(this);
		btnExcluir.setIcon(new ImageIcon(frmCadastroResponsavel.class.getResource("/imagens/excluir 48x48.png")));
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(100, 11, 80, 79);
		btnExcluir.setHorizontalTextPosition(SwingConstants.CENTER);
		btnExcluir.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlBotoes.add(btnExcluir);

		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(this);
		btnAlterar.setIcon(new ImageIcon(frmCadastroResponsavel.class.getResource("/imagens/editar 48x48.png")));
		btnAlterar.setEnabled(false);
		btnAlterar.setBounds(190, 11, 80, 79);
		btnAlterar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAlterar.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlBotoes.add(btnAlterar);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(frmCadastroResponsavel.class.getResource("/imagens/salvar 48x48.png")));
		btnSalvar.addActionListener(this);
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(280, 11, 80, 79);
		btnSalvar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalvar.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlBotoes.add(btnSalvar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(frmCadastroResponsavel.class.getResource("/imagens/cancelar 48x48.png")));
		btnCancelar.addActionListener(this);
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(370, 11, 80, 79);
		btnCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlBotoes.add(btnCancelar);

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setIcon(new ImageIcon(frmCadastroResponsavel.class.getResource("/imagens/lupa 48x48.png")));
		btnPesquisar.addActionListener(this);
		btnPesquisar.setBounds(460, 11, 85, 79);
		btnPesquisar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPesquisar.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlBotoes.add(btnPesquisar);

		pnlCadastroResponsavel = new JPanel();
		pnlCadastroResponsavel.setBounds(10, 113, 804, 179);
		pnlCadastroResponsavel.setBorder(
				new TitledBorder(null, "Cadastro de Responsavel", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		txtCPF.setBounds(597, 17, 197, 28);
		pnlCadastroResponsavel.add(txtCPF);

		lblGrauDeInstrucao = new JLabel("Grau de Instru\u00E7\u00E3o");
		lblGrauDeInstrucao.setHorizontalAlignment(SwingConstants.LEFT);
		lblGrauDeInstrucao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGrauDeInstrucao.setBounds(458, 56, 129, 28);
		pnlCadastroResponsavel.add(lblGrauDeInstrucao);

		cbGrauInstrucao = new JComboBox();
		cbGrauInstrucao.setEnabled(false);
		cbGrauInstrucao.setModel(new DefaultComboBoxModel(new String[] { "N\u00E3o Alfabetizado",
				"Ensino Fundamental Incompleto", "Ensino Fundamental Completo", "Ensino M\u00E9dio Incompleto",
				"Ensino M\u00E9dio Completo", "Ensino Superior Incompleto", "Ensino Superior Completo" }));
		cbGrauInstrucao.setBounds(597, 56, 197, 28);
		cbGrauInstrucao.setSelectedItem(null);
		
		cbGrauInstrucao.setEditable(true);
		JTextField edtCbGrauInstrucao = (JTextField) cbGrauInstrucao.getEditor().getEditorComponent();
		edtCbGrauInstrucao.addKeyListener(new ComboKeyHandler(cbGrauInstrucao));
		
		pnlCadastroResponsavel.add(cbGrauInstrucao);

		lblProfissao = new JLabel("Profiss\u00E3o");
		lblProfissao.setHorizontalAlignment(SwingConstants.LEFT);
		lblProfissao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProfissao.setBounds(458, 95, 90, 28);
		pnlCadastroResponsavel.add(lblProfissao);

		txtProfissao = new JTextField();
		txtProfissao.setEnabled(false);
		txtProfissao.setColumns(10);
		txtProfissao.setBounds(597, 95, 197, 28);
		pnlCadastroResponsavel.add(txtProfissao);

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
		pnlLocalDeTrabalho.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Local de Trabalho",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlLocalDeTrabalho.setBounds(10, 303, 804, 100);
		getContentPane().add(pnlLocalDeTrabalho);
		pnlLocalDeTrabalho.setLayout(null);

		lblNomeDoLocal = new JLabel("Nome do Local");
		lblNomeDoLocal.setHorizontalAlignment(SwingConstants.LEFT);
		lblNomeDoLocal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeDoLocal.setBounds(10, 22, 99, 28);
		pnlLocalDeTrabalho.add(lblNomeDoLocal);

		txtNomeDoLocal = new JTextField();
		txtNomeDoLocal.setEnabled(false);
		txtNomeDoLocal.setColumns(10);
		txtNomeDoLocal.setBounds(112, 22, 238, 28);
		pnlLocalDeTrabalho.add(txtNomeDoLocal);

		label = new JLabel("Logradouro");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(10, 61, 80, 28);
		pnlLocalDeTrabalho.add(label);

		cbLogradouro = new JComboBox();
		cbLogradouro.setEnabled(false);
		cbLogradouro.setModel(new DefaultComboBoxModel(new String[] { "Avenida", "Rua", "Pra\u00E7a", "Zona Rural" }));
		cbLogradouro.setBounds(112, 61, 142, 28);
		cbLogradouro.setSelectedItem(null);
		
		cbLogradouro.setEditable(true);
		JTextField edtCbLogradouro = (JTextField) cbLogradouro.getEditor().getEditorComponent();
		edtCbLogradouro.addKeyListener(new ComboKeyHandler(cbLogradouro));
		
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
		txtNumero.setEnabled(false);
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
		txtHorario.setHorizontalAlignment(SwingConstants.CENTER);
		txtHorario.setEnabled(false);
		txtHorario.setBounds(433, 22, 47, 28);
		pnlLocalDeTrabalho.add(txtHorario);

		pnlPerfilSocioEconomico = new JPanel();
		pnlPerfilSocioEconomico.setBorder(new TitledBorder(null, "Perfil S\u00F3cio Economico", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnlPerfilSocioEconomico.setBounds(10, 414, 804, 106);
		getContentPane().add(pnlPerfilSocioEconomico);
		pnlPerfilSocioEconomico.setLayout(null);

		lblRenda = new JLabel("Renda");
		lblRenda.setHorizontalAlignment(SwingConstants.LEFT);
		lblRenda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRenda.setBounds(10, 21, 69, 28);
		pnlPerfilSocioEconomico.add(lblRenda);

		lblCasaPropria = new JLabel("Casa Propria");
		lblCasaPropria.setHorizontalAlignment(SwingConstants.LEFT);
		lblCasaPropria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCasaPropria.setBounds(188, 21, 95, 28);
		pnlPerfilSocioEconomico.add(lblCasaPropria);

		cbCasaPropria = new JComboBox();
		cbCasaPropria.setEnabled(false);
		cbCasaPropria.setModel(new DefaultComboBoxModel(new String[] { "Sim", "N\u00E3o" }));
		cbCasaPropria.setBounds(293, 21, 58, 28);
		cbCasaPropria.setSelectedItem(null);
		
		cbCasaPropria.setEditable(true);
		JTextField edtCbCasaPropria = (JTextField) cbCasaPropria.getEditor().getEditorComponent();
		edtCbCasaPropria.addKeyListener(new ComboKeyHandler(cbCasaPropria));
		
		pnlPerfilSocioEconomico.add(cbCasaPropria);

		lblNumeroDeFilhos = new JLabel("Numero de Filhos");
		lblNumeroDeFilhos.setHorizontalAlignment(SwingConstants.LEFT);
		lblNumeroDeFilhos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumeroDeFilhos.setBounds(361, 21, 130, 28);
		pnlPerfilSocioEconomico.add(lblNumeroDeFilhos);

		lblNumeroDePessoasQueResidemNaCasa = new JLabel("Numero de Pessoas que Residem na Casa");
		lblNumeroDePessoasQueResidemNaCasa.setHorizontalAlignment(SwingConstants.LEFT);
		lblNumeroDePessoasQueResidemNaCasa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumeroDePessoasQueResidemNaCasa.setBounds(10, 60, 283, 28);
		pnlPerfilSocioEconomico.add(lblNumeroDePessoasQueResidemNaCasa);

		txtRenda = new JTextField();
		txtRenda.setEnabled(false);
		txtRenda.setColumns(10);
		txtRenda.setBounds(59, 21, 119, 28);
		pnlPerfilSocioEconomico.add(txtRenda);

		txtNumeroFilho = new JTextField();
		txtNumeroFilho.setEnabled(false);
		txtNumeroFilho.setBounds(474, 21, 58, 28);
		pnlPerfilSocioEconomico.add(txtNumeroFilho);

		txtNumeroPessoasCasa = new JTextField();
		txtNumeroPessoasCasa.setEnabled(false);
		txtNumeroPessoasCasa.setBounds(293, 60, 58, 28);
		pnlPerfilSocioEconomico.add(txtNumeroPessoasCasa);

	}

	public void preencheCadastro(Responsavel responsavel) {
		FuncoesGlobais.desativaCampos(pnlBotoes);
		FuncoesGlobais.limpaCampos(pnlCadastroResponsavel);
		FuncoesGlobais.limpaCampos(pnlLocalDeTrabalho);
		FuncoesGlobais.limpaCampos(pnlPerfilSocioEconomico);
		FuncoesGlobais.resetaBordaPadrao(pnlCadastroResponsavel);
		FuncoesGlobais.resetaBordaPadrao(pnlLocalDeTrabalho);
		FuncoesGlobais.resetaBordaPadrao(pnlPerfilSocioEconomico);
		FuncoesGlobais.desativaCampos(pnlCadastroResponsavel);
		FuncoesGlobais.desativaCampos(pnlLocalDeTrabalho);
		FuncoesGlobais.desativaCampos(pnlPerfilSocioEconomico);

		btnNovo.setEnabled(true);
		btnAlterar.setEnabled(true);
		btnExcluir.setEnabled(true);
		btnPesquisar.setEnabled(true);

		txtRegistro.setText(Integer.toString(responsavel.getRegistro()));
		txtNome.setText(responsavel.getNome_do_Responsavel());
		cbGrauInstrucao.setSelectedItem(responsavel.getGrau_de_Intrucao());
		txtProfissao.setText(responsavel.getProfissao());
		txtNomeDoLocal.setText(responsavel.getLocal_de_Trabalho());
		txtHorario.setValue(responsavel.getHorario_de_Trabalho());
		cbLogradouro.setSelectedItem(responsavel.getLogradouro());
		txtEndereco.setText(responsavel.getEndereco());
		txtNumero.setText(responsavel.getNumeroCasa() == 0 ? null : Integer.toString(responsavel.getNumeroCasa()));
		txtDataDeNascimento.setValue(new SimpleDateFormat("dd/MM/yyyy").format(responsavel.getData_de_Nascimento()));
		txtRG.setText(responsavel.getRG());
		txtCPF.setText(responsavel.getCPF());
		txtRenda.setText(Double.toString(responsavel.getRenda()));
		cbCasaPropria.setSelectedItem(responsavel.getCasa_Propria() == true ? "Sim" : "Não");
		txtNumeroFilho.setText(Integer.toString(responsavel.getNumero_de_Filhos()));
		txtNumeroPessoasCasa.setText(Integer.toString(responsavel.getNumero_de_Pessoas_que_Residem_na_Casa()));
		// Time horarioTrabalho = new Time(new
		// SimpleDateFormat("HH:mm").parse(txtHorario.getText()).getTime());

	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnNovo) {
				btnNovo_click();
			} else if (e.getSource() == btnSalvar) {
				btnSalvar_click();
			} else if (e.getSource() == btnExcluir) {
				btnExcluir_click();
			} else if (e.getSource() == btnAlterar) {
				btnAlterar_click();
			} else if (e.getSource() == btnCancelar) {
				btnCancelar_click();
			} else if (e.getSource() == btnPesquisar) {
				btnPesquisar_click();
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro ao tentar concluir ação, tente novamente!", "Sistema",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void btnNovo_click() {
		FuncoesGlobais.limpaCampos(pnlCadastroResponsavel);
		FuncoesGlobais.limpaCampos(pnlLocalDeTrabalho);
		FuncoesGlobais.limpaCampos(pnlPerfilSocioEconomico);

		FuncoesGlobais.ativaCampos(pnlCadastroResponsavel);
		FuncoesGlobais.ativaCampos(pnlLocalDeTrabalho);
		FuncoesGlobais.ativaCampos(pnlPerfilSocioEconomico);

		FuncoesGlobais.desativaCampos(pnlBotoes);

		btnSalvar.setEnabled(true);
		btnCancelar.setEnabled(true);
		txtRegistro.setText("NOVO");
		txtRegistro.setEnabled(false);
		txtCPF.requestFocus();
	}

	private void btnSalvar_click() {

		ArrayList<Component> listaComp = new ArrayList<>();
		listaComp.add(txtNumero);

		if (FuncoesGlobais.verificaCampos(pnlCadastroResponsavel)
				| FuncoesGlobais.verificaCampos(pnlPerfilSocioEconomico)
				| FuncoesGlobais.verificaCampos(pnlLocalDeTrabalho, listaComp)) {
			JOptionPane.showMessageDialog(this, "Erro - Os campos em vermelho devem ser preenchidos!", "Sistema",
					JOptionPane.ERROR_MESSAGE);
		}

		else {
			if (JOptionPane.showConfirmDialog(this, "Deseja realmente salvar o cadastrado?", "Sistema",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {

				try {
					Responsavel responsavel = new Responsavel();

					responsavel.setCPF(txtCPF.getText().replace(".", ""));
					responsavel.setNome_do_Responsavel(txtNome.getText());
					responsavel.setGrau_de_Intrucao(cbGrauInstrucao.getSelectedItem().toString());
					responsavel.setRG(txtRG.getText());
					responsavel.setProfissao(txtProfissao.getText());
					responsavel.setData_de_Nascimento(
							new SimpleDateFormat("dd/MM/yyyy").parse(txtDataDeNascimento.getText()));
					responsavel.setLocal_de_Trabalho(txtNomeDoLocal.getText());
					Time horarioTrabalho = new Time(
							new SimpleDateFormat("HH:mm").parse(txtHorario.getText()).getTime());
					responsavel.setHorario_de_Trabalho(horarioTrabalho);
					responsavel.setLogradouro(cbLogradouro.getSelectedItem().toString());
					responsavel.setEndereco(txtEndereco.getText());
					responsavel
							.setNumeroCasa(txtNumero.getText().isEmpty() ? 0 : Integer.parseInt(txtNumero.getText()));
					responsavel.setRenda(Double.parseDouble(txtRenda.getText()));
					responsavel.setCasa_Propria(cbCasaPropria.getSelectedItem().toString() == "Sim" ? true : false);
					responsavel.setNumero_de_Filhos(Integer.parseInt(txtNumeroFilho.getText()));
					responsavel
							.setNumero_de_Pessoas_que_Residem_na_Casa(Integer.parseInt(txtNumeroPessoasCasa.getText()));

					DAOResponsavel daoResponsavel = new DAOResponsavel();

					if (txtRegistro.getText().equals("NOVO")) {
						daoResponsavel.novoResponsavel(responsavel);
					} else {
						responsavel.setRegistro(Integer.parseInt(txtRegistro.getText()));
						daoResponsavel.atualizaResponsavel(responsavel);
					}

					FuncoesGlobais.limpaCampos(pnlCadastroResponsavel);
					FuncoesGlobais.limpaCampos(pnlPerfilSocioEconomico);
					FuncoesGlobais.limpaCampos(pnlLocalDeTrabalho);
					FuncoesGlobais.desativaCampos(pnlCadastroResponsavel);
					FuncoesGlobais.desativaCampos(pnlPerfilSocioEconomico);
					FuncoesGlobais.desativaCampos(pnlLocalDeTrabalho);
					FuncoesGlobais.desativaCampos(pnlBotoes);

					btnNovo.setEnabled(true);
					btnPesquisar.setEnabled(true);

					if (txtRegistro.getText().equals("NOVO")) {
						JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sistema",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(this, "Cadastro atualizado com sucesso!", "Sistema",
								JOptionPane.INFORMATION_MESSAGE);

					}
				} catch (ParseException | SQLException e) {
					e.printStackTrace();

					JOptionPane.showMessageDialog(this, "Erro ao tentar salvar o cadastro!", "Sistema",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	private void btnExcluir_click() throws NumberFormatException, SQLException {

		if (JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o cadastro?", "Sistema",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			DAOResponsavel daoResponsavel = new DAOResponsavel();

			daoResponsavel.excluirResponsavel(Integer.parseInt(txtRegistro.getText()));
			JOptionPane.showMessageDialog(this, "Registro excluido com sucesso!", "Sistema",
					JOptionPane.INFORMATION_MESSAGE);

			FuncoesGlobais.limpaCampos(pnlCadastroResponsavel);
			FuncoesGlobais.limpaCampos(pnlLocalDeTrabalho);
			FuncoesGlobais.limpaCampos(pnlPerfilSocioEconomico);
			FuncoesGlobais.desativaCampos(pnlCadastroResponsavel);
			FuncoesGlobais.desativaCampos(pnlLocalDeTrabalho);
			FuncoesGlobais.desativaCampos(pnlPerfilSocioEconomico);
			FuncoesGlobais.desativaCampos(pnlBotoes);

			btnNovo.setEnabled(true);
			btnPesquisar.setEnabled(true);
			btnNovo.requestFocus();
		}
	}

	private void btnAlterar_click() {
		FuncoesGlobais.ativaCampos(pnlCadastroResponsavel);
		FuncoesGlobais.ativaCampos(pnlLocalDeTrabalho);
		FuncoesGlobais.ativaCampos(pnlPerfilSocioEconomico);
		FuncoesGlobais.desativaCampos(pnlBotoes);

		btnSalvar.setEnabled(true);
		btnCancelar.setEnabled(true);
		txtRegistro.setEnabled(false);
		txtCPF.requestFocus();
	}

	private void btnCancelar_click() {
		if (JOptionPane.showConfirmDialog(this, "Deseja realmente cancelar o cadastrado?", "Sistema",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {

			FuncoesGlobais.limpaCampos(pnlCadastroResponsavel);
			FuncoesGlobais.limpaCampos(pnlPerfilSocioEconomico);
			FuncoesGlobais.limpaCampos(pnlLocalDeTrabalho);

			FuncoesGlobais.desativaCampos(pnlCadastroResponsavel);
			FuncoesGlobais.desativaCampos(pnlPerfilSocioEconomico);
			FuncoesGlobais.desativaCampos(pnlLocalDeTrabalho);

			FuncoesGlobais.desativaCampos(pnlBotoes);
			FuncoesGlobais.resetaBordaPadrao(pnlCadastroResponsavel);
			FuncoesGlobais.resetaBordaPadrao(pnlPerfilSocioEconomico);
			FuncoesGlobais.resetaBordaPadrao(pnlLocalDeTrabalho);

			btnNovo.setEnabled(true);
			btnPesquisar.setEnabled(true);

			JOptionPane.showMessageDialog(this, "Cancelado com sucesso!", "Sistema", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void btnPesquisar_click() throws ParseException, SQLException, PropertyVetoException {
		if (frmPesquisaResponsavel.getInstance().isVisible()) {
			frmPesquisaResponsavel.getInstance().atualizaDados(); // Atualiza os dados do formulario
			frmPesquisaResponsavel.getInstance().getBtnConfirma().setVisible(true);
			frmPesquisaResponsavel.getInstance().getBtnConfirma().setText("Abrir cadastro de responsável");
			frmPesquisaResponsavel.getInstance().setSelected(true);
		} else {
			frmPesquisaResponsavel.getInstance().atualizaDados(); // Atualiza os dados do formulario
			frmMenu.getInstance().getDskPrincipal().add(frmPesquisaResponsavel.getInstance());
			frmPesquisaResponsavel.getInstance().getBtnConfirma().setVisible(true);
			frmPesquisaResponsavel.getInstance().getBtnConfirma().setText("Abrir cadastro de responsável");
			frmPesquisaResponsavel.getInstance().setVisible(true);
			frmPesquisaResponsavel.getInstance().setSelected(true);
		}
	}

	public void internalFrameActivated(InternalFrameEvent e) {
	}

	// Limpar o buffer da memoria
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

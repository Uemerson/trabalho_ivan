package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import controler.FuncoesGlobais;
import dao.DAOAluno;
import dao.DAOFuncionario;
import dao.DAOMatricula;
import dao.DAOMensalidade;
import dao.DAOResponsavel;
import model.Aluno;
import model.Funcionario;
import model.Matricula;
import model.Mensalidade;
import model.Responsavel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.InternalFrameEvent;

public class frmCadastroMatricula extends JInternalFrame implements ActionListener, InternalFrameListener {
	private JPanel pnlBotoes;
	private JButton btnNovo;
	private JButton btnExcluir;
	private JButton btnAlterar;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnPesquisar;
	private JTextField txtRegistro;
	private JLabel lblDataDaMatricula;
	private JLabel lblRegistro;
	private JLabel lblAluno;
	private JFormattedTextField txtDataDaMatricula;
	private JComboBox cbNivelQueEstaSendoMatriculado;
	private JLabel lblSerie;
	private JComboBox cbSerie;
	private JLabel lblTurno;
	private JComboBox cbTurno;
	private JLabel lblPaiOuResponsavel;
	private JComboBox cbResponsavel;
	private JLabel lblSecretrio;
	private JComboBox cbSecretario;

	private static frmCadastroMatricula singleton = null;
	private JPanel pnlCadastroDeMatricula;
	private JComboBox cbAluno;
	private JButton btnPesquisarAluno;
	private JLabel lblNvelEmQue;
	private JTextField txtVencimentoDia;
	private JLabel lblVencimentoDia;

	private int idAluno, idSecretario, idResponsavel, idMensalidade;
	private JButton btnPesquisarResponsavel;
	private JButton btnPesquisarSecretario;
	private JComboBox cbMensalidade;
	private JButton btnPesquisarMensalidade;
	
	public static frmCadastroMatricula getInstance() throws ParseException {
		if (singleton == null) {
			singleton = new frmCadastroMatricula();
		}

		return singleton;
	}

	public frmCadastroMatricula() throws ParseException {
		addInternalFrameListener(this);
		setBounds(100, 100, 807, 382);
		setClosable(true);
		setTitle("Cadastro de Matr\u00EDcula");
		getContentPane().setLayout(null);

		pnlBotoes = new JPanel();
		pnlBotoes.setBounds(10, 11, 685, 97);
		pnlBotoes.setLayout(null);
		getContentPane().add(pnlBotoes);

		btnNovo = new JButton("Novo");
		btnNovo.setIcon(new ImageIcon(frmCadastroMatricula.class.getResource("/imagens/novo 48x48.png")));
		btnNovo.addActionListener(this);
		btnNovo.setBounds(10, 11, 80, 79);
		btnNovo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNovo.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlBotoes.add(btnNovo);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(frmCadastroMatricula.class.getResource("/imagens/excluir 48x48.png")));
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(100, 11, 80, 79);
		btnExcluir.setHorizontalTextPosition(SwingConstants.CENTER);
		btnExcluir.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlBotoes.add(btnExcluir);

		btnAlterar = new JButton("Alterar");
		btnAlterar.setIcon(new ImageIcon(frmCadastroMatricula.class.getResource("/imagens/editar 48x48.png")));
		btnAlterar.setEnabled(false);
		btnAlterar.setBounds(190, 11, 80, 79);
		btnAlterar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAlterar.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlBotoes.add(btnAlterar);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(frmCadastroMatricula.class.getResource("/imagens/salvar 48x48.png")));
		btnSalvar.addActionListener(this);
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(280, 11, 80, 79);
		btnSalvar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalvar.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlBotoes.add(btnSalvar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(frmCadastroMatricula.class.getResource("/imagens/cancelar 48x48.png")));
		btnCancelar.addActionListener(this);
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(370, 11, 80, 79);
		btnCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlBotoes.add(btnCancelar);

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setIcon(new ImageIcon(frmCadastroMatricula.class.getResource("/imagens/lupa 48x48.png")));
		btnPesquisar.addActionListener(this);
		btnPesquisar.setBounds(460, 11, 85, 79);
		btnPesquisar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPesquisar.setVerticalTextPosition(SwingConstants.BOTTOM);
		pnlBotoes.add(btnPesquisar);

		pnlCadastroDeMatricula = new JPanel();
		pnlCadastroDeMatricula.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Cadastro de Matr\u00EDcula", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlCadastroDeMatricula.setBounds(10, 119, 771, 222);
		getContentPane().add(pnlCadastroDeMatricula);
		pnlCadastroDeMatricula.setLayout(null);

		lblRegistro = new JLabel("Registro");
		lblRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegistro.setBounds(10, 23, 90, 28);
		pnlCadastroDeMatricula.add(lblRegistro);

		txtRegistro = new JTextField();
		txtRegistro.setEnabled(false);
		txtRegistro.setColumns(10);
		txtRegistro.setBounds(141, 23, 139, 28);
		pnlCadastroDeMatricula.add(txtRegistro);

		lblDataDaMatricula = new JLabel("Data da Matr\u00EDcula");
		lblDataDaMatricula.setHorizontalAlignment(SwingConstants.LEFT);
		lblDataDaMatricula.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDaMatricula.setBounds(10, 62, 153, 28);
		pnlCadastroDeMatricula.add(lblDataDaMatricula);

		lblAluno = new JLabel("Aluno");
		lblAluno.setHorizontalAlignment(SwingConstants.LEFT);
		lblAluno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAluno.setBounds(330, 23, 91, 28);
		pnlCadastroDeMatricula.add(lblAluno);

		cbAluno = new JComboBox();
		cbAluno.setEnabled(false);
		cbAluno.setBounds(431, 23, 139, 28);
		pnlCadastroDeMatricula.add(cbAluno);

		txtDataDaMatricula = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataDaMatricula.setEnabled(false);
		txtDataDaMatricula.setBounds(141, 62, 139, 28);
		pnlCadastroDeMatricula.add(txtDataDaMatricula);

		cbNivelQueEstaSendoMatriculado = new JComboBox();
		cbNivelQueEstaSendoMatriculado.setModel(new DefaultComboBoxModel(new String[] {"Educa\u00E7\u00E3o infantil", "Fundamental 1", "Fundamental 2", "Ensino m\u00E9dio"}));
		cbNivelQueEstaSendoMatriculado.setEnabled(false);
		cbNivelQueEstaSendoMatriculado.setBounds(541, 64, 221, 28);
		cbNivelQueEstaSendoMatriculado.setSelectedItem(null);
		pnlCadastroDeMatricula.add(cbNivelQueEstaSendoMatriculado);

		lblSerie = new JLabel("S\u00E9rie");
		lblSerie.setHorizontalAlignment(SwingConstants.LEFT);
		lblSerie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSerie.setBounds(10, 101, 90, 28);
		pnlCadastroDeMatricula.add(lblSerie);

		cbSerie = new JComboBox();
		cbSerie.setEnabled(false);
		cbSerie.setForeground(new Color(0, 0, 0));
		cbSerie.setModel(new DefaultComboBoxModel(new String[] { "1\u00BA ano", "2\u00BA ano", "3\u00BA ano",
				"4\u00BA ano", "5\u00BA ano", "6\u00BA ano", "7\u00BA ano", "8\u00BA ano", "9\u00BA ano",
				"1\u00BA colegial", "2\u00BA colegial", "3\u00BA colegial" }));
		cbSerie.setBounds(141, 101, 139, 28);
		cbSerie.setSelectedItem(null);
		pnlCadastroDeMatricula.add(cbSerie);

		lblTurno = new JLabel("Turno");
		lblTurno.setHorizontalAlignment(SwingConstants.LEFT);
		lblTurno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTurno.setBounds(623, 23, 47, 28);
		pnlCadastroDeMatricula.add(lblTurno);

		cbTurno = new JComboBox();
		cbTurno.setEnabled(false);
		cbTurno.setModel(new DefaultComboBoxModel(new String[] { "Matutino", "Vespetino" }));
		cbTurno.setBounds(672, 23, 90, 28);
		cbTurno.setSelectedItem(null);
		pnlCadastroDeMatricula.add(cbTurno);

		lblPaiOuResponsavel = new JLabel("Pai ou Respons\u00E1vel");
		lblPaiOuResponsavel.setHorizontalAlignment(SwingConstants.LEFT);
		lblPaiOuResponsavel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPaiOuResponsavel.setBounds(10, 138, 130, 28);
		pnlCadastroDeMatricula.add(lblPaiOuResponsavel);

		cbResponsavel = new JComboBox();
		cbResponsavel.setEnabled(false);
		cbResponsavel.setBounds(141, 138, 139, 28);
		pnlCadastroDeMatricula.add(cbResponsavel);

		lblSecretrio = new JLabel("Secret\u00E1rio (a)");
		lblSecretrio.setHorizontalAlignment(SwingConstants.LEFT);
		lblSecretrio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSecretrio.setBounds(330, 101, 90, 28);
		pnlCadastroDeMatricula.add(lblSecretrio);

		cbSecretario = new JComboBox();
		cbSecretario.setEnabled(false);
		cbSecretario.setBounds(430, 101, 294, 28);
		pnlCadastroDeMatricula.add(cbSecretario);

		btnPesquisarResponsavel = new JButton("");
		btnPesquisarResponsavel.addActionListener(this);
		btnPesquisarResponsavel
				.setIcon(new ImageIcon(frmCadastroMatricula.class.getResource("/imagens/pesquisar.png")));
		btnPesquisarResponsavel.setEnabled(false);
		btnPesquisarResponsavel.setBounds(290, 138, 28, 28);
		pnlCadastroDeMatricula.add(btnPesquisarResponsavel);

		btnPesquisarAluno = new JButton("");
		btnPesquisarAluno.addActionListener(this);
		btnPesquisarAluno.setIcon(new ImageIcon(frmCadastroMatricula.class.getResource("/imagens/pesquisar.png")));
		btnPesquisarAluno.setEnabled(false);
		btnPesquisarAluno.setBounds(585, 23, 28, 28);
		pnlCadastroDeMatricula.add(btnPesquisarAluno);
		
		btnPesquisarSecretario = new JButton("");
		btnPesquisarSecretario.addActionListener(this);
		btnPesquisarSecretario.setIcon(new ImageIcon(frmCadastroMatricula.class.getResource("/imagens/pesquisar.png")));
		btnPesquisarSecretario.setEnabled(false);
		btnPesquisarSecretario.setBounds(734, 101, 28, 28);
		pnlCadastroDeMatricula.add(btnPesquisarSecretario);
		
		btnPesquisarMensalidade = new JButton("");
		btnPesquisarMensalidade.addActionListener(this);
		btnPesquisarMensalidade.setIcon(new ImageIcon(frmCadastroMatricula.class.getResource("/imagens/pesquisar.png")));
		btnPesquisarMensalidade.setEnabled(false);
		btnPesquisarMensalidade.setBounds(734, 138, 28, 28);
		pnlCadastroDeMatricula.add(btnPesquisarMensalidade);
		
		cbMensalidade = new JComboBox();
		cbMensalidade.setEnabled(false);
		cbMensalidade.setBounds(430, 138, 294, 28);
		pnlCadastroDeMatricula.add(cbMensalidade);
		
		JLabel lblMensalidade = new JLabel("Mensalidade");
		lblMensalidade.setHorizontalAlignment(SwingConstants.LEFT);
		lblMensalidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMensalidade.setBounds(330, 138, 92, 28);
		pnlCadastroDeMatricula.add(lblMensalidade);
		
		lblNvelEmQue = new JLabel("N\u00EDvel em que est\u00E1 matr\u00EDculado");
		lblNvelEmQue.setBounds(330, 64, 201, 28);
		pnlCadastroDeMatricula.add(lblNvelEmQue);
		lblNvelEmQue.setHorizontalAlignment(SwingConstants.LEFT);
		lblNvelEmQue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtVencimentoDia = new JTextField();
		txtVencimentoDia.setEnabled(false);
		txtVencimentoDia.setColumns(10);
		txtVencimentoDia.setBounds(141, 177, 139, 28);
		pnlCadastroDeMatricula.add(txtVencimentoDia);
		
		lblVencimentoDia = new JLabel("Vencimento dia");
		lblVencimentoDia.setHorizontalAlignment(SwingConstants.LEFT);
		lblVencimentoDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVencimentoDia.setBounds(10, 177, 116, 28);
		pnlCadastroDeMatricula.add(lblVencimentoDia);

	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnNovo) {
				btnNovo_click();
			} else if (e.getSource() == btnSalvar) {
				btnSalvar_click();
			} else if (e.getSource() == btnCancelar) {
				btnCancelar_click();
			} else if (e.getSource() == btnPesquisar) {
				btnPesquisar_click();
			} else if (e.getSource() == btnPesquisarResponsavel) {
				btnPesquisarResponsavel_click();
			} else if (e.getSource() == btnPesquisarAluno) {
				btnPesquisarAluno_click();
			} else if (e.getSource() == btnPesquisarSecretario) {
				btnPesquisarSecretario_click();
			} else if (e.getSource() == btnPesquisarMensalidade) {
				btnPesquisarMensalidade_click();
			}
			
		} catch (PropertyVetoException | ParseException | SQLException ex) {
			JOptionPane.showMessageDialog(this, "Erro ao tentar concluir a��o, tente novamente!", "Sistema", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void btnPesquisarResponsavel_click() throws ParseException, SQLException, PropertyVetoException {
		idResponsavel = 0;
		
		//Limpa os dados da Combobox
		cbResponsavel.removeAll();
		DefaultComboBoxModel<String> cbResponsavelModel = new DefaultComboBoxModel<>();
		for (int i = 0; i < new DAOResponsavel().listaResponsavel().size(); i++) {
			cbResponsavelModel.addElement(new DAOResponsavel().listaResponsavel().get(i).getNome_do_Responsavel());
		}
		
		cbResponsavel.setModel(cbResponsavelModel);
		cbResponsavel.setSelectedItem(null);
		
		if (frmPesquisaResponsavel.getInstance().isVisible()) {
			frmPesquisaResponsavel.getInstance().atualizaDados(); // Atualiza os dados do formulario
			frmPesquisaResponsavel.getInstance().getBtnConfirma().setVisible(true);
			frmPesquisaResponsavel.getInstance().getBtnConfirma().setText("Abrir cadastro de matr�cula");
			frmPesquisaResponsavel.getInstance().setSelected(true);
		} else {
			frmPesquisaResponsavel.getInstance().atualizaDados(); // Atualiza os dados do formulario
			frmMenu.getFrmMenu().getDskPrincipal().add(frmPesquisaResponsavel.getInstance());
			frmPesquisaResponsavel.getInstance().getBtnConfirma().setVisible(true);
			frmPesquisaResponsavel.getInstance().getBtnConfirma().setText("Abrir cadastro de matr�cula");
			frmPesquisaResponsavel.getInstance().setVisible(true);
			frmPesquisaResponsavel.getInstance().setSelected(true);
		}
	}
	
	private void btnPesquisarAluno_click() throws SQLException, ParseException, PropertyVetoException {
		idAluno = 0;
		
		//Limpa os dados da Combobox
		cbAluno.removeAll();
		DefaultComboBoxModel<String> cbAlunoModel = new DefaultComboBoxModel<>();
		for (int i = 0; i < new DAOAluno().listaAluno().size(); i++) {
			cbAlunoModel.addElement(new DAOAluno().listaAluno().get(i).getNome());
		}
		
		cbAluno.setModel(cbAlunoModel);
		cbAluno.setSelectedItem(null);
		
		if (frmPesquisaAluno.getInstance().isVisible()) {
			frmPesquisaAluno.getInstance().atualizaDados(); // Atualiza os dados do formulario
			frmPesquisaAluno.getInstance().getBtnConfirma().setVisible(true);
			frmPesquisaAluno.getInstance().getBtnConfirma().setText("Abrir cadastro de matr�cula");
			frmPesquisaAluno.getInstance().setSelected(true);
		} else {
			frmPesquisaAluno.getInstance().atualizaDados(); // Atualiza os dados do formulario
			frmMenu.getFrmMenu().getDskPrincipal().add(frmPesquisaAluno.getInstance());
			frmPesquisaAluno.getInstance().getBtnConfirma().setVisible(true);
			frmPesquisaAluno.getInstance().getBtnConfirma().setText("Abrir cadastro de matr�cula");
			frmPesquisaAluno.getInstance().setVisible(true);
			frmPesquisaAluno.getInstance().setSelected(true);
		}
	}
	
	private void btnPesquisarSecretario_click() throws SQLException, ParseException, PropertyVetoException {
		idSecretario = 0;
		
		//Limpa os dados da Combobox
		cbSecretario.removeAll();
		DefaultComboBoxModel<String> cbSecretarioModel = new DefaultComboBoxModel<>();
		for (int i = 0; i < new DAOFuncionario().listaFuncionario().size(); i++) {
			cbSecretarioModel.addElement(new DAOFuncionario().listaFuncionario().get(i).getNome());
		}
		
		cbSecretario.setModel(cbSecretarioModel);
		cbSecretario.setSelectedItem(null);
		
		if (frmPesquisaFuncionario.getInstance().isVisible()) {
			frmPesquisaFuncionario.getInstance().atualizaDados(); // Atualiza os dados do formulario
			frmPesquisaFuncionario.getInstance().getBtnConfirma().setVisible(true);
			frmPesquisaFuncionario.getInstance().getBtnConfirma().setText("Abrir cadastro de matr�cula");
			frmPesquisaFuncionario.getInstance().setSelected(true);
		} else {
			frmPesquisaFuncionario.getInstance().atualizaDados(); // Atualiza os dados do formulario
			frmMenu.getFrmMenu().getDskPrincipal().add(frmPesquisaFuncionario.getInstance());
			frmPesquisaFuncionario.getInstance().getBtnConfirma().setVisible(true);
			frmPesquisaFuncionario.getInstance().getBtnConfirma().setText("Abrir cadastro de matr�cula");
			frmPesquisaFuncionario.getInstance().setVisible(true);
			frmPesquisaFuncionario.getInstance().setSelected(true);
		}
	}
	
	private void btnPesquisarMensalidade_click() throws SQLException, ParseException, PropertyVetoException {
		idMensalidade = 0;
		
		//Limpa os dados da Combobox
		cbMensalidade.removeAll();
		DefaultComboBoxModel<String> cbMensalidadeModel = new DefaultComboBoxModel<>();
		for (int i = 0; i < new DAOMensalidade().listaMensalidade().size(); i++) {
			cbMensalidadeModel.addElement("Mensalidade n� " + new DAOMensalidade().listaMensalidade().get(i).getRegistro());
		}
		
		cbMensalidade.setModel(cbMensalidadeModel);
		cbMensalidade.setSelectedItem(null);
		
		if (frmPesquisaMensalidade.getInstance().isVisible()) {
			frmPesquisaMensalidade.getInstance().atualizaDados(); // Atualiza os dados do formulario
			frmPesquisaMensalidade.getInstance().getBtnConfirma().setVisible(true);
			frmPesquisaMensalidade.getInstance().getBtnConfirma().setText("Abrir cadastro de matr�cula");
			frmPesquisaMensalidade.getInstance().setSelected(true);
		} else {
			frmPesquisaMensalidade.getInstance().atualizaDados(); // Atualiza os dados do formulario
			frmMenu.getFrmMenu().getDskPrincipal().add(frmPesquisaMensalidade.getInstance());
			frmPesquisaMensalidade.getInstance().getBtnConfirma().setVisible(true);
			frmPesquisaMensalidade.getInstance().getBtnConfirma().setText("Abrir cadastro de matr�cula");
			frmPesquisaMensalidade.getInstance().setVisible(true);
			frmPesquisaMensalidade.getInstance().setSelected(true);
		}
	}
	
	public void preencheResponsavel(Responsavel responsavel) throws SQLException {
		this.idResponsavel = responsavel.getRegistro();
		cbResponsavel.setSelectedItem(responsavel.getNome_do_Responsavel());
	}
	
	public void preencheAluno(Aluno aluno) {
		this.idAluno = aluno.getRegistro();
		cbAluno.setSelectedItem(aluno.getNome());
	}
	
	public void preencheSecretario(Funcionario funcionario) {
		this.idSecretario = funcionario.getRegistro();
		cbSecretario.setSelectedItem(funcionario.getNome());
	}
	
	public void preencheMensalidade(Mensalidade mensalidade) {
		this.idMensalidade = mensalidade.getRegistro();
		cbMensalidade.setSelectedItem("Mensalidade n� " + mensalidade.getRegistro());
	}
	
	private void btnNovo_click() {
		FuncoesGlobais.limpaCampos(pnlCadastroDeMatricula);
		FuncoesGlobais.ativaCampos(pnlCadastroDeMatricula);
		FuncoesGlobais.desativaCampos(pnlBotoes);

		btnSalvar.setEnabled(true);
		btnCancelar.setEnabled(true);
		txtRegistro.setText("NOVO");
		txtRegistro.setEnabled(false);
		
		idAluno = idSecretario = idResponsavel = idMensalidade = 0;
		
		cbResponsavel.setEnabled(false);
		cbAluno.setEnabled(false);
		cbSecretario.setEnabled(false);
		cbMensalidade.setEnabled(false);
		
	}

	private void btnCancelar_click() {

		if (JOptionPane.showConfirmDialog(this, "Deseja realmente cancelar o novo cadastrado?", "Sistema",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {

			FuncoesGlobais.limpaCampos(pnlCadastroDeMatricula);
			FuncoesGlobais.desativaCampos(pnlCadastroDeMatricula);
			FuncoesGlobais.desativaCampos(pnlCadastroDeMatricula);
			FuncoesGlobais.desativaCampos(pnlBotoes);
			FuncoesGlobais.resetaBordaPadrao(pnlCadastroDeMatricula);
			btnNovo.setEnabled(true);
			btnPesquisar.setEnabled(true);

			JOptionPane.showMessageDialog(this, "Cancelado com sucesso!", "Sistema", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void btnSalvar_click() throws ParseException, SQLException {

		if (FuncoesGlobais.verificaCampos(pnlCadastroDeMatricula)) {

			JOptionPane.showMessageDialog(this, "Erro - Os campos em vermelho devem ser preenchidos!", "Sistema",
					JOptionPane.ERROR_MESSAGE);

		} else {

			if (JOptionPane.showConfirmDialog(this, "Deseja realmente salvar o cadastrado?", "Sistema",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {

				Matricula matricula = new Matricula();
				matricula.setAluno(idAluno);
				matricula.setTurno(cbTurno.getSelectedItem().toString());
				matricula.setData_de_Matricula(new SimpleDateFormat("dd/MM/yyyy").parse(txtDataDaMatricula.getText()));
				matricula.setNivel_Em_Que__Esta_Sendo_Matriculado(cbNivelQueEstaSendoMatriculado.getSelectedItem().toString());
				matricula.setSerie(cbSerie.getSelectedItem().toString());
				matricula.setSecretario(idSecretario);
				matricula.setResponsavel(idResponsavel);
				matricula.setMensalidade(idMensalidade);
				matricula.setVencimentoDia(Integer.parseInt(txtVencimentoDia.getText()));
				
				DAOMatricula daoMatricula = new DAOMatricula();
				if (txtRegistro.getText().equals("NOVO")) {
					daoMatricula.novoMatricula(matricula);
				}
				
				FuncoesGlobais.limpaCampos(pnlCadastroDeMatricula);
				FuncoesGlobais.desativaCampos(pnlCadastroDeMatricula);
				FuncoesGlobais.desativaCampos(pnlBotoes);

				btnNovo.setEnabled(true);
				btnPesquisar.setEnabled(true);

				JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sistema",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}

	private void btnPesquisar_click() throws PropertyVetoException, ParseException, SQLException {
		if (frmPesquisaMatricula.getInstance().isVisible()) {
			frmPesquisaMatricula.getInstance().setSelected(true);
		} else {
			frmMenu.getFrmMenu().getDskPrincipal().add(frmPesquisaMatricula.getInstance());
			frmPesquisaMatricula.getInstance().setVisible(true);
			frmPesquisaMatricula.getInstance().setSelected(true);
		}
	}

	public void internalFrameActivated(InternalFrameEvent e) {
	}
	
	//Limpa a memoria
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

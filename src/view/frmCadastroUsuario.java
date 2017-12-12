package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import controler.FuncoesGlobais;
import dao.DAOConexaoMySQL;
import dao.DAOFuncionario;
import dao.DAOUsuario;
import model.Usuario;

import javax.swing.JPasswordField;

public class frmCadastroUsuario extends JInternalFrame implements ActionListener, InternalFrameListener {
	private JPanel pnlBotoes;
	private JButton btnNovo;
	private JButton btnExcluir;
	private JButton btnAlterar;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnPesquisar;
	private JTextField txtRegistro;
	private JTextField txtLogin;
	private JLabel lblRegistro;
	private JLabel lblLogin;
	private JLabel lblSenha;
	private JButton btnPesquisarFuncionario;
	private JPanel pnlCadastroDeUsuario;
	private JComboBox cbFuncionario;
	private int idFuncionario = 0;

	private static frmCadastroUsuario singleton = null;
	private JPasswordField txtSenha;

	public static frmCadastroUsuario getInstance() throws ParseException, SQLException {
		if (singleton == null) {
			singleton = new frmCadastroUsuario();
		}

		return singleton;
	}

	public static void setInstance(frmCadastroUsuario estado) {
		singleton = estado;
	}
	
	public frmCadastroUsuario() throws SQLException {
		idFuncionario = 0;
		addInternalFrameListener(this);
		setBounds(100, 100, 591, 336);
		setClosable(true);
		setTitle("Cadastro de Usuário");
		getContentPane().setLayout(null);

		// Hack para remover icone do nimbus
		Container pane = ((BasicInternalFrameUI) this.getUI()).getNorthPane();
		// pane.remove(0);
		pane.getComponent(0).setVisible(false);

		pnlBotoes = new JPanel();
		pnlBotoes.setBounds(10, 11, 555, 102);
		pnlBotoes.setLayout(null);
		getContentPane().add(pnlBotoes);

		btnNovo = new JButton("Novo");
		btnNovo.setIcon(new ImageIcon(frmCadastroUsuario.class.getResource("/imagens/novo 48x48.png")));
		btnNovo.addActionListener(this);
		btnNovo.setBounds(10, 11, 80, 79);
		btnNovo.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNovo.setHorizontalTextPosition(SwingConstants.CENTER);
		pnlBotoes.add(btnNovo);

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(this);
		btnExcluir.setIcon(new ImageIcon(frmCadastroUsuario.class.getResource("/imagens/excluir 48x48.png")));
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(100, 11, 80, 79);
		btnExcluir.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnExcluir.setHorizontalTextPosition(SwingConstants.CENTER);
		pnlBotoes.add(btnExcluir);

		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(this);
		btnAlterar.setIcon(new ImageIcon(frmCadastroUsuario.class.getResource("/imagens/editar 48x48.png")));
		btnAlterar.setEnabled(false);
		btnAlterar.setBounds(190, 11, 80, 79);
		btnAlterar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAlterar.setHorizontalTextPosition(SwingConstants.CENTER);
		pnlBotoes.add(btnAlterar);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(frmCadastroUsuario.class.getResource("/imagens/salvar 48x48.png")));
		btnSalvar.addActionListener(this);
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(280, 11, 80, 79);
		btnSalvar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSalvar.setHorizontalTextPosition(SwingConstants.CENTER);
		pnlBotoes.add(btnSalvar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(frmCadastroUsuario.class.getResource("/imagens/cancelar 48x48.png")));
		btnCancelar.addActionListener(this);
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(370, 11, 80, 79);
		btnCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
		pnlBotoes.add(btnCancelar);

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setIcon(new ImageIcon(frmCadastroUsuario.class.getResource("/imagens/lupa 48x48.png")));
		btnPesquisar.addActionListener(this);
		btnPesquisar.setBounds(460, 11, 85, 79);
		btnPesquisar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnPesquisar.setHorizontalTextPosition(SwingConstants.CENTER);
		pnlBotoes.add(btnPesquisar);

		pnlCadastroDeUsuario = new JPanel();
		pnlCadastroDeUsuario.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Cadastro de Usu\u00E1rio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlCadastroDeUsuario.setBounds(10, 124, 555, 174);
		getContentPane().add(pnlCadastroDeUsuario);
		pnlCadastroDeUsuario.setLayout(null);

		lblRegistro = new JLabel("Registro");
		lblRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegistro.setBounds(10, 21, 90, 28);
		pnlCadastroDeUsuario.add(lblRegistro);

		lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.LEFT);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLogin.setBounds(10, 96, 90, 28);
		pnlCadastroDeUsuario.add(lblLogin);

		lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.LEFT);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(10, 135, 90, 28);
		pnlCadastroDeUsuario.add(lblSenha);

		txtRegistro = new JTextField();
		txtRegistro.setEnabled(false);
		txtRegistro.setColumns(10);
		txtRegistro.setBounds(110, 21, 338, 28);
		pnlCadastroDeUsuario.add(txtRegistro);

		txtLogin = new JTextField();
		txtLogin.setEnabled(false);
		txtLogin.setColumns(10);
		txtLogin.setBounds(110, 96, 338, 28);
		pnlCadastroDeUsuario.add(txtLogin);

		JLabel lblFuncionrio = new JLabel("Funcion\u00E1rio");
		lblFuncionrio.setHorizontalAlignment(SwingConstants.LEFT);
		lblFuncionrio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFuncionrio.setBounds(10, 57, 90, 28);
		pnlCadastroDeUsuario.add(lblFuncionrio);

		cbFuncionario = new JComboBox();

		DefaultComboBoxModel<String> cbFuncionarioModel = new DefaultComboBoxModel<>();

		for (int i = 0; i < new DAOFuncionario().listaFuncionario().size(); i++) {
			cbFuncionarioModel.addElement(new DAOFuncionario().listaFuncionario().get(i).getNome());
		}

		cbFuncionario.setModel(cbFuncionarioModel);
		cbFuncionario.setSelectedItem(null);
		cbFuncionario.setEnabled(false);
		cbFuncionario.setBounds(110, 57, 338, 28);
		pnlCadastroDeUsuario.add(cbFuncionario);

		btnPesquisarFuncionario = new JButton("");
		btnPesquisarFuncionario.addActionListener(this);
		btnPesquisarFuncionario.setEnabled(false);
		btnPesquisarFuncionario.setIcon(new ImageIcon(frmCadastroUsuario.class.getResource("/imagens/pesquisar.png")));
		btnPesquisarFuncionario.setBounds(458, 57, 28, 28);
		pnlCadastroDeUsuario.add(btnPesquisarFuncionario);

		txtSenha = new JPasswordField();
		txtSenha.setEnabled(false);
		txtSenha.setBounds(110, 135, 338, 28);
		pnlCadastroDeUsuario.add(txtSenha);

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
			} else if (e.getSource() == btnPesquisarFuncionario) {
				btnPesquisarFuncionario_click();
			} else if (e.getSource() == btnExcluir) {
				btnExcluir_click();
			} else if (e.getSource() == btnAlterar) {
				btnAlterar_click();
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Erro ao tentar concluir ação, tente novamente!", "Sistema",
					JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}

	private void btnExcluir_click() throws NumberFormatException, SQLException {
		if (JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o cadastro?", "Sistema",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			DAOUsuario daoUsuario = new DAOUsuario();

			daoUsuario.excluirUsuario(Integer.parseInt(txtRegistro.getText()));
			JOptionPane.showMessageDialog(this, "Registro excluido com sucesso!", "Sistema",
					JOptionPane.INFORMATION_MESSAGE);

			FuncoesGlobais.limpaCampos(pnlCadastroDeUsuario);
			FuncoesGlobais.desativaCampos(pnlCadastroDeUsuario);
			FuncoesGlobais.desativaCampos(pnlBotoes);

			btnNovo.setEnabled(true);
			btnPesquisar.setEnabled(true);
			btnNovo.requestFocus();
		}
	}

	private void btnNovo_click() {
		FuncoesGlobais.desativaCampos(pnlBotoes);
		FuncoesGlobais.ativaCampos(pnlCadastroDeUsuario);
		FuncoesGlobais.limpaCampos(pnlCadastroDeUsuario);

		cbFuncionario.setEnabled(false);
		txtRegistro.setEnabled(false);
		txtRegistro.setText("NOVO");
		cbFuncionario.requestFocus();

		btnCancelar.setEnabled(true);
		btnSalvar.setEnabled(true);

		idFuncionario = 0;
		btnSalvar.requestFocus();
	}

	private void btnSalvar_click() {
		if (FuncoesGlobais.verificaCampos(pnlCadastroDeUsuario)) {
			JOptionPane.showMessageDialog(this, "Erro - Os campos em vermelho devem ser preenchidos!", "Sistema",
					JOptionPane.ERROR_MESSAGE);
		} else {

			if (JOptionPane.showConfirmDialog(this, "Deseja realmente salvar o cadastrado?", "Sistema",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				try {
					Usuario usuario = new Usuario();

					usuario.setLogin(txtLogin.getText());
					String senha = new String(txtSenha.getPassword());
					usuario.setId_funcionario(idFuncionario);
					usuario.setSenha(senha);

					DAOUsuario daoUsuario = new DAOUsuario();

					if (txtRegistro.getText().equals("NOVO")) {
						daoUsuario.novoUsuario(usuario);
					} else {
						System.out.println(idFuncionario);
						usuario.setRegistro(Integer.parseInt(txtRegistro.getText()));
						daoUsuario.alterarUsuario(usuario);
					}

					FuncoesGlobais.limpaCampos(pnlCadastroDeUsuario);
					FuncoesGlobais.desativaCampos(pnlCadastroDeUsuario);
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
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private void btnAlterar_click() {
		FuncoesGlobais.ativaCampos(pnlCadastroDeUsuario);
		FuncoesGlobais.desativaCampos(pnlBotoes);

		cbFuncionario.setEnabled(false);
		btnPesquisarFuncionario.setEnabled(false);

		btnSalvar.setEnabled(true);
		btnCancelar.setEnabled(true);
		txtRegistro.setEnabled(false);
		txtLogin.requestFocus();
	}

	private void btnPesquisar_click() throws ParseException, SQLException, PropertyVetoException {
		if (frmPesquisaUsuario.getInstance().isVisible()) {
			frmPesquisaUsuario.getInstance().atualizaDados(); // Atualiza os dados do formulario
			frmPesquisaUsuario.getInstance().getBtnConfirma().setVisible(true);
			frmPesquisaUsuario.getInstance().getBtnConfirma().setText("Abrir cadastro de usuário");
			frmPesquisaUsuario.getInstance().setSelected(true);
		} else {
			frmPesquisaUsuario.getInstance().atualizaDados(); // Atualiza os dados do formulario
			frmMenu.getInstance().getDskPrincipal().add(frmPesquisaUsuario.getInstance());
			frmPesquisaUsuario.getInstance().getBtnConfirma().setVisible(true);
			frmPesquisaUsuario.getInstance().getBtnConfirma().setText("Abrir cadastro de usuário");
			frmPesquisaUsuario.getInstance().setVisible(true);
			frmPesquisaUsuario.getInstance().setSelected(true);
		}
	}

	private void btnCancelar_click() {
		if (JOptionPane.showConfirmDialog(this, "Deseja realmente cancelar o cadastrado?", "Sistema",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

			FuncoesGlobais.limpaCampos(pnlCadastroDeUsuario);
			FuncoesGlobais.desativaCampos(pnlCadastroDeUsuario);
			FuncoesGlobais.desativaCampos(pnlBotoes);
			FuncoesGlobais.resetaBordaPadrao(pnlCadastroDeUsuario);

			btnNovo.setEnabled(true);
			btnPesquisar.setEnabled(true);

			JOptionPane.showMessageDialog(this, "Cancelado com sucesso!", "Sistema", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void btnPesquisarFuncionario_click() {
		try {
			if (frmPesquisaFuncionario.getInstance().isVisible()) {
				frmPesquisaFuncionario.getInstance().atualizaDados(); // Atualiza os dados do formulario
				frmPesquisaFuncionario.getInstance().getBtnConfirma().setVisible(true);
				frmPesquisaFuncionario.getInstance().getBtnConfirma().setText("Abrir cadastro de usuário");
				frmPesquisaFuncionario.getInstance().setSelected(true);
			} else {
				frmPesquisaFuncionario.getInstance().atualizaDados(); // Atualiza os dados do formulario
				frmMenu.getInstance().getDskPrincipal().add(frmPesquisaFuncionario.getInstance());
				frmPesquisaFuncionario.getInstance().getBtnConfirma().setVisible(true);
				frmPesquisaFuncionario.getInstance().getBtnConfirma().setText("Abrir cadastro de usuário");
				frmPesquisaFuncionario.getInstance().setVisible(true);
				frmPesquisaFuncionario.getInstance().setSelected(true);
			}
		} catch (ParseException | SQLException | PropertyVetoException ex) {
			JOptionPane.showMessageDialog(this, "Erro ao tentar abrir pesquisa de funcionario, tente novamente!",
					"Sistema", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void preencheFuncionario(int idFuncionario) throws SQLException {
		this.idFuncionario = idFuncionario;

		cbFuncionario.setSelectedItem(new DAOFuncionario().buscaFuncionario(idFuncionario).getNome());

		// System.out.println("Id funcionario: " + idFuncionario);
	}

	public void preencheCadastro(Usuario usuario) {
		FuncoesGlobais.desativaCampos(pnlBotoes);
		FuncoesGlobais.limpaCampos(pnlCadastroDeUsuario);
		FuncoesGlobais.resetaBordaPadrao(pnlCadastroDeUsuario);
		FuncoesGlobais.desativaCampos(pnlCadastroDeUsuario);

		pnlBotoes.requestFocusInWindow();

		btnNovo.setEnabled(true);
		btnAlterar.setEnabled(true);
		btnExcluir.setEnabled(true);
		btnPesquisar.setEnabled(true);

		this.idFuncionario = usuario.getId_funcionario();
		txtRegistro.setText(Integer.toString(usuario.getRegistro()));
		cbFuncionario.setSelectedItem(usuario.getNome_funcionario() == null ? null : usuario.getNome_funcionario());
		txtLogin.setText(usuario.getLogin());
		txtSenha.setText(usuario.getSenha());
	}

	public void internalFrameActivated(InternalFrameEvent e) {
	}

	// Limpo a memoria do singleton
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

	public void internalFrameOpened(InternalFrameEvent arg0e) {
	}
}

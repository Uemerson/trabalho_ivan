package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import controler.FuncoesGlobais;
import model.Cargo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Container;

import javax.swing.event.InternalFrameListener;
import javax.swing.event.SwingPropertyChangeSupport;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.event.InternalFrameEvent;

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
	private JTextField TxtSenha;
	private JLabel lblRegistro;
	private JLabel lblLogin;
	private JLabel lblSenha;
	private JButton btnPesquisarFuncionario;
	private JPanel pnlCadastroDeUsuario;
	private JComboBox cbFuncionario;

	private static frmCadastroUsuario singleton = null;

	public static frmCadastroUsuario getInstance() throws ParseException {
		if (singleton == null) {
			singleton = new frmCadastroUsuario();
		}

		return singleton;
	}

	public frmCadastroUsuario() {
		addInternalFrameListener(this);
		setBounds(100, 100, 591, 336);
		setClosable(true);
		setTitle("Cadastro de Usuário");
		getContentPane().setLayout(null);
		
		//Hack para remover icone do nimbus
		Container pane =  ((BasicInternalFrameUI) this.getUI()).getNorthPane();
		//pane.remove(0);
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
		btnExcluir.setIcon(new ImageIcon(frmCadastroUsuario.class.getResource("/imagens/excluir 48x48.png")));
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(100, 11, 80, 79);
		btnExcluir.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnExcluir.setHorizontalTextPosition(SwingConstants.CENTER);
		pnlBotoes.add(btnExcluir);

		btnAlterar = new JButton("Alterar");
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

		TxtSenha = new JTextField();
		TxtSenha.setEnabled(false);
		TxtSenha.setColumns(10);
		TxtSenha.setBounds(110, 135, 338, 28);
		pnlCadastroDeUsuario.add(TxtSenha);

		JLabel lblFuncionrio = new JLabel("Funcion\u00E1rio");
		lblFuncionrio.setHorizontalAlignment(SwingConstants.LEFT);
		lblFuncionrio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFuncionrio.setBounds(10, 57, 90, 28);
		pnlCadastroDeUsuario.add(lblFuncionrio);

		cbFuncionario = new JComboBox();

		cbFuncionario.addItem("Administrador");
		cbFuncionario.addItem("Uemerson");
		cbFuncionario.addItem("Teste");

		cbFuncionario.setSelectedItem(null);
		cbFuncionario.setEnabled(false);
		cbFuncionario.setBounds(110, 57, 338, 28);
		pnlCadastroDeUsuario.add(cbFuncionario);

		btnPesquisarFuncionario = new JButton("");
		btnPesquisarFuncionario.setEnabled(false);
		btnPesquisarFuncionario.setIcon(new ImageIcon(frmCadastroUsuario.class.getResource("/imagens/pesquisar.png")));
		btnPesquisarFuncionario.setBounds(458, 57, 28, 28);
		pnlCadastroDeUsuario.add(btnPesquisarFuncionario);

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

			}
		} catch (ParseException | SQLException | PropertyVetoException ex) {
			ex.printStackTrace();
		}
	}

	private void btnNovo_click() {
		FuncoesGlobais.desativaCampos(pnlBotoes);
		FuncoesGlobais.ativaCampos(pnlCadastroDeUsuario);

		txtRegistro.setEnabled(false);
		txtRegistro.setText("NOVO");
		cbFuncionario.requestFocus();

		btnCancelar.setEnabled(true);
		btnSalvar.setEnabled(true);
	}

	private void btnSalvar_click() {
		if (FuncoesGlobais.verificaCampos(pnlCadastroDeUsuario)) {
			JOptionPane.showMessageDialog(this, "Erro - Os campos em vermelho devem ser preenchidos!", "Sistema",
					JOptionPane.ERROR_MESSAGE);
		} else {

			if (JOptionPane.showConfirmDialog(this, "Deseja realmente salvar o novo cadastrado?", "Sistema",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

				FuncoesGlobais.limpaCampos(pnlCadastroDeUsuario);
				FuncoesGlobais.desativaCampos(pnlCadastroDeUsuario);
				FuncoesGlobais.desativaCampos(pnlBotoes);

				btnNovo.setEnabled(true);
				btnPesquisar.setEnabled(true);

				JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sistema",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	private void btnPesquisar_click() throws ParseException, SQLException, PropertyVetoException {
		if (frmPesquisaUsuario.getInstance().isVisible()) {
			frmPesquisaUsuario.getInstance().setSelected(true);
		} else {
			frmMenu.getFrmMenu().getDskPrincipal().add(frmPesquisaUsuario.getInstance());
			frmPesquisaUsuario.getInstance().setVisible(true);
			frmPesquisaUsuario.getInstance().setSelected(true);
		}
	}

	private void btnCancelar_click() {
		if (JOptionPane.showConfirmDialog(this, "Deseja realmente cancelar o novo cadastrado?", "Sistema",
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

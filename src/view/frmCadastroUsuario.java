package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class frmCadastroUsuario extends JInternalFrame {
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

	
	public frmCadastroUsuario() {
		setBounds(100, 100, 591, 300);
		setClosable(true);
		setTitle("Cadastro de Usuário");
		getContentPane().setLayout(null);
		
		pnlBotoes = new JPanel();
		pnlBotoes.setBounds(10, 11, 555, 64);
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
		
		JPanel pnlCadastroDeUsuario = new JPanel();
		pnlCadastroDeUsuario.setBorder(new TitledBorder(null, "Cad\u00E1stro de Usu\u00E1rio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCadastroDeUsuario.setBounds(10, 86, 555, 174);
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
		lblLogin.setBounds(10, 60, 90, 28);
		pnlCadastroDeUsuario.add(lblLogin);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.LEFT);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(10, 99, 90, 28);
		pnlCadastroDeUsuario.add(lblSenha);
		
		txtRegistro = new JTextField();
		txtRegistro.setEnabled(false);
		txtRegistro.setColumns(10);
		txtRegistro.setBounds(110, 21, 338, 28);
		pnlCadastroDeUsuario.add(txtRegistro);
		
		txtLogin = new JTextField();
		txtLogin.setEnabled(false);
		txtLogin.setColumns(10);
		txtLogin.setBounds(110, 60, 338, 28);
		pnlCadastroDeUsuario.add(txtLogin);
		
		TxtSenha = new JTextField();
		TxtSenha.setEnabled(false);
		TxtSenha.setColumns(10);
		TxtSenha.setBounds(110, 99, 338, 28);
		pnlCadastroDeUsuario.add(TxtSenha);

	}

}

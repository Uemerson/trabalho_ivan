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
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;

public class frmCadastroUsuario extends JInternalFrame implements ActionListener {
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
	
	public static frmCadastroUsuario getFrmCadastroUsuario() throws ParseException {
		if (singleton == null) {
			singleton = new frmCadastroUsuario();
		}
		
		return singleton;
	}
	
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
		btnNovo.addActionListener(this);
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
		btnPesquisar.setBounds(460, 11, 85, 44);
		pnlBotoes.add(btnPesquisar);
		
		pnlCadastroDeUsuario = new JPanel();
		pnlCadastroDeUsuario.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cadastro de Usu\u00E1rio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
		if (e.getSource() == btnNovo) {
			btnNovo_click();
		}else if (e.getSource() == btnSalvar) {
			btnSalvar_click();
		}else if (e.getSource() == btnCancelar) {
			btnCancelar_click();
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
			JOptionPane.showMessageDialog(this, "Erro - Os campos em vermelho devem ser preenchidos!", "Sistema", JOptionPane.ERROR_MESSAGE);
		}else {
				
				if(JOptionPane.showConfirmDialog(this, 
												"Deseja realmente salvar o novo cadastrado?", 
												"Sistema", 
												JOptionPane.YES_NO_OPTION, 
												JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
					
					FuncoesGlobais.limpaCampos(pnlCadastroDeUsuario);
					FuncoesGlobais.desativaCampos(pnlCadastroDeUsuario);
					FuncoesGlobais.desativaCampos(pnlBotoes);
					
					btnNovo.setEnabled(true);
					btnPesquisar.setEnabled(true);
					
					JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sistema", JOptionPane.INFORMATION_MESSAGE);
				}
			}
	}
	
	private void btnCancelar_click() {
		if(JOptionPane.showConfirmDialog(this, 
				"Deseja realmente cancelar o novo cadastrado?", 
				"Sistema", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
			
		
			FuncoesGlobais.limpaCampos(pnlCadastroDeUsuario);
			FuncoesGlobais.desativaCampos(pnlCadastroDeUsuario);
			FuncoesGlobais.desativaCampos(pnlBotoes);
			FuncoesGlobais.resetaBordaPadrao(pnlCadastroDeUsuario);
			
			btnNovo.setEnabled(true);
			btnPesquisar.setEnabled(true);
			
			JOptionPane.showMessageDialog(this, "Cancelado com sucesso!", "Sistema", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}

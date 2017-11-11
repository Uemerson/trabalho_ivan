package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class frmLogin extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JPasswordField txtSenha;
	private JButton btnEntrar;
	private JComboBox cbLogin;
	
	public frmLogin(){
		setTitle("Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 231, 344);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(10, 147, 46, 14);
		contentPane.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(10, 211, 46, 14);
		contentPane.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(10, 236, 205, 28);
		contentPane.add(txtSenha);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(this);
		btnEntrar.setBounds(126, 281, 89, 23);
		contentPane.add(btnEntrar);
		
		JLabel lblCadeado = new JLabel("");
		lblCadeado.setIcon(new ImageIcon(frmLogin.class.getResource("/imagens/usuarioLogin_128x128.png")));
		lblCadeado.setBounds(47, 9, 142, 127);
		contentPane.add(lblCadeado);
		
		cbLogin = new JComboBox();
		cbLogin.setBounds(10, 172, 205, 28);
		AutoCompleteDecorator.decorate(cbLogin);
			
		contentPane.add(cbLogin);
	}
	
	private void btnEntrar_click(){
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btnEntrar) {
			btnEntrar_click();
		}
	}
}
